module GameMachine
  module GameSystems
    
    class NpcManager < GameMachine::Actor::Base
      
      aspect %w(CreateNpc)

      def post_init(*args)
        @scheduler = get_context.system.scheduler
        @dispatcher = get_context.system.dispatcher

        update_interval = 100
        @duration = GameMachine::JavaLib::Duration.create(update_interval, java.util.concurrent.TimeUnit::MILLISECONDS)

        @actor_refs = GameMachine::Actor::Builder.new(NpcRouter).distributed(200).start
        @npc_actors = {}
        schedule_update
      end

      def on_receive(message)
        if message.is_a?(String)
          if message == 'update'
            @actor_refs.each {|actor_ref| actor_ref.tell('update',nil)}
          end
        elsif message.has_create_npc
          ref = NpcRouter.find_distributed(message.create_npc.npc.id)
          ref.tell(message)
        end
      end

      def schedule_update
        @scheduler.schedule(@duration, @duration, get_self, "update", @dispatcher, nil)
      end

    end
  end
end
