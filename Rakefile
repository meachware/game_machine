

task :default => :build_proto

task :build_proto do
  system("rprotoc --proto_path java/game_machine/src/main/resources/ClientMessage.proto --out ruby/lib")
  system("protoc java/game_machine/src/main/resources/ClientMessage.proto --java_out=java/game_machine/src/main/java/com/game_machine/messages")
end
