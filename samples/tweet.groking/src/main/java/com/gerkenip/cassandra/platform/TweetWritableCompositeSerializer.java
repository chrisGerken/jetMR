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

import me.prettyprint.cassandra.serializers.*;
import me.prettyprint.hector.api.beans.AbstractComposite.ComponentEquality;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.ddl.ComparatorType;

/**
 * Converts bytes to TweetWritable and back again
 * 
 */
public final class TweetWritableCompositeSerializer extends AbstractSerializer<TweetWritable> {

  private static final TweetWritableCompositeSerializer instance = new TweetWritableCompositeSerializer();

  public static TweetWritableCompositeSerializer get() {
    return instance;
  }

  @Override
  public ByteBuffer toByteBuffer(TweetWritable obj) {
    if (obj == null) {
      return null;
    }
	Composite composite = new Composite();
	composite.addComponent(0, obj.getHandle() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(1, obj.getContent() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(2, obj.getId() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(3, obj.getRetweets() , IntegerSerializer.get(), "IntegerType", ComponentEquality.EQUAL);
	composite.addComponent(4, obj.getIsRetweet() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(5, obj.getUrls() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(6, obj.getHashtags() , LongSerializer.get(), "LongType", ComponentEquality.EQUAL);
	composite.addComponent(7, obj.getTimestamp() , DateSerializer.get(), "DateType", ComponentEquality.EQUAL);
	ByteBuffer byteBuffer = new CompositeSerializer().toByteBuffer(composite);
	return byteBuffer;
  }

  @Override
  public TweetWritable fromByteBuffer(ByteBuffer byteBuffer) {
    if (byteBuffer == null) {
      return null;
    }
	TweetWritable obj = new TweetWritable();
	Composite decomposed = new CompositeSerializer().fromByteBuffer(TBaseHelper.rightSize(byteBuffer));
	obj.setHandle(decomposed.get(0, StringSerializer.get()));
	obj.setContent(decomposed.get(1, StringSerializer.get()));
	obj.setId(decomposed.get(2, StringSerializer.get()));
	obj.setRetweets(decomposed.get(3, IntegerSerializer.get()));
	obj.setIsRetweet(decomposed.get(4, BooleanSerializer.get()));
	obj.setUrls(decomposed.get(5, StringSerializer.get()));
	obj.setHashtags(decomposed.get(6, LongSerializer.get()));
	obj.setTimestamp(decomposed.get(7, DateSerializer.get()));
	return obj;
  }

  @Override
  public ComparatorType getComparatorType() {
    return BYTESTYPE;
  }

}
