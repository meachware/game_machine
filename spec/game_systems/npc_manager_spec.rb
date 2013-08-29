require 'spec_helper'

module GameMachine
  module GameSystems
    describe NpcManager do

      subject do
        props = JavaLib::Props.new(NpcManager);
        ref = JavaLib::TestActorRef.create(Akka.instance.actor_system, props, 'npc_manager_test');
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

      let(:actor_ref) {double('Actor::Ref', :tell => true)}
      let(:actor_refs) {[actor_ref]}

      describe "#on_receive" do

        before(:each) do
          subject.stub(:schedule_update)
          subject.stub(:create_npc_routers).and_return(actor_refs)
          NpcRouter.stub(:find_distributed).and_return(actor_ref)
        end

        context "receives a CreateNpc message" do
          it "sends CreateNpc message to npc router" do
            expect(actor_ref).to receive(:tell).with(create_npc)
            subject.on_receive(create_npc)
          end
        end

        context "receives a NotifyNpc message" do
          it "sends NotifyNpc message to npc router" do
            expect(actor_ref).to receive(:tell).with(notify_npc)
            subject.on_receive(notify_npc)
          end
        end

        context "receives a DestroyNpc message" do
          it "sends DestroyNpc message to npc router" do
            expect(actor_ref).to receive(:tell).with(destroy_npc)
            subject.on_receive(destroy_npc)
          end
        end

        context "receives an update message" do
          it "sends an update message to npc router" do
            subject.post_init
            expect(actor_refs.first).to receive(:tell)
            subject.on_receive('update')
          end
        end

      end
    end
  end
end

