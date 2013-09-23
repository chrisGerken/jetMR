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
public class ReasonWritable implements Writable , WritableComparable {

  public static int	PROPERTY_TERM = 0;
  public static int	PROPERTY_ISLINK = 1;
  public static int	PROPERTY_ISHASHTAG = 2;
  public static int	PROPERTY_ISTERM = 3;
   
  private boolean[] changeFlag = new boolean[4];
   
  private String	_term;
  private boolean	_isLink;
  private boolean	_isHashtag;
  private boolean	_isTerm;

  public ReasonWritable() {
    resetChangeFlags();
  }

  public ReasonWritable(String _term, boolean _isLink, boolean _isHashtag, boolean _isTerm) {
    resetChangeFlags();
	setTerm(_term);  
	setIsLink(_isLink);  
	setIsHashtag(_isHashtag);  
	setIsTerm(_isTerm);  
  }

  public ReasonWritable(ReasonWritable original) {
    resetChangeFlags();
	setTerm(original.getTerm());  
	setIsLink(original.getIsLink());  
	setIsHashtag(original.getIsHashtag());  
	setIsTerm(original.getIsTerm());  
  }
  
  public ReasonWritable(byte[] bytes) {
	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
	DataInput in = new DataInputStream(is);
	try { readFields(in); } catch (IOException e) { }
	resetChangeFlags();
  }
  

   
  public String getTerm() {
    return _term;
  }
  
  public void setTerm(String value) {
    _term = value;
    changeFlag[PROPERTY_TERM] = true;
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
		
			// Read String _term
		
		if (in.readBoolean()) {
			_term = Text.readString(in);
		    changeFlag[PROPERTY_TERM] = true;
		} else {
			_term = null;
		    changeFlag[PROPERTY_TERM] = false;
		}
		
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
		
			// Write String _term

	  if (_term == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_term);
	  }
		
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
    changeFlag[PROPERTY_TERM] = false;
    changeFlag[PROPERTY_ISLINK] = false;
    changeFlag[PROPERTY_ISHASHTAG] = false;
    changeFlag[PROPERTY_ISTERM] = false;
  }
  
  public boolean getChangeFlag(int i) {
  	return changeFlag[i];
  }

  
   public byte[] getTermAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _term

	  if (_term == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_term);
	  }

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

  
   public void setTermFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _term
		
		if (in.readBoolean()) {
			_term = Text.readString(in);
		    changeFlag[PROPERTY_TERM] = true;
		} else {
			_term = null;
		    changeFlag[PROPERTY_TERM] = false;
		}

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
		
        Tuple tuple = TupleFactory.getInstance().newTuple(4);

		if (getTerm() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getTerm());
		}
        tuple.set(1,(getIsLink() ? new Integer(1) : new Integer(0)));
        tuple.set(2,(getIsHashtag() ? new Integer(1) : new Integer(0)));
        tuple.set(3,(getIsTerm() ? new Integer(1) : new Integer(0)));

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
		
		if (obj instanceof ReasonWritable) { 
	
			ReasonWritable other = (ReasonWritable) obj;
			int test = 0;

	
			return 0;
		
		}
	
		return 0;
	
			// End comparable logic
	}

    public static ResourceSchema getPigSchema() throws IOException {

    	ResourceSchema schema = new ResourceSchema();
    	ResourceFieldSchema fieldSchema[] = new ResourceFieldSchema[4];
    	ResourceSchema bagSchema;
    	ResourceFieldSchema bagField[];

		fieldSchema[0] = new ResourceFieldSchema();
		fieldSchema[0].setName("term");
		fieldSchema[0].setType(DataType.CHARARRAY);

		fieldSchema[1] = new ResourceFieldSchema();
		fieldSchema[1].setName("isLink");
		fieldSchema[1].setType(DataType.INTEGER);

		fieldSchema[2] = new ResourceFieldSchema();
		fieldSchema[2].setName("isHashtag");
		fieldSchema[2].setType(DataType.INTEGER);

		fieldSchema[3] = new ResourceFieldSchema();
		fieldSchema[3].setName("isTerm");
		fieldSchema[3].setType(DataType.INTEGER);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static ReasonWritable fromJson(String source) {
	
		ReasonWritable obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static ReasonWritable fromJson(JSONObject jsonObj) {
	
		ReasonWritable obj = new ReasonWritable();

		try {

			if (jsonObj.has("term")) {
				obj.setTerm(jsonObj.getString("term"));
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

			if (getTerm() != null) {
				jsonObj.put("term", getTerm());
			}
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

        sb.append(String.valueOf(getTerm()));

        sb.append("\t");
        sb.append(String.valueOf(getIsLink()));

        sb.append("\t");
        sb.append(String.valueOf(getIsHashtag()));

        sb.append("\t");
        sb.append(String.valueOf(getIsTerm()));

		return sb.toString();	
	}


}
