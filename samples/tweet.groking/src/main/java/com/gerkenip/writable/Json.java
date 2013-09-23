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
public class Json implements Writable , WritableComparable {

  public static int	PROPERTY_JSONSTRING = 0;
   
  private boolean[] changeFlag = new boolean[1];
   
  private String	_jsonString;

  public Json() {
    resetChangeFlags();
  }

  public Json(String _jsonString) {
    resetChangeFlags();
	setJsonString(_jsonString);  
  }

  public Json(Json original) {
    resetChangeFlags();
	setJsonString(original.getJsonString());  
  }
  
  public Json(byte[] bytes) {
	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
	DataInput in = new DataInputStream(is);
	try { readFields(in); } catch (IOException e) { }
	resetChangeFlags();
  }
  

   
  public String getJsonString() {
    return _jsonString;
  }
  
  public void setJsonString(String value) {
    _jsonString = value;
    changeFlag[PROPERTY_JSONSTRING] = true;
  }  
  
  public void readFields(DataInput in) throws IOException {
		
			// Read String _jsonString
		
		if (in.readBoolean()) {
			_jsonString = Text.readString(in);
		    changeFlag[PROPERTY_JSONSTRING] = true;
		} else {
			_jsonString = null;
		    changeFlag[PROPERTY_JSONSTRING] = false;
		}
  }
  
  public void write(DataOutput out) throws IOException {
		
			// Write String _jsonString

	  if (_jsonString == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_jsonString);
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
    changeFlag[PROPERTY_JSONSTRING] = false;
  }
  
  public boolean getChangeFlag(int i) {
  	return changeFlag[i];
  }

  
   public byte[] getJsonStringAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _jsonString

	  if (_jsonString == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_jsonString);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }

  
   public void setJsonStringFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _jsonString
		
		if (in.readBoolean()) {
			_jsonString = Text.readString(in);
		    changeFlag[PROPERTY_JSONSTRING] = true;
		} else {
			_jsonString = null;
		    changeFlag[PROPERTY_JSONSTRING] = false;
		}

   }
	
	public Tuple asTuple() throws ExecException {
		
        Tuple tuple = TupleFactory.getInstance().newTuple(1);

		if (getJsonString() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getJsonString());
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
		
		if (obj instanceof Json) { 
	
			Json other = (Json) obj;
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
		fieldSchema[0].setName("jsonString");
		fieldSchema[0].setType(DataType.CHARARRAY);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static Json fromJson(String source) {
	
		Json obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static Json fromJson(JSONObject jsonObj) {
	
		Json obj = new Json();

		try {

			if (jsonObj.has("jsonString")) {
				obj.setJsonString(jsonObj.getString("jsonString"));
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

			if (getJsonString() != null) {
				jsonObj.put("jsonString", getJsonString());
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

        sb.append(String.valueOf(getJsonString()));

		return sb.toString();	
	}


}
