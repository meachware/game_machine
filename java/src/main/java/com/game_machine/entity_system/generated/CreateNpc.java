
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

public final class CreateNpc  implements Externalizable, Message<CreateNpc>, Schema<CreateNpc>
{




    public static Schema<CreateNpc> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static CreateNpc getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final CreateNpc DEFAULT_INSTANCE = new CreateNpc();



    public String npcId;



    public String controller;


    


    public CreateNpc()
    {
        
    }






    

	public String getNpcId() {
		return npcId;
	}
	
	public CreateNpc setNpcId(String npcId) {
		this.npcId = npcId;
		return this;
	}
	
	public Boolean hasNpcId()  {
        return npcId == null ? false : true;
    }



    

	public String getController() {
		return controller;
	}
	
	public CreateNpc setController(String controller) {
		this.controller = controller;
		return this;
	}
	
	public Boolean hasController()  {
        return controller == null ? false : true;
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

    public Schema<CreateNpc> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public CreateNpc newMessage()
    {
        return new CreateNpc();
    }

    public Class<CreateNpc> typeClass()
    {
        return CreateNpc.class;
    }

    public String messageName()
    {
        return CreateNpc.class.getSimpleName();
    }

    public String messageFullName()
    {
        return CreateNpc.class.getName();
    }

    public boolean isInitialized(CreateNpc message)
    {
        return true;
    }

    public void mergeFrom(Input input, CreateNpc message) throws IOException
    {
        for(int number = input.readFieldNumber(this);; number = input.readFieldNumber(this))
        {
            switch(number)
            {
                case 0:
                    return;

            	case 1:


                	message.npcId = input.readString();
                	break;

                	


            	case 2:


                	message.controller = input.readString();
                	break;

                	


            
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, CreateNpc message) throws IOException
    {

    	

    	if(message.npcId == null)
            throw new UninitializedMessageException(message);

    	


    	if(message.npcId != null)
            output.writeString(1, message.npcId, false);

    	


    	

    	if(message.controller == null)
            throw new UninitializedMessageException(message);

    	


    	if(message.controller != null)
            output.writeString(2, message.controller, false);

    	


    	
    }

    public String getFieldName(int number)
    {
        switch(number)
        {

        	case 1: return "npcId";

        	case 2: return "controller";

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

    	__fieldMap.put("npcId", 1);

    	__fieldMap.put("controller", 2);

    }
   
   public static List<String> getFields() {
	ArrayList<String> fieldNames = new ArrayList<String>();
	String fieldName = null;
	Integer i = 1;
	
    while(true) { 
		fieldName = CreateNpc.getSchema().getFieldName(i);
		if (fieldName == null) {
			break;
		}
		fieldNames.add(fieldName);
		i++;
	}
	return fieldNames;
}

public static CreateNpc parseFrom(byte[] bytes) {
	CreateNpc message = new CreateNpc();
	ProtobufIOUtil.mergeFrom(bytes, message, RuntimeSchema.getSchema(CreateNpc.class));
	return message;
}

public CreateNpc clone() {
	byte[] bytes = this.toByteArray();
	CreateNpc createNpc = CreateNpc.parseFrom(bytes);
	return createNpc;
}
	
public byte[] toByteArray() {
	return toProtobuf();
	//return toJson();
}

public byte[] toJson() {
	boolean numeric = false;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
		JsonIOUtil.writeTo(out, this, CreateNpc.getSchema(), numeric);
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
		bytes = ProtobufIOUtil.toByteArray(this, RuntimeSchema.getSchema(CreateNpc.class), buffer);
		buffer.clear();
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Protobuf encoding failed");
	}
	return bytes;
}

}
