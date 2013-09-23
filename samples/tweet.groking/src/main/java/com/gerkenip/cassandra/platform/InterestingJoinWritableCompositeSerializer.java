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

import com.gerkenip.writable.InterestingJoinWritable;

import me.prettyprint.cassandra.serializers.*;
import me.prettyprint.hector.api.beans.AbstractComposite.ComponentEquality;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.ddl.ComparatorType;

/**
 * Converts bytes to InterestingJoinWritable and back again
 * 
 */
public final class InterestingJoinWritableCompositeSerializer extends AbstractSerializer<InterestingJoinWritable> {

  private static final InterestingJoinWritableCompositeSerializer instance = new InterestingJoinWritableCompositeSerializer();

  public static InterestingJoinWritableCompositeSerializer get() {
    return instance;
  }

  @Override
  public ByteBuffer toByteBuffer(InterestingJoinWritable obj) {
    if (obj == null) {
      return null;
    }
	Composite composite = new Composite();
	composite.addComponent(0, obj.getIsFromTweet() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(1, obj.getToken() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(2, obj.getWeight() , DoubleSerializer.get(), "DoubleType", ComponentEquality.EQUAL);
	composite.addComponent(3, obj.getIsLink() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(4, obj.getIsHashtag() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(5, obj.getIsTerm() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(6, obj.getHandle() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(7, obj.getTweetId() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(8, obj.getTimestamp() , DateSerializer.get(), "DateType", ComponentEquality.EQUAL);
	ByteBuffer byteBuffer = new CompositeSerializer().toByteBuffer(composite);
	return byteBuffer;
  }

  @Override
  public InterestingJoinWritable fromByteBuffer(ByteBuffer byteBuffer) {
    if (byteBuffer == null) {
      return null;
    }
	InterestingJoinWritable obj = new InterestingJoinWritable();
	Composite decomposed = new CompositeSerializer().fromByteBuffer(TBaseHelper.rightSize(byteBuffer));
	obj.setIsFromTweet(decomposed.get(0, BooleanSerializer.get()));
	obj.setToken(decomposed.get(1, StringSerializer.get()));
	obj.setWeight(decomposed.get(2, DoubleSerializer.get()));
	obj.setIsLink(decomposed.get(3, BooleanSerializer.get()));
	obj.setIsHashtag(decomposed.get(4, BooleanSerializer.get()));
	obj.setIsTerm(decomposed.get(5, BooleanSerializer.get()));
	obj.setHandle(decomposed.get(6, StringSerializer.get()));
	obj.setTweetId(decomposed.get(7, StringSerializer.get()));
	obj.setTimestamp(decomposed.get(8, DateSerializer.get()));
	return obj;
  }

  @Override
  public ComparatorType getComparatorType() {
    return BYTESTYPE;
  }

}
