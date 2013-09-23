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
public class TweetWritable implements Writable , WritableComparable {

  public static int	PROPERTY_HANDLE = 0;
  public static int	PROPERTY_CONTENT = 1;
  public static int	PROPERTY_ID = 2;
  public static int	PROPERTY_RETWEETS = 3;
  public static int	PROPERTY_ISRETWEET = 4;
  public static int	PROPERTY_URLS = 5;
  public static int	PROPERTY_HASHTAGS = 6;
  public static int	PROPERTY_TIMESTAMP = 7;
   
  private boolean[] changeFlag = new boolean[8];
   
  private String	_handle;
  private String	_content;
  private String	_id;
  private int	_retweets;
  private boolean	_isRetweet;
  private String	_urls;
  private long	_hashtags;
  private Date	_timestamp;

  public TweetWritable() {
    resetChangeFlags();
  }

  public TweetWritable(String _handle, String _content, String _id, int _retweets, boolean _isRetweet, String _urls, long _hashtags, Date _timestamp) {
    resetChangeFlags();
	setHandle(_handle);  
	setContent(_content);  
	setId(_id);  
	setRetweets(_retweets);  
	setIsRetweet(_isRetweet);  
	setUrls(_urls);  
	setHashtags(_hashtags);  
	setTimestamp(_timestamp);  
  }

  public TweetWritable(TweetWritable original) {
    resetChangeFlags();
	setHandle(original.getHandle());  
	setContent(original.getContent());  
	setId(original.getId());  
	setRetweets(original.getRetweets());  
	setIsRetweet(original.getIsRetweet());  
	setUrls(original.getUrls());  
	setHashtags(original.getHashtags());  
	setTimestamp(original.getTimestamp());  
  }
  
  public TweetWritable(byte[] bytes) {
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
   
  public String getContent() {
    return _content;
  }
  
  public void setContent(String value) {
    _content = value;
    changeFlag[PROPERTY_CONTENT] = true;
  }  
   
  public String getId() {
    return _id;
  }
  
  public void setId(String value) {
    _id = value;
    changeFlag[PROPERTY_ID] = true;
  }  
   
  public int getRetweets() {
    return _retweets;
  }
  
  public void setRetweets(int value) {
    _retweets = value;
    changeFlag[PROPERTY_RETWEETS] = true;
  }  
   
  public boolean getIsRetweet() {
    return _isRetweet;
  }
  
  public void setIsRetweet(boolean value) {
    _isRetweet = value;
    changeFlag[PROPERTY_ISRETWEET] = true;
  }  
   
  public String getUrls() {
    return _urls;
  }
  
  public void setUrls(String value) {
    _urls = value;
    changeFlag[PROPERTY_URLS] = true;
  }  
   
  public long getHashtags() {
    return _hashtags;
  }
  
  public void setHashtags(long value) {
    _hashtags = value;
    changeFlag[PROPERTY_HASHTAGS] = true;
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
		
			// Read String _content
		
		if (in.readBoolean()) {
			_content = Text.readString(in);
		    changeFlag[PROPERTY_CONTENT] = true;
		} else {
			_content = null;
		    changeFlag[PROPERTY_CONTENT] = false;
		}
		
			// Read String _id
		
		if (in.readBoolean()) {
			_id = Text.readString(in);
		    changeFlag[PROPERTY_ID] = true;
		} else {
			_id = null;
		    changeFlag[PROPERTY_ID] = false;
		}
		
			// Read int _retweets
		
		_retweets = in.readInt();
	    changeFlag[PROPERTY_RETWEETS] = true;
				
			// Read boolean _isRetweet
		
		_isRetweet = in.readBoolean();
	    changeFlag[PROPERTY_ISRETWEET] = true;
				
			// Read String _urls
		
		if (in.readBoolean()) {
			_urls = Text.readString(in);
		    changeFlag[PROPERTY_URLS] = true;
		} else {
			_urls = null;
		    changeFlag[PROPERTY_URLS] = false;
		}
		
			// Read long _hashtags
		
		_hashtags = in.readLong();
	    changeFlag[PROPERTY_HASHTAGS] = true;
				
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
		
			// Write String _content

	  if (_content == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_content);
	  }
		
			// Write String _id

	  if (_id == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_id);
	  }
		
			// Write int _retweets
		
	  out.writeInt(_retweets);
		
			// Write boolean _isRetweet
		
	  out.writeBoolean(_isRetweet);
		
			// Write String _urls

