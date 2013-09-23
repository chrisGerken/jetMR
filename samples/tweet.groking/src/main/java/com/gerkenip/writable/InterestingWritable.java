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
public class InterestingWritable implements Writable , WritableComparable {

  public static int	PROPERTY_TOKEN = 0;
  public static int	PROPERTY_WEIGHT = 1;
  public static int	PROPERTY_ISLINK = 2;
  public static int	PROPERTY_ISHASHTAG = 3;
  public static int	PROPERTY_ISTERM = 4;
   
  private boolean[] changeFlag = new boolean[5];
   
  private String	_token;
  private double	_weight;
  private boolean	_isLink;
  private boolean	_isHashtag;
  private boolean	_isTerm;

  public InterestingWritable() {
    resetChangeFlags();
  }

  public InterestingWritable(String _token, double _weight, boolean _isLink, boolean _isHashtag, boolean _isTerm) {
    resetChangeFlags();
	setToken(_token);  
	setWeight(_weight);  
	setIsLink(_isLink);  
	setIsHashtag(_isHashtag);  
	setIsTerm(_isTerm);  
  }

  public InterestingWritable(InterestingWritable original) {
    resetChangeFlags();
	setToken(original.getToken());  
	setWeight(original.getWeight());  
	setIsLink(original.getIsLink());  
	setIsHashtag(original.getIsHashtag());  
	setIsTerm(original.getIsTerm());  
  }
  
  public InterestingWritable(byte[] bytes) {
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
   
  public boolean getIsLink() {
    return _isLink;
  }
  
  public void setIsLink(boolean value) {
    _isLink = value;
    changeFlag[PROPERTY_ISLINK] = true;
  }  
   
  public boolean getIsHashtag() {
    return _isHashtag;
  }
  
  public void setIsHashtag(boolean value) {
    _isHashtag = value;
    changeFlag[PROPERTY_ISHASHTAG] = true;
  }  
   
  public boolean getIsTerm() {
    return _isTerm;
  }
  
  public void setIsTerm(boolean value) {
    _isTerm = value;
    changeFlag[PROPERTY_ISTERM] = true;
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
				
			// Read boolean _isLink
		
		_isLink = in.readBoolean();
	    changeFlag[PROPERTY_ISLINK] = true;
				
			// Read boolean _isHashtag
		
		_isHashtag = in.readBoolean();
	    changeFlag[PROPERTY_ISHASHTAG] = true;
				
			// Read boolean _isTerm
		
		_isTerm = in.readBoolean();
	    changeFlag[PROPERTY_ISTERM] = true;
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
		
			// Write boolean _isLink
		
	  out.writeBoolean(_isLink);
		
			// Write boolean _isHashtag
		
	  out.writeBoolean(_isHashtag);
		
			// Write boolean _isTerm
		
	  out.writeBoolean(_isTerm);
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
    changeFlag[PROPERTY_ISLINK] = false;
    changeFlag[PROPERTY_ISHASHTAG] = false;
    changeFlag[PROPERTY_ISTERM] = false;
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
  
   public byte[] getIsLinkAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write boolean _isLink
		
	  out.writeBoolean(_isLink);

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getIsHashtagAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write boolean _isHashtag
		
	  out.writeBoolean(_isHashtag);

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getIsTermAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write boolean _isTerm
		
	  out.writeBoolean(_isTerm);

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
  
   public void setIsLinkFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read boolean _isLink
		
		_isLink = in.readBoolean();
	    changeFlag[PROPERTY_ISLINK] = true;
		
   }
  
   public void setIsHashtagFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read boolean _isHashtag
		
		_isHashtag = in.readBoolean();
	    changeFlag[PROPERTY_ISHASHTAG] = true;
		
   }
  
   public void setIsTermFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read boolean _isTerm
		
		_isTerm = in.readBoolean();
	    changeFlag[PROPERTY_ISTERM] = true;
		
   }
	
	public Tuple asTuple() throws ExecException {
		
        Tuple tuple = TupleFactory.getInstance().newTuple(5);

		if (getToken() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getToken());
		}
        tuple.set(1, new Double(getWeight()));
        tuple.set(2,(getIsLink() ? new Integer(1) : new Integer(0)));
        tuple.set(3,(getIsHashtag() ? new Integer(1) : new Integer(0)));
        tuple.set(4,(getIsTerm() ? new Integer(1) : new Integer(0)));

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
		
		if (obj instanceof InterestingWritable) { 
	
			InterestingWritable other = (InterestingWritable) obj;
			int test = 0;

	
			return 0;
		
		}
	
		return 0;
	
			// End comparable logic
	}

    public static ResourceSchema getPigSchema() throws IOException {

    	ResourceSchema schema = new ResourceSchema();
    	ResourceFieldSchema fieldSchema[] = new ResourceFieldSchema[5];
    	ResourceSchema bagSchema;
    	ResourceFieldSchema bagField[];

		fieldSchema[0] = new ResourceFieldSchema();
		fieldSchema[0].setName("token");
		fieldSchema[0].setType(DataType.CHARARRAY);

		fieldSchema[1] = new ResourceFieldSchema();
		fieldSchema[1].setName("weight");
		fieldSchema[1].setType(DataType.DOUBLE);

		fieldSchema[2] = new ResourceFieldSchema();
		fieldSchema[2].setName("isLink");
		fieldSchema[2].setType(DataType.INTEGER);

		fieldSchema[3] = new ResourceFieldSchema();
		fieldSchema[3].setName("isHashtag");
		fieldSchema[3].setType(DataType.INTEGER);

		fieldSchema[4] = new ResourceFieldSchema();
		fieldSchema[4].setName("isTerm");
		fieldSchema[4].setType(DataType.INTEGER);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static InterestingWritable fromJson(String source) {
	
		InterestingWritable obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static InterestingWritable fromJson(JSONObject jsonObj) {
	
		InterestingWritable obj = new InterestingWritable();

		try {

			if (jsonObj.has("token")) {
				obj.setToken(jsonObj.getString("token"));
			}

			if (jsonObj.has("weight")) {
				obj.setWeight(jsonObj.getDouble("weight"));
			}

			if (jsonObj.has("isLink")) {
				obj.setIsLink(jsonObj.getBoolean("isLink"));
			}

			if (jsonObj.has("isHashtag")) {
				obj.setIsHashtag(jsonObj.getBoolean("isHashtag"));
			}

			if (jsonObj.has("isTerm")) {
				obj.setIsTerm(jsonObj.getBoolean("isTerm"));
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

			jsonObj.put("isLink", getIsLink());

			jsonObj.put("isHashtag", getIsHashtag());

			jsonObj.put("isTerm", getIsTerm());

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

        sb.append("\t");
        sb.append(String.valueOf(getIsLink()));

        sb.append("\t");
        sb.append(String.valueOf(getIsHashtag()));

        sb.append("\t");
        sb.append(String.valueOf(getIsTerm()));

		return sb.toString();	
	}


}
