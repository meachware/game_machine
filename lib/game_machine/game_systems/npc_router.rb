require 'benchmark'
require 'active_support/inflector'
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
          if message.has_notify_npc
            if npc_controller = @npc_controllers.fetch(message.notify_npc.npc_id,nil)
              npc_controller.on_receive(message)
            else
              GameMachine.logger.error("Npc Controller for #{message.notify_npc.npc_id} not found")
            end
          elsif message.has_create_npc
            begin
              npc_id = message.create_npc.npc_id
              controller_class = message.create_npc.controller.constantize
              @npc_controllers[npc_id] = controller_class.new(message,self)
            rescue Exception => e
              GameMachine.logger.error("CreateNpc error: #{e.class} #{e.message}")
            end
          elsif message.has_destroy_npc
            if npc_controller = @npc_controllers.fetch(message.destroy_npc.npc_id,nil)
              npc_controller.destroy(message)
              @npc_controllers.delete(message.destroy_npc.npc_id)
            else
              GameMachine.logger.error("Npc Controller for #{message.destroy_npc.npc_id} not found")
            end
          end
        end
      end

    end
  end
end

