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
public class UseInfo implements Writable , WritableComparable {

  public static int	PROPERTY_HANDLE = 0;
  public static int	PROPERTY_TWEETID = 1;
  public static int	PROPERTY_TIMESTAMP = 2;
   
  private boolean[] changeFlag = new boolean[3];
   
  private String	_handle;
  private String	_tweetId;
  private Date	_timestamp;

  public UseInfo() {
    resetChangeFlags();
  }

  public UseInfo(String _handle, String _tweetId, Date _timestamp) {
    resetChangeFlags();
	setHandle(_handle);  
	setTweetId(_tweetId);  
	setTimestamp(_timestamp);  
  }

  public UseInfo(UseInfo original) {
    resetChangeFlags();
	setHandle(original.getHandle());  
	setTweetId(original.getTweetId());  
	setTimestamp(original.getTimestamp());  
  }
  
  public UseInfo(byte[] bytes) {
	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
	DataInput in = new DataInputStream(is);
	try { readFields(in); } catch (IOException e) { }
	resetChangeFlags();
  }
  

   
  public String getHandle() {
    return _handle;
  }
  
  public void setHandle(String value) {
    _handle = value;
    changeFlag[PROPERTY_HANDLE] = true;
  }  
   
  public String getTweetId() {
    return _tweetId;
  }
  
  public void setTweetId(String value) {
    _tweetId = value;
    changeFlag[PROPERTY_TWEETID] = true;
  }  
   
  public Date getTimestamp() {
    return _timestamp;
  }
  
  public void setTimestamp(Date value) {
    _timestamp = value;
    changeFlag[PROPERTY_TIMESTAMP] = true;
  }  
  
  public void readFields(DataInput in) throws IOException {
		
			// Read String _handle
		
		if (in.readBoolean()) {
			_handle = Text.readString(in);
		    changeFlag[PROPERTY_HANDLE] = true;
		} else {
			_handle = null;
		    changeFlag[PROPERTY_HANDLE] = false;
		}
		
			// Read String _tweetId
		
		if (in.readBoolean()) {
			_tweetId = Text.readString(in);
		    changeFlag[PROPERTY_TWEETID] = true;
		} else {
			_tweetId = null;
		    changeFlag[PROPERTY_TWEETID] = false;
		}
		
			// Read Date _timestamp
		
		if (in.readBoolean()) {
			_timestamp = new Date(in.readLong());
		    changeFlag[PROPERTY_TIMESTAMP] = true;
		} else {
			_timestamp = null;
		    changeFlag[PROPERTY_TIMESTAMP] = false;
		}  }
  
  public void write(DataOutput out) throws IOException {
		
			// Write String _handle

	  if (_handle == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_handle);
	  }
		
			// Write String _tweetId

	  if (_tweetId == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_tweetId);
	  }
		
			// Write Date _timestamp

	  if (_timestamp == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
            out.writeLong(_timestamp.getTime());
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
    changeFlag[PROPERTY_HANDLE] = false;
    changeFlag[PROPERTY_TWEETID] = false;
    changeFlag[PROPERTY_TIMESTAMP] = false;
  }
  
  public boolean getChangeFlag(int i) {
  	return changeFlag[i];
  }

  
   public byte[] getHandleAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _handle

	  if (_handle == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_handle);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getTweetIdAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _tweetId

	  if (_tweetId == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_tweetId);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getTimestampAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write Date _timestamp

	  if (_timestamp == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
            out.writeLong(_timestamp.getTime());
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }

  
   public void setHandleFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _handle
		
		if (in.readBoolean()) {
			_handle = Text.readString(in);
		    changeFlag[PROPERTY_HANDLE] = true;
		} else {
			_handle = null;
		    changeFlag[PROPERTY_HANDLE] = false;
		}

   }
  
   public void setTweetIdFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _tweetId
		
		if (in.readBoolean()) {
			_tweetId = Text.readString(in);
		    changeFlag[PROPERTY_TWEETID] = true;
		} else {
			_tweetId = null;
		    changeFlag[PROPERTY_TWEETID] = false;
		}

   }
  
   public void setTimestampFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read Date _timestamp
		
		if (in.readBoolean()) {
			_timestamp = new Date(in.readLong());
		    changeFlag[PROPERTY_TIMESTAMP] = true;
		} else {
			_timestamp = null;
		    changeFlag[PROPERTY_TIMESTAMP] = false;
		}
   }
	
	public Tuple asTuple() throws ExecException {
		
        Tuple tuple = TupleFactory.getInstance().newTuple(3);

		if (getHandle() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getHandle());
		}
		if (getTweetId() == null) {
        	tuple.set(1, (String) null);
		} else {
        	tuple.set(1, getTweetId());
		}
		if (getTimestamp() == null) {
        	tuple.set(2, (Long) null);
		} else {
        	tuple.set(2, new Long(getTimestamp().getTime()));
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
		
		if (obj instanceof UseInfo) { 
	
			UseInfo other = (UseInfo) obj;
			int test = 0;

	
			return 0;
		
		}
	
		return 0;
	
			// End comparable logic
	}

    public static ResourceSchema getPigSchema() throws IOException {

    	ResourceSchema schema = new ResourceSchema();
    	ResourceFieldSchema fieldSchema[] = new ResourceFieldSchema[3];
    	ResourceSchema bagSchema;
    	ResourceFieldSchema bagField[];

		fieldSchema[0] = new ResourceFieldSchema();
		fieldSchema[0].setName("handle");
		fieldSchema[0].setType(DataType.CHARARRAY);

		fieldSchema[1] = new ResourceFieldSchema();
		fieldSchema[1].setName("tweetId");
		fieldSchema[1].setType(DataType.CHARARRAY);

		fieldSchema[2] = new ResourceFieldSchema();
		fieldSchema[2].setName("timestamp");
		fieldSchema[2].setType(DataType.LONG);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static UseInfo fromJson(String source) {
	
		UseInfo obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static UseInfo fromJson(JSONObject jsonObj) {
	
		UseInfo obj = new UseInfo();

		try {

			if (jsonObj.has("handle")) {
				obj.setHandle(jsonObj.getString("handle"));
			}

			if (jsonObj.has("tweetId")) {
				obj.setTweetId(jsonObj.getString("tweetId"));
			}

			if (jsonObj.has("timestamp")) {
				obj.setTimestamp(new Date(jsonObj.getLong("timestamp")));
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

			if (getHandle() != null) {
				jsonObj.put("handle", getHandle());
			}
			if (getTweetId() != null) {
				jsonObj.put("tweetId", getTweetId());
			}
			if (getTimestamp() != null) {
				jsonObj.put("timestamp", getTimestamp().getTime());
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

        sb.append(String.valueOf(getHandle()));

        sb.append("\t");
        sb.append(String.valueOf(getTweetId()));

        sb.append("\t");
        sb.append(String.valueOf(getTimestamp()));

		return sb.toString();	
	}


}
