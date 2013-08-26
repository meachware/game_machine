
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

public final class DestroyNpc  implements Externalizable, Message<DestroyNpc>, Schema<DestroyNpc>
{




    public static Schema<DestroyNpc> getSchema()
    {
        return DEFAULT_INSTANCE;
    }

    public static DestroyNpc getDefaultInstance()
    {
        return DEFAULT_INSTANCE;
    }

    static final DestroyNpc DEFAULT_INSTANCE = new DestroyNpc();



    public String npcId;


    


    public DestroyNpc()
    {
        
    }






    

	public String getNpcId() {
		return npcId;
	}
	
	public DestroyNpc setNpcId(String npcId) {
		this.npcId = npcId;
		return this;
	}
	
	public Boolean hasNpcId()  {
        return npcId == null ? false : true;
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

    public Schema<DestroyNpc> cachedSchema()
    {
        return DEFAULT_INSTANCE;
    }

    // schema methods

    public DestroyNpc newMessage()
    {
        return new DestroyNpc();
    }

    public Class<DestroyNpc> typeClass()
    {
        return DestroyNpc.class;
    }

    public String messageName()
    {
        return DestroyNpc.class.getSimpleName();
    }

    public String messageFullName()
    {
        return DestroyNpc.class.getName();
    }

    public boolean isInitialized(DestroyNpc message)
    {
        return true;
    }

    public void mergeFrom(Input input, DestroyNpc message) throws IOException
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

                	


            
                default:
                    input.handleUnknownField(number, this);
            }   
        }
    }


    public void writeTo(Output output, DestroyNpc message) throws IOException
    {

    	

    	if(message.npcId == null)
            throw new UninitializedMessageException(message);

    	


    	if(message.npcId != null)
            output.writeString(1, message.npcId, false);

    	


    	
    }

    public String getFieldName(int number)
    {
        switch(number)
        {

        	case 1: return "npcId";

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

    }
   
   public static List<String> getFields() {
	ArrayList<String> fieldNames = new ArrayList<String>();
	String fieldName = null;
	Integer i = 1;
	
    while(true) { 
		fieldName = DestroyNpc.getSchema().getFieldName(i);
		if (fieldName == null) {
			break;
		}
		fieldNames.add(fieldName);
		i++;
	}
	return fieldNames;
}

public static DestroyNpc parseFrom(byte[] bytes) {
	DestroyNpc message = new DestroyNpc();
	ProtobufIOUtil.mergeFrom(bytes, message, RuntimeSchema.getSchema(DestroyNpc.class));
	return message;
}

public DestroyNpc clone() {
	byte[] bytes = this.toByteArray();
	DestroyNpc destroyNpc = DestroyNpc.parseFrom(bytes);
	return destroyNpc;
}
	
public byte[] toByteArray() {
	return toProtobuf();
	//return toJson();
}

public byte[] toJson() {
	boolean numeric = false;
	ByteArrayOutputStream out = new ByteArrayOutputStream();
	try {
		JsonIOUtil.writeTo(out, this, DestroyNpc.getSchema(), numeric);
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
		bytes = ProtobufIOUtil.toByteArray(this, RuntimeSchema.getSchema(DestroyNpc.class), buffer);
		buffer.clear();
	} catch (Exception e) {
		e.printStackTrace();
		throw new RuntimeException("Protobuf encoding failed");
	}
	return bytes;
}

}
