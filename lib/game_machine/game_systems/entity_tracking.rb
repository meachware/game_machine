module GameMachine
  module GameSystems
    class EntityTracking < Actor::Base

      GRID =  JavaLib::Grid.new(Settings.world_grid_size,Settings.world_grid_cell_size)
      aspect %w(TrackEntity)
      aspect %w(GetNeighbors)

      attr_reader :grid


      def post_init
        @entity_updates = []
        @grid = GRID
        @paths = {}
        @width = GRID.get_width
        @cell_count = GRID.get_cell_count
      end

      def on_receive(message)
        if message.is_a?(Entity)
          if message.get_neighbors
            send_neighbors(message)
          end

          if message.track_entity
            set_entity_location(message)
          end
        else
          unhandled(message)
        end
      end

      private

      def set_entity_location(entity)
        vector = entity.vector3
        #puts "#{entity.id},#{vector.x},#{vector.y},#{vector.z},#{entity.entity_type}"
        @grid.set(entity.id,vector.x,vector.y,vector.z,entity.entity_type)
      end

      def location_entity(grid_value)
        #x = grid_value.x - ((grid_value.x / @width) * @width)
        #y = grid_value.y - ((grid_value.y / @width) * @width)
        Entity.new.set_id(grid_value.id).set_vector3(
          Vector3.new.set_xi(grid_value.x.to_i).set_yi(grid_value.y.to_i)
        )
      end

      def send_neighbors(message)
        type = message.get_neighbors.neighbor_type
        x = message.get_neighbors.vector3.x
        y = message.get_neighbors.vector3.y
        search_results = self.class.neighbors_from_grid(x,y,type)
       
        neighbors = {:players => [], :npcs => []}
        search_results.each do |grid_value|
          if grid_value.entityType == 'player'
            neighbors[:players] << location_entity(grid_value)
          elsif grid_value.entityType == 'npc'
            neighbors[:npcs] << location_entity(grid_value)
          end
        end
 
        if neighbors[:players].empty? && neighbors[:npcs].empty?
          return
        end

        if message.has_player
          send_neighbors_to_player(neighbors,message.player)
        else
          neighbors.each_slice(100) do |slice|
            send_neighbors_to_sender(slice,message)
          end
        end
      end

      def send_neighbors_to_player(neighbors,player)
        entity = Entity.new.set_neighbors(
          Neighbors.new.
          set_player_list(neighbors[:players]).
          set_npc_list(neighbors[:npcs])
        )
        entity.set_id(player.id)
        entity.set_player(player)
        entity.set_send_to_player(true)
        PlayerGateway.find.tell(entity)
      end

      def send_neighbors_to_sender(neighbors,message)
        entity = Entity.new.set_neighbors(
          Neighbors.new.
          set_player_list(neighbors[:players]).
          set_npc_list(neighbors[:npcs])
        ).set_id(message.id)
        sender.tell(entity,self)
      end

      def self.neighbors_from_grid(x,y,neighbor_type)
        GRID.neighbors(x,y,neighbor_type)
      end

    end
  end
end
