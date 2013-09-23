/*
 * 
 * This source code and information are provided "AS-IS" without 
 * warranty of any kind, either expressed or implied, including
 * but not limited to the implied warranties of merchantability
 * and/or fitness for a particular purpose.
 * 
 * This source code was generated using an evaluation copy 
 * of the Cassandra/Hadoop Accelerator and may not be used for
 * production purposes.
 *
 * For more information, contact Chris Gerken at
 * chris.gerken@gerkenip.com
 * 
 */
package com.gerkenip.writable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.*;
import com.eaio.uuid.UUID;
import org.apache.hadoop.io.*;
import org.apache.pig.ResourceSchema;
import org.apache.pig.ResourceSchema.ResourceFieldSchema;
import org.apache.pig.backend.executionengine.ExecException;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.DataType;
import org.apache.pig.data.DefaultDataBag;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.TupleFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressWarnings("rawtypes")
public class HashtagId implements Writable , WritableComparable {

  public static int	PROPERTY_HASHTAG = 0;
   
  private boolean[] changeFlag = new boolean[1];
   
  private String	_hashtag;

  public HashtagId() {
    resetChangeFlags();
  }

  public HashtagId(String _hashtag) {
    resetChangeFlags();
	setHashtag(_hashtag);  
  }

  public HashtagId(HashtagId original) {
    resetChangeFlags();
	setHashtag(original.getHashtag());  
  }
  
  public HashtagId(byte[] bytes) {
	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
	DataInput in = new DataInputStream(is);
	try { readFields(in); } catch (IOException e) { }
	resetChangeFlags();
  }
  

   
  public String getHashtag() {
    return _hashtag;
  }
  
  public void setHashtag(String value) {
    _hashtag = value;
    changeFlag[PROPERTY_HASHTAG] = true;
  }  
  
  public void readFields(DataInput in) throws IOException {
		
			// Read String _hashtag
		
		if (in.readBoolean()) {
			_hashtag = Text.readString(in);
		    changeFlag[PROPERTY_HASHTAG] = true;
		} else {
			_hashtag = null;
		    changeFlag[PROPERTY_HASHTAG] = false;
		}
  }
  
  public void write(DataOutput out) throws IOException {
		
			// Write String _hashtag

	  if (_hashtag == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_hashtag);
	  }
  }
  
  public byte[] getBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
	  write(out);
	  out.flush();
	  out.close();
	  return os.toByteArray();
  }

  public void resetChangeFlags() {
    changeFlag[PROPERTY_HASHTAG] = false;
  }
  
  public boolean getChangeFlag(int i) {
  	return changeFlag[i];
  }

  
   public byte[] getHashtagAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _hashtag

	  if (_hashtag == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_hashtag);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }

  
   public void setHashtagFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _hashtag
		
		if (in.readBoolean()) {
			_hashtag = Text.readString(in);
		    changeFlag[PROPERTY_HASHTAG] = true;
		} else {
			_hashtag = null;
		    changeFlag[PROPERTY_HASHTAG] = false;
		}

   }
	
	public Tuple asTuple() throws ExecException {
		
        Tuple tuple = TupleFactory.getInstance().newTuple(1);

		if (getHashtag() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getHashtag());
		}

        return tuple;
	}

	/*
	 * Returns:
	 *   a negative integer if this object is less than the other object,
	 *   zero if this object "is equal to" the other object, or
	 *   a positive integer if this object "is greater than" the other object. 
	 */ 
	public int compareTo(Object obj) {

			// Begin comparable logic
		
		if (obj instanceof HashtagId) { 
	
			HashtagId other = (HashtagId) obj;
			int test = 0;

	
			return 0;
		
		}
	
		return 0;
	
			// End comparable logic
	}

    public static ResourceSchema getPigSchema() throws IOException {

    	ResourceSchema schema = new ResourceSchema();
    	ResourceFieldSchema fieldSchema[] = new ResourceFieldSchema[1];
    	ResourceSchema bagSchema;
    	ResourceFieldSchema bagField[];

		fieldSchema[0] = new ResourceFieldSchema();
		fieldSchema[0].setName("hashtag");
		fieldSchema[0].setType(DataType.CHARARRAY);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static HashtagId fromJson(String source) {
	
		HashtagId obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static HashtagId fromJson(JSONObject jsonObj) {
	
		HashtagId obj = new HashtagId();

		try {

			if (jsonObj.has("hashtag")) {
				obj.setHashtag(jsonObj.getString("hashtag"));
			}

		} catch (JSONException e) {
			System.out.println(e.toString());
			obj = null;
		}

		return obj;	
	}

	public JSONObject toJson() {
	
		try {
			JSONObject jsonObj = new JSONObject();
			JSONArray jsonArray;

			if (getHashtag() != null) {
				jsonObj.put("hashtag", getHashtag());
			}
			return jsonObj;	
		} catch (JSONException e) { }

		return null;	
	}

	public String toJsonString() {
	
		return toJson().toString();	

	}

	public String toTSV() {
	
		StringBuffer sb = new StringBuffer();

        sb.append(String.valueOf(getHashtag()));

		return sb.toString();	
	}


}
