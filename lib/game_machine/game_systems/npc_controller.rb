module GameMachine
  module GameSystems
    class NpcController

      attr_reader :npc, :parent
      def initialize(npc,parent)
        @npc = npc
        @parent = parent
      end

      def position
        @position ||= Vector.new
      end

      def on_receive(message)

      end

      def update

      end

      def destroy(message)

      end

      def neighbors
        GameMachine::GameSystems::EntityTracking.neighbors_from_grid(@position.x,@position.y,nil,'player',nil)
      end

      def track
        if @track_entity
          @track_entity.vector3.set_x(@position.x).
            set_y(@position.y).set_z(@position.z)
        else
          @track_entity = Entity.new.set_track_entity(
            TrackEntity.new.set_value(true)
          ).set_id(@npc.id)
          @track_entity.set_entity_type('npc')
          @track_entity.set_vector3(
            Vector3.new.set_x(@position.x).set_y(@position.y)
          )
        end

        unless @actor_ref
          @actor_ref = GameMachine::GameSystems::EntityTracking.find.actor
        end
        @actor_ref.tell(@track_entity)
      end

    end
  end
end
