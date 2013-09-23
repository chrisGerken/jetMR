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
public class InterestingToken implements Writable , WritableComparable {

  public static int	PROPERTY_TOKEN = 0;
  public static int	PROPERTY_WEIGHT = 1;
   
  private boolean[] changeFlag = new boolean[2];
   
  private String	_token;
  private double	_weight;

  public InterestingToken() {
    resetChangeFlags();
  }

  public InterestingToken(String _token, double _weight) {
    resetChangeFlags();
	setToken(_token);  
	setWeight(_weight);  
  }

  public InterestingToken(InterestingToken original) {
    resetChangeFlags();
	setToken(original.getToken());  
	setWeight(original.getWeight());  
  }
  
  public InterestingToken(byte[] bytes) {
	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
	DataInput in = new DataInputStream(is);
	try { readFields(in); } catch (IOException e) { }
	resetChangeFlags();
  }
  

   
  public String getToken() {
    return _token;
  }
  
  public void setToken(String value) {
    _token = value;
    changeFlag[PROPERTY_TOKEN] = true;
  }  
   
  public double getWeight() {
    return _weight;
  }
  
  public void setWeight(double value) {
    _weight = value;
    changeFlag[PROPERTY_WEIGHT] = true;
  }  
  
  public void readFields(DataInput in) throws IOException {
		
			// Read String _token
		
		if (in.readBoolean()) {
			_token = Text.readString(in);
		    changeFlag[PROPERTY_TOKEN] = true;
		} else {
			_token = null;
		    changeFlag[PROPERTY_TOKEN] = false;
		}
		
			// Read double _weight
		
		_weight = in.readDouble();
	    changeFlag[PROPERTY_WEIGHT] = true;
		  }
  
  public void write(DataOutput out) throws IOException {
		
			// Write String _token

	  if (_token == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_token);
	  }
		
			// Write double _weight
		
	  out.writeDouble(_weight);
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
    changeFlag[PROPERTY_TOKEN] = false;
    changeFlag[PROPERTY_WEIGHT] = false;
  }
  
  public boolean getChangeFlag(int i) {
  	return changeFlag[i];
  }

  
   public byte[] getTokenAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _token

	  if (_token == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_token);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getWeightAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write double _weight
		
	  out.writeDouble(_weight);

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }

  
   public void setTokenFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _token
		
		if (in.readBoolean()) {
			_token = Text.readString(in);
		    changeFlag[PROPERTY_TOKEN] = true;
		} else {
			_token = null;
		    changeFlag[PROPERTY_TOKEN] = false;
		}

   }
  
   public void setWeightFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read double _weight
		
		_weight = in.readDouble();
	    changeFlag[PROPERTY_WEIGHT] = true;
		
   }
	
	public Tuple asTuple() throws ExecException {
		
        Tuple tuple = TupleFactory.getInstance().newTuple(2);

		if (getToken() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getToken());
		}
        tuple.set(1, new Double(getWeight()));

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
		
		if (obj instanceof InterestingToken) { 
	
			InterestingToken other = (InterestingToken) obj;
			int test = 0;

	
			return 0;
		
		}
	
		return 0;
	
			// End comparable logic
	}

    public static ResourceSchema getPigSchema() throws IOException {

    	ResourceSchema schema = new ResourceSchema();
    	ResourceFieldSchema fieldSchema[] = new ResourceFieldSchema[2];
    	ResourceSchema bagSchema;
    	ResourceFieldSchema bagField[];

		fieldSchema[0] = new ResourceFieldSchema();
		fieldSchema[0].setName("token");
		fieldSchema[0].setType(DataType.CHARARRAY);

		fieldSchema[1] = new ResourceFieldSchema();
		fieldSchema[1].setName("weight");
		fieldSchema[1].setType(DataType.DOUBLE);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static InterestingToken fromJson(String source) {
	
		InterestingToken obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static InterestingToken fromJson(JSONObject jsonObj) {
	
		InterestingToken obj = new InterestingToken();

		try {

			if (jsonObj.has("token")) {
				obj.setToken(jsonObj.getString("token"));
			}

			if (jsonObj.has("weight")) {
				obj.setWeight(jsonObj.getDouble("weight"));
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

			if (getToken() != null) {
				jsonObj.put("token", getToken());
			}
			jsonObj.put("weight", getWeight());

			return jsonObj;	
		} catch (JSONException e) { }

		return null;	
	}

	public String toJsonString() {
	
		return toJson().toString();	

	}

	public String toTSV() {
	
		StringBuffer sb = new StringBuffer();

        sb.append(String.valueOf(getToken()));

        sb.append("\t");
        sb.append(String.valueOf(getWeight()));

		return sb.toString();	
	}


}
