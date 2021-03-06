module GameMachine
  module Handlers
    class Request < Actor::Base

      def on_receive(message)
        if message.is_a?(ClientMessage)
          if message.has_player_logout
            logged_out(message)
          elsif message.has_client_disconnect
            disconnected(message)
          elsif message.has_player
            update_entities(message)
            if Authentication.authenticated?(message.player.id)
              game_handler.tell(message)
            else
              authenticate_player(message)
            end
          else
            unhandled(message)
          end
        else
          unhandled(message)
        end
      end

      private

      def game_handler
        @game_handler ||= Handlers::Game.find
      end

      def logged_out(message)
        PlayerRegistry.find.tell(message.player_logout)
        GameSystems::EntityTracking::GRID.remove(message.player.id)
        Authentication.unregister_player(message.player.id)
      end

      def disconnected(message)
        PlayerRegistry.find.tell(message.client_disconnect)
      end

      def update_entities(message)
        if message.get_entity_list
          message.get_entity_list.each do |entity|
            entity.set_player(message.player)
          end
        end
      end

      def authenticate_player(message)
        Authentication.find_distributed_local(
          message.player.id
        ).tell(message,self)
      end

    end
  end
end
