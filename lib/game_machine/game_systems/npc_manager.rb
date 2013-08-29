module GameMachine
  module GameSystems
    
    class NpcManager < GameMachine::Actor::Base
      
      aspect %w(CreateNpc)
      aspect %w(NotifyNpc)
      aspect %w(DestroyNpc)

      def post_init(*args)
        @actor_refs = create_npc_routers

        update_interval = args.first || 100
        schedule_update(update_interval)
      end

      def create_npc_routers
        GameMachine::Actor::Builder.new(NpcRouter).distributed(200).start
      end

      def on_receive(message)
        if message.is_a?(String)
          if message == 'update'
            @actor_refs.each {|actor_ref| actor_ref.tell('update',nil)}
          end
        elsif message.has_notify_npc
          ref = NpcRouter.find_distributed(message.notify_npc.npc_id)
          ref.tell(message)
        elsif message.has_create_npc
          ref = NpcRouter.find_distributed(message.create_npc.npc_id)
          ref.tell(message)
        elsif message.has_destroy_npc
          ref = NpcRouter.find_distributed(message.destroy_npc.npc_id)
          ref.tell(message)
        else
          unhandled(message)
        end
      end

      def schedule_update(update_interval)
        duration = GameMachine::JavaLib::Duration.create(update_interval, java.util.concurrent.TimeUnit::MILLISECONDS)
        scheduler = get_context.system.scheduler
        dispatcher = get_context.system.dispatcher
        scheduler.schedule(duration, duration, get_self, "update", dispatcher, nil)
      end

    end
  end
end
