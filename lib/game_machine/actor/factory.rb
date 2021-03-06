module GameMachine
  module Actor

    class Factory
      include JavaLib::UntypedActorFactory

      def initialize(klass,args)
        @klass = klass
        @args = args
      end

      def create
        actor = @klass.new
        if actor.respond_to?(:initialize_states)
          actor.initialize_states
        end
        if actor.respond_to?(:post_init)
          actor.post_init(*@args)
        end
        actor
      end

      def self.create
      end

    end
  end
end
