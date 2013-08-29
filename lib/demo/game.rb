require_relative 'npc_actor'

module Demo
  class Game

    def start
      1000.times do |i|
        create_npc("#{GameMachine::Application.config.akka_port}_#{i}")
      end
    end

    def create_npc(id)
      max = GameMachine::Settings.world_grid_size - 10

      x = rand(max) + 1
      y = rand(max) + 1
      z = 1.10
      entity = Entity.new
      entity.set_id(id)
      entity.set_create_npc(
        CreateNpc.new.set_id(id).set_controller(Demo::ZombieController.name)
      )
      entity.set_is_npc(IsNpc.new.set_enabled(true))
      entity.set_vector3(Vector3.new.set_x(x.to_f).set_y(y.to_f).set_z(z.to_f))

      self.class.find.tell(entity)
    end

  end
end
