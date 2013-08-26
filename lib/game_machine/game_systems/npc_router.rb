require 'benchmark'
module GameMachine
  module GameSystems
    class NpcRouter < Actor::Base
      
      def post_init
        @npc_controllers = {}
      end

      def on_receive(message)
        if message.is_a?(String)
          if message == 'update'
            @npc_controllers.each do |npc_id,controller|
              controller.update
            end
          end
        elsif message.is_a?(Entity)
          if message.has_create_npc
            npc_id = message.create_npc.npc_id
            @npc_controllers[npc_id] = 1#NpcBehavior.new(message,self)
          elsif message.has_notify_npc
            if npc_controller = @npc_controllers.fetch(message.notify_npc.npc_id,nil)
              npc_controller.update(message)
            else
              GameMachine.logger.error("Npc Controller for #{message.notify_npc.npc_id} not found")
            end
          end
        end
      end

      def schedule_update
        duration = GameMachine::JavaLib::Duration.create(100, java.util.concurrent.TimeUnit::MILLISECONDS)
        @scheduler.schedule(duration, duration, get_self, "update", @dispatcher, nil)
      end

    end
  end
end

