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
public class InterestingJoinWritable implements Writable , WritableComparable {

  public static int	PROPERTY_ISFROMTWEET = 0;
  public static int	PROPERTY_TOKEN = 1;
  public static int	PROPERTY_WEIGHT = 2;
  public static int	PROPERTY_ISLINK = 3;
  public static int	PROPERTY_ISHASHTAG = 4;
  public static int	PROPERTY_ISTERM = 5;
  public static int	PROPERTY_HANDLE = 6;
  public static int	PROPERTY_TWEETID = 7;
  public static int	PROPERTY_TIMESTAMP = 8;
   
  private boolean[] changeFlag = new boolean[9];
   
  private boolean	_isFromTweet;
  private String	_token;
  private double	_weight;
  private boolean	_isLink;
  private boolean	_isHashtag;
  private boolean	_isTerm;
  private String	_handle;
  private String	_tweetId;
  private Date	_timestamp;

  public InterestingJoinWritable() {
    resetChangeFlags();
  }

  public InterestingJoinWritable(boolean _isFromTweet, String _token, double _weight, boolean _isLink, boolean _isHashtag, boolean _isTerm, String _handle, String _tweetId, Date _timestamp) {
    resetChangeFlags();
	setIsFromTweet(_isFromTweet);  
	setToken(_token);  
	setWeight(_weight);  
	setIsLink(_isLink);  
	setIsHashtag(_isHashtag);  
	setIsTerm(_isTerm);  
	setHandle(_handle);  
	setTweetId(_tweetId);  
	setTimestamp(_timestamp);  
  }

  public InterestingJoinWritable(InterestingJoinWritable original) {
    resetChangeFlags();
	setIsFromTweet(original.getIsFromTweet());  
	setToken(original.getToken());  
	setWeight(original.getWeight());  
	setIsLink(original.getIsLink());  
	setIsHashtag(original.getIsHashtag());  
	setIsTerm(original.getIsTerm());  
	setHandle(original.getHandle());  
	setTweetId(original.getTweetId());  
	setTimestamp(original.getTimestamp());  
  }
  
  public InterestingJoinWritable(byte[] bytes) {
	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
	DataInput in = new DataInputStream(is);
	try { readFields(in); } catch (IOException e) { }
	resetChangeFlags();
  }
  

   
  public boolean getIsFromTweet() {
    return _isFromTweet;
  }
  
  public void setIsFromTweet(boolean value) {
    _isFromTweet = value;
    changeFlag[PROPERTY_ISFROMTWEET] = true;
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
		
			// Read boolean _isFromTweet
		
		_isFromTweet = in.readBoolean();
	    changeFlag[PROPERTY_ISFROMTWEET] = true;
				
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
		
			// Write boolean _isFromTweet
		
	  out.writeBoolean(_isFromTweet);
		
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
    changeFlag[PROPERTY_ISFROMTWEET] = false;
    changeFlag[PROPERTY_TOKEN] = false;
    changeFlag[PROPERTY_WEIGHT] = false;
    changeFlag[PROPERTY_ISLINK] = false;
    changeFlag[PROPERTY_ISHASHTAG] = false;
    changeFlag[PROPERTY_ISTERM] = false;
    changeFlag[PROPERTY_HANDLE] = false;
    changeFlag[PROPERTY_TWEETID] = false;
    changeFlag[PROPERTY_TIMESTAMP] = false;
  }
  
  public boolean getChangeFlag(int i) {
  	return changeFlag[i];
  }

  
   public byte[] getIsFromTweetAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write boolean _isFromTweet
		
	  out.writeBoolean(_isFromTweet);

	  out.flush();
	  out.close();
	  return os.toByteArray();
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

  
   public void setIsFromTweetFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read boolean _isFromTweet
		
		_isFromTweet = in.readBoolean();
	    changeFlag[PROPERTY_ISFROMTWEET] = true;
		
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
		
        Tuple tuple = TupleFactory.getInstance().newTuple(9);