	  if (_urls == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_urls);
	  }
		
			// Write long _hashtags
		
	  out.writeLong(_hashtags);
		
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
    changeFlag[PROPERTY_CONTENT] = false;
    changeFlag[PROPERTY_ID] = false;
    changeFlag[PROPERTY_RETWEETS] = false;
    changeFlag[PROPERTY_ISRETWEET] = false;
    changeFlag[PROPERTY_URLS] = false;
    changeFlag[PROPERTY_HASHTAGS] = false;
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
  
   public byte[] getContentAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _content

	  if (_content == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_content);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getIdAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _id

	  if (_id == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_id);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getRetweetsAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write int _retweets
		
	  out.writeInt(_retweets);

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getIsRetweetAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write boolean _isRetweet
		
	  out.writeBoolean(_isRetweet);

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getUrlsAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _urls

	  if (_urls == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_urls);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }
  
   public byte[] getHashtagsAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write long _hashtags
		
	  out.writeLong(_hashtags);

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
  
   public void setContentFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _content
		
		if (in.readBoolean()) {
			_content = Text.readString(in);
		    changeFlag[PROPERTY_CONTENT] = true;
		} else {
			_content = null;
		    changeFlag[PROPERTY_CONTENT] = false;
		}

   }
  
   public void setIdFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _id
		
		if (in.readBoolean()) {
			_id = Text.readString(in);
		    changeFlag[PROPERTY_ID] = true;
		} else {
			_id = null;
		    changeFlag[PROPERTY_ID] = false;
		}

   }
  
   public void setRetweetsFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read int _retweets
		
		_retweets = in.readInt();
	    changeFlag[PROPERTY_RETWEETS] = true;
		
   }
  
   public void setIsRetweetFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read boolean _isRetweet
		
		_isRetweet = in.readBoolean();
	    changeFlag[PROPERTY_ISRETWEET] = true;
		
   }
  
   public void setUrlsFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _urls
		
		if (in.readBoolean()) {
			_urls = Text.readString(in);
		    changeFlag[PROPERTY_URLS] = true;
		} else {
			_urls = null;
		    changeFlag[PROPERTY_URLS] = false;
		}

   }
  
   public void setHashtagsFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read long _hashtags
		
		_hashtags = in.readLong();
	    changeFlag[PROPERTY_HASHTAGS] = true;
		
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
		
        Tuple tuple = TupleFactory.getInstance().newTuple(8);

		if (getHandle() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getHandle());
		}
		if (getContent() == null) {
        	tuple.set(1, (String) null);
		} else {
        	tuple.set(1, getContent());
		}
		if (getId() == null) {
        	tuple.set(2, (String) null);
		} else {
        	tuple.set(2, getId());
		}
        tuple.set(3, new Integer(getRetweets()));
        tuple.set(4,(getIsRetweet() ? new Integer(1) : new Integer(0)));
		if (getUrls() == null) {
        	tuple.set(5, (String) null);
		} else {
        	tuple.set(5, getUrls());
		}
        tuple.set(6, new Long(getHashtags()));
		if (getTimestamp() == null) {
        	tuple.set(7, (Long) null);
		} else {
        	tuple.set(7, new Long(getTimestamp().getTime()));
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
		
		if (obj instanceof TweetWritable) { 
	
			TweetWritable other = (TweetWritable) obj;
			int test = 0;

	
			return 0;
		
		}
	
		return 0;
	
			// End comparable logic
	}

    public static ResourceSchema getPigSchema() throws IOException {

    	ResourceSchema schema = new ResourceSchema();
    	ResourceFieldSchema fieldSchema[] = new ResourceFieldSchema[8];
    	ResourceSchema bagSchema;
    	ResourceFieldSchema bagField[];

		fieldSchema[0] = new ResourceFieldSchema();
		fieldSchema[0].setName("handle");
		fieldSchema[0].setType(DataType.CHARARRAY);

		fieldSchema[1] = new ResourceFieldSchema();
		fieldSchema[1].setName("content");
		fieldSchema[1].setType(DataType.CHARARRAY);

		fieldSchema[2] = new ResourceFieldSchema();
		fieldSchema[2].setName("id");
		fieldSchema[2].setType(DataType.CHARARRAY);

		fieldSchema[3] = new ResourceFieldSchema();
		fieldSchema[3].setName("retweets");
		fieldSchema[3].setType(DataType.INTEGER);

		fieldSchema[4] = new ResourceFieldSchema();
		fieldSchema[4].setName("isRetweet");
		fieldSchema[4].setType(DataType.INTEGER);

		fieldSchema[5] = new ResourceFieldSchema();
		fieldSchema[5].setName("urls");
		fieldSchema[5].setType(DataType.CHARARRAY);

		fieldSchema[6] = new ResourceFieldSchema();
		fieldSchema[6].setName("hashtags");
		fieldSchema[6].setType(DataType.LONG);

		fieldSchema[7] = new ResourceFieldSchema();
		fieldSchema[7].setName("timestamp");
		fieldSchema[7].setType(DataType.LONG);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static TweetWritable fromJson(String source) {
	
		TweetWritable obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static TweetWritable fromJson(JSONObject jsonObj) {
	
		TweetWritable obj = new TweetWritable();

		try {

			if (jsonObj.has("handle")) {
				obj.setHandle(jsonObj.getString("handle"));
			}

			if (jsonObj.has("content")) {
				obj.setContent(jsonObj.getString("content"));
			}

			if (jsonObj.has("id")) {
				obj.setId(jsonObj.getString("id"));
			}

			if (jsonObj.has("retweets")) {
				obj.setRetweets(jsonObj.getInt("retweets"));
			}

			if (jsonObj.has("isRetweet")) {
				obj.setIsRetweet(jsonObj.getBoolean("isRetweet"));
			}

			if (jsonObj.has("urls")) {
				obj.setUrls(jsonObj.getString("urls"));
			}

			if (jsonObj.has("hashtags")) {
				obj.setHashtags(jsonObj.getLong("hashtags"));
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
			if (getContent() != null) {
				jsonObj.put("content", getContent());
			}
			if (getId() != null) {
				jsonObj.put("id", getId());
			}
			jsonObj.put("retweets", getRetweets());

			jsonObj.put("isRetweet", getIsRetweet());

			if (getUrls() != null) {
				jsonObj.put("urls", getUrls());
			}
			jsonObj.put("hashtags", getHashtags());

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
        sb.append(String.valueOf(getContent()));

        sb.append("\t");
        sb.append(String.valueOf(getId()));

        sb.append("\t");
        sb.append(String.valueOf(getRetweets()));

        sb.append("\t");
        sb.append(String.valueOf(getIsRetweet()));

        sb.append("\t");
        sb.append(String.valueOf(getUrls()));

        sb.append("\t");
        sb.append(String.valueOf(getHashtags()));

        sb.append("\t");
        sb.append(String.valueOf(getTimestamp()));

		return sb.toString();	
	}


}
