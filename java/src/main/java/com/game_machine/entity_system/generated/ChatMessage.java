
package com.game_machine.entity_system.generated;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.List;

import com.dyuproject.protostuff.ByteString;
import com.dyuproject.protostuff.GraphIOUtil;
import com.dyuproject.protostuff.Input;
import com.dyuproject.protostuff.Message;
import com.dyuproject.protostuff.Output;

import java.io.ByteArrayOutputStream;
import com.dyuproject.protostuff.JsonIOUtil;
import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.game_machine.entity_system.generated.Entity;

import com.dyuproject.protostuff.Pipe;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.UninitializedMessageException;

public final class ChatMessage  implements Externalizable, Message<ChatMessage>, Schema<ChatMessage>
{




    public static Schema<ChatMessage> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static ChatMessage getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final ChatMessage DEFAULT_INSTANCE = new ChatMessage();



    public ChatChannel chatChannel;



    public String message;



    public String type;



    public String senderId;


    


    public ChatMessage()
    {
        
    }






    

	public ChatChannel getChatChannel() {
		return chatChannel;
	}
	
	public ChatMessage setChatChannel(ChatChannel chatChannel) {
		this.chatChannel = chatChannel;
		return this;
	}
	
	public Boolean hasChatChannel()  {
        return chatChannel == null ? false : true;
    }



    

	public String getMessage() {
		return message;
	}
	
	public ChatMessage setMessage(String message) {
		this.message = message;
		return this;
	}
	
	public Boolean hasMessage()  {
        return message == null ? false : true;
    }



    

	public String getType() {
		return type;
	}
	
	public ChatMessage setType(String type) {
		this.type = type;
		return this;
	}
	
	public Boolean hasType()  {
        return type == null ? false : true;
    }



    

	public String getSenderId() {
		return senderId;
	}
	
	public ChatMessage setSenderId(String senderId) {
		this.senderId = senderId;
		return this;
	}
	
	public Boolean hasSenderId()  {
        return senderId == null ? false : true;
    }



  
    // java serialization

    public void readExternal(ObjectInput in) throws IOException
    {
        GraphIOUtil.mergeDelimitedFrom(in, this, this);
    }

    public void writeExternal(ObjectOutput out) throws IOException
    {
        GraphIOUtil.writeDelimitedTo(out, this, this);
    }

    // message method

    public Schema<ChatMessage> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public ChatMessage newMessage()
    {
        return new ChatMessage();
    }

    public Class<ChatMessage> typeClass()
    {
        return ChatMessage.class;
    }

    public String messageName()
    {
        return ChatMessage.class.getSimpleName();
    }

    public String messageFullName()
    {
        return ChatMessage.class.getName();
    }

    public boolean isInitialized(ChatMessage message)
    {
        return true;
    }

    public void mergeFrom(Input input, ChatMessage message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;

            	case 1:


                	message.chatChannel = input.mergeObject(message.chatChannel, ChatChannel.getSchema());
                    break;

                	


            	case 2:


                	message.message = input.readString();
                	break;

                	


            	case 3:


                	message.type = input.readString();
                	break;

                	


            	case 4:


                	message.senderId = input.readString();
                	break;

                	


            
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, ChatMessage message) throws IOException
    {

    	

    	if(message.chatChannel == null)
            throw new UninitializedMessageException(message);

    	


    	if(message.chatChannel != null)
    		output.writeObject(1, message.chatChannel, ChatChannel.getSchema(), false);

    	


    	

    	if(message.message == null)
            throw new UninitializedMessageException(message);

    	


    	if(message.message != null)
            output.writeString(2, message.message, false);

    	


    	

    	if(message.type == null)
            throw new UninitializedMessageException(message);

    	


    	if(message.type != null)
            output.writeString(3, message.type, false);

    	


    	

    	


    	if(message.senderId != null)
            output.writeString(4, message.senderId, false);

    	


    	
    }

    public String getFieldName(int number)
    {
        switch(number)
        {

        	case 1: return "chatChannel";

        	case 2: return "message";

        	case 3: return "type";

        	case 4: return "senderId";

            default: return null;
        }
    }

    public int getFieldNumber(String name)
    {
        final Integer number = __fieldMap.get(name);
        return number == null ? 0 : number.intValue();
    }

    private static final java.util.HashMap<String,Integer> __fieldMap = new java.util.HashMap<String,Integer>();
    static
    {

    	__fieldMap.put("chatChannel", 1);

    	__fieldMap.put("message", 2);

    	__fieldMap.put("type", 3);

    	__fieldMap.put("senderId", 4);

    }
   
   public static List<String> getFields() {
	ArrayList<String> fieldNames = new ArrayList<String>();
	String fieldName = null;
	Integer i = 1;
	
    while(true) { 
		fieldName = ChatMessage.getSchema().getFieldName(i);
		if (fieldName == null) {
			break;
		}
		fieldNames.add(fieldName);
		i++;
	}
	return fieldNames;
}

public static ChatMessage parseFrom(byte[] bytes) {
	ChatMessage message = new ChatMessage();
	ProtobufIOUtil.mergeFrom(bytes, message, RuntimeSchema.getSchema(ChatMessage.class));
	return message;
}

public ChatMessage clone() {
	byte[] bytes = this.toByteArray();
	ChatMessage chatMessage = ChatMessage.parseFrom(bytes);
	return chatMessage;
}
	
public byte[] toByteArray() {
	return toProtobuf();
	//return toJson();
}

public byte[] toJson() {
	boolean numeric = false;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
		JsonIOUtil.writeTo(out, this, ChatMessage.getSchema(), numeric);
	} catch (IOException e) {
		e.printStackTrace();
		throw new RuntimeException("Json encoding failed");
	}
	return out.toByteArray();
}
		
public byte[] toProtobuf() {
	LinkedBuffer buffer = LinkedBuffer.allocate(8024);
	byte[] bytes = null;

	try {
		bytes = ProtobufIOUtil.toByteArray(this, RuntimeSchema.getSchema(ChatMessage.class), buffer);
		buffer.clear();
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Protobuf encoding failed");
	}
	return bytes;
}

}
