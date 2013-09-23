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
package com.gerkenip.cassandra.platform;

import static me.prettyprint.hector.api.ddl.ComparatorType.BYTESTYPE;

import java.nio.ByteBuffer;

import org.apache.thrift.TBaseHelper;

import com.gerkenip.writable.TweetWritable;

import me.prettyprint.cassandra.serializers.AbstractSerializer;
import me.prettyprint.hector.api.ddl.ComparatorType;

/**
 * Converts bytes to TweetWritable and back again
 * 
 */
public final class TweetWritableSerializer extends AbstractSerializer<TweetWritable> {

  private static final TweetWritableSerializer instance = new TweetWritableSerializer();

  public static TweetWritableSerializer get() {
    return instance;
  }

  @Override
  public ByteBuffer toByteBuffer(TweetWritable obj) {
    if (obj == null) {
      return null;
    }
    try { 
    	return ByteBuffer.wrap(obj.getBytes());
    } catch (Exception e) {
    	return null;
    }
  }

  @Override
  public TweetWritable fromByteBuffer(ByteBuffer byteBuffer) {
    if ((byteBuffer == null) || (byteBuffer.remaining() < 8)) {
      return null;
    }
    return new TweetWritable(TBaseHelper.rightSize(byteBuffer).array());
  }

  @Override
  public ComparatorType getComparatorType() {
    return BYTESTYPE;
  }

}
