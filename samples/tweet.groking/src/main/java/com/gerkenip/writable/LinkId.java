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
public class LinkId implements Writable , WritableComparable {

  public static int	PROPERTY_LINK = 0;
   
  private boolean[] changeFlag = new boolean[1];
   
  private String	_link;

  public LinkId() {
    resetChangeFlags();
  }

  public LinkId(String _link) {
    resetChangeFlags();
	setLink(_link);  
  }

  public LinkId(LinkId original) {
    resetChangeFlags();
	setLink(original.getLink());  
  }
  
  public LinkId(byte[] bytes) {
	ByteArrayInputStream is = new ByteArrayInputStream(bytes);
	DataInput in = new DataInputStream(is);
	try { readFields(in); } catch (IOException e) { }
	resetChangeFlags();
  }
  

   
  public String getLink() {
    return _link;
  }
  
  public void setLink(String value) {
    _link = value;
    changeFlag[PROPERTY_LINK] = true;
  }  
  
  public void readFields(DataInput in) throws IOException {
		
			// Read String _link
		
		if (in.readBoolean()) {
			_link = Text.readString(in);
		    changeFlag[PROPERTY_LINK] = true;
		} else {
			_link = null;
		    changeFlag[PROPERTY_LINK] = false;
		}
  }
  
  public void write(DataOutput out) throws IOException {
		
			// Write String _link

	  if (_link == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_link);
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
    changeFlag[PROPERTY_LINK] = false;
  }
  
  public boolean getChangeFlag(int i) {
  	return changeFlag[i];
  }

  
   public byte[] getLinkAsBytes() throws IOException {
	  ByteArrayOutputStream os = new ByteArrayOutputStream();
	  DataOutputStream out = new DataOutputStream(os);
		
			// Write String _link

	  if (_link == null) {
	  		out.writeBoolean(false);
	  } else {
	  		out.writeBoolean(true);
			Text.writeString(out,_link);
	  }

	  out.flush();
	  out.close();
	  return os.toByteArray();
   }

  
   public void setLinkFromBytes(byte[] b) throws IOException {
	  ByteArrayInputStream is = new ByteArrayInputStream(b);
	  DataInput in = new DataInputStream(is);
	  int len;
		
			// Read String _link
		
		if (in.readBoolean()) {
			_link = Text.readString(in);
		    changeFlag[PROPERTY_LINK] = true;
		} else {
			_link = null;
		    changeFlag[PROPERTY_LINK] = false;
		}

   }
	
	public Tuple asTuple() throws ExecException {
		
        Tuple tuple = TupleFactory.getInstance().newTuple(1);

		if (getLink() == null) {
        	tuple.set(0, (String) null);
		} else {
        	tuple.set(0, getLink());
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
		
		if (obj instanceof LinkId) { 
	
			LinkId other = (LinkId) obj;
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
		fieldSchema[0].setName("link");
		fieldSchema[0].setType(DataType.CHARARRAY);
    	
    	schema.setFields(fieldSchema);
    	return schema;
    	
    }

	public static LinkId fromJson(String source) {
	
		LinkId obj = null;

		try {
			JSONObject jsonObj = new JSONObject(source);
			obj = fromJson(jsonObj);
		} catch (JSONException e) {
			System.out.println(e.toString());
		}

		return obj;	
	}

	public static LinkId fromJson(JSONObject jsonObj) {
	
		LinkId obj = new LinkId();

		try {

			if (jsonObj.has("link")) {
				obj.setLink(jsonObj.getString("link"));
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

			if (getLink() != null) {
				jsonObj.put("link", getLink());
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

        sb.append(String.valueOf(getLink()));

		return sb.toString();	
	}


}