        tuple.set(0,(getIsFromTweet() ? new Integer(1) : new Integer(0)));
		if (getToken() == null) {
        	tuple.set(1, (String) null);
		} else {
        	tuple.set(1, getToken());
		}
        tuple.set(2, new Double(getWeight()));
        tuple.set(3,(getIsLink() ? new Integer(1) : new Integer(0)));
        tuple.set(4,(getIsHashtag() ? new Integer(1) : new Integer(0)));
        tuple.set(5,(getIsTerm() ? new Integer(1) : new Integer(0)));
		if (getHandle() == null) {
        	tuple.set(6, (String) null);
		} else {
        	tuple.set(6, getHandle());
		}
		if (getTweetId() == null) {
        	tuple.set(7, (String) null);
		} else {
        	tuple.set(7, getTweetId());
		}
		if (getTimestamp() == null) {
        	tuple.set(8, (Long) null);
		} else {
        	tuple.set(8, new Long(getTimestamp().getTime()));
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
		
		if (obj instanceof InterestingJoinWritable) { 
	
			InterestingJoinWritable other = (InterestingJoinWritable) obj;
			int test = 0;

	
			return 0;
		
		}
	
		return 0;
	
			// End comparable logic
	}

    public static ResourceSchema getPigSchema() throws IOException {

    	ResourceSchema schema = new ResourceSchema();
    	ResourceFieldSchema fieldSchema[] = new ResourceFieldSchema[9];
    	ResourceSchema bagSchema;
    	ResourceFieldSchema bagField[];

		fieldSchema[0] = new ResourceFieldSchema();
		fieldSchema[0].setName("isFromTweet");
		fieldSchema[0].setType(DataType.INTEGER);

		fieldSchema[1] = new ResourceFieldSchema();
		fieldSchema[1].setName("token");
		fieldSchema[1].setType(DataType.CHARARRAY);

		fieldSchema[2] = new ResourceFieldSchema();
		fieldSchema[2].setName("weight");
		fieldSchema[2].setType(DataType.DOUBLE);

		fieldSchema[3] = new ResourceFieldSchema();
		fieldSchema[3].setName("isLink");
		fieldSchema[3].setType(DataType.INTEGER);

		fieldSchema[4] = new ResourceFieldSchema();
		fieldSchema[4].setName("isHashtag");
		fieldSchema[4].setType(DataType.INTEGER);

		fieldSchema[5] = new ResourceFieldSchema();
		fieldSchema[5].setName("isTerm");
		fieldSchema[5].setType(DataType.INTEGER);

		fieldSchema[6] = new ResourceFieldSchema();
		fieldSchema[6].setName("handle");
		fieldSchema[6].setType(DataType.CHARARRAY);

		fieldSchema[7] = new ResourceFieldSchema();
		fieldSchema[7].setName("tweetId");
		fieldSchema[7].setType(DataType.CHARARRAY);

		fieldSchema[8] = new ResourceFieldSchema();
		fieldSchema[8].setName("timestamp");
		fieldSchema[8].setType(DataType.LONG);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static InterestingJoinWritable fromJson(String source) {
	
		InterestingJoinWritable obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static InterestingJoinWritable fromJson(JSONObject jsonObj) {
	
		InterestingJoinWritable obj = new InterestingJoinWritable();

		try {

			if (jsonObj.has("isFromTweet")) {
				obj.setIsFromTweet(jsonObj.getBoolean("isFromTweet"));
			}

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

			jsonObj.put("isFromTweet", getIsFromTweet());

			if (getToken() != null) {
				jsonObj.put("token", getToken());
			}
			jsonObj.put("weight", getWeight());

			jsonObj.put("isLink", getIsLink());

			jsonObj.put("isHashtag", getIsHashtag());

			jsonObj.put("isTerm", getIsTerm());

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

        sb.append(String.valueOf(getIsFromTweet()));

        sb.append("\t");
        sb.append(String.valueOf(getToken()));

        sb.append("\t");
        sb.append(String.valueOf(getWeight()));

        sb.append("\t");
        sb.append(String.valueOf(getIsLink()));

        sb.append("\t");
        sb.append(String.valueOf(getIsHashtag()));

        sb.append("\t");
        sb.append(String.valueOf(getIsTerm()));

        sb.append("\t");
        sb.append(String.valueOf(getHandle()));

        sb.append("\t");
        sb.append(String.valueOf(getTweetId()));

        sb.append("\t");
        sb.append(String.valueOf(getTimestamp()));

		return sb.toString();	
	}


}
