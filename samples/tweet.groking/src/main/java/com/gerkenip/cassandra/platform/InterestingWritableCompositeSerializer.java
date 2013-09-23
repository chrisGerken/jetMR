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

import com.gerkenip.writable.InterestingWritable;

import me.prettyprint.cassandra.serializers.*;
import me.prettyprint.hector.api.beans.AbstractComposite.ComponentEquality;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.ddl.ComparatorType;

/**
 * Converts bytes to InterestingWritable and back again
 * 
 */
public final class InterestingWritableCompositeSerializer extends AbstractSerializer<InterestingWritable> {

  private static final InterestingWritableCompositeSerializer instance = new InterestingWritableCompositeSerializer();

  public static InterestingWritableCompositeSerializer get() {
    return instance;
  }

  @Override
  public ByteBuffer toByteBuffer(InterestingWritable obj) {
    if (obj == null) {
      return null;
    }
	Composite composite = new Composite();
	composite.addComponent(0, obj.getToken() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(1, obj.getWeight() , DoubleSerializer.get(), "DoubleType", ComponentEquality.EQUAL);
	composite.addComponent(2, obj.getIsLink() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(3, obj.getIsHashtag() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(4, obj.getIsTerm() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	ByteBuffer byteBuffer = new CompositeSerializer().toByteBuffer(composite);
	return byteBuffer;
  }

  @Override
  public InterestingWritable fromByteBuffer(ByteBuffer byteBuffer) {
    if (byteBuffer == null) {
      return null;
    }
	InterestingWritable obj = new InterestingWritable();
	Composite decomposed = new CompositeSerializer().fromByteBuffer(TBaseHelper.rightSize(byteBuffer));
	obj.setToken(decomposed.get(0, StringSerializer.get()));
	obj.setWeight(decomposed.get(1, DoubleSerializer.get()));
	obj.setIsLink(decomposed.get(2, BooleanSerializer.get()));
	obj.setIsHashtag(decomposed.get(3, BooleanSerializer.get()));
	obj.setIsTerm(decomposed.get(4, BooleanSerializer.get()));
	return obj;
  }

  @Override
  public ComparatorType getComparatorType() {
    return BYTESTYPE;
  }

}
