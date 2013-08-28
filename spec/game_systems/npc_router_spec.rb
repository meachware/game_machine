require 'spec_helper'

module GameMachine
  module GameSystems
    describe NpcRouter do

      subject do
        props = JavaLib::Props.new(NpcRouter);
        ref = JavaLib::TestActorRef.create(Akka.instance.actor_system, props, 'npc_router_test');
        ref.underlying_actor.post_init
        ref.underlying_actor
      end

      let(:create_npc) do
        Entity.new.set_create_npc(
          CreateNpc.new.set_npc_id('npc1').
          set_controller('GameMachine::GameSystems::NpcController')
        ).set_id('entity')
      end


      let(:notify_npc) do
        Entity.new.set_notify_npc(
          NotifyNpc.new.set_npc_id('npc1')
        ).set_id('entity')
      end

      let(:destroy_npc) do
        Entity.new.set_destroy_npc(
          DestroyNpc.new.set_npc_id('npc1')
        ).set_id('entity')
      end


      describe "#on_receive" do

        context "receives a CreateNpc message" do
          it "creates an npc controller" do
            expect(NpcController).to receive(:new).with(create_npc,anything)
            subject.on_receive(create_npc)
          end
        end

        context "receives a CreateNpc message with nonexistant controller class" do
          let(:create_bad_npc) do
            Entity.new.set_create_npc(
              CreateNpc.new.set_npc_id('npc1').
              set_controller('BadController')
            ).set_id('entity')
          end

          it "logs an error" do
            expect(GameMachine.logger).to receive(:error).
              with("CreateNpc error: NameError uninitialized constant BadController")
            subject.on_receive(create_bad_npc)
          end
        end

        context "receives a NotifyNpc message" do
          it "calls on_receive on the npc controller with message" do
            subject.on_receive(create_npc)
            expect_any_instance_of(NpcController).to receive(:on_receive).with(notify_npc)
            subject.on_receive(notify_npc)
          end
        end

        context "receives a NotifyNpc message after being destroyed" do
          it "logs an error message" do
            subject.on_receive(create_npc)
            subject.on_receive(destroy_npc)
            expect(GameMachine.logger).to receive(:error).
              with("Npc Controller for npc1 not found")
            subject.on_receive(notify_npc)
          end
        end

        context "receives a DestroyNpc message" do
          it "calls destroy on the npc controller with message" do
            subject.on_receive(create_npc)
            expect_any_instance_of(NpcController).to receive(:destroy).with(destroy_npc)
            subject.on_receive(destroy_npc)
          end
        end

        context "receives an update message" do
          it "calls update on the npc controller" do
            subject.on_receive(create_npc)
            expect_any_instance_of(NpcController).to receive(:update)
            subject.on_receive('update')
          end
        end
      end
    end
  end
end
