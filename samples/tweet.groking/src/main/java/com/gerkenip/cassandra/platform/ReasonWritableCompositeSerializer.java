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

import com.gerkenip.writable.ReasonWritable;

import me.prettyprint.cassandra.serializers.*;
import me.prettyprint.hector.api.beans.AbstractComposite.ComponentEquality;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.ddl.ComparatorType;

/**
 * Converts bytes to ReasonWritable and back again
 * 
 */
public final class ReasonWritableCompositeSerializer extends AbstractSerializer<ReasonWritable> {

  private static final ReasonWritableCompositeSerializer instance = new ReasonWritableCompositeSerializer();

  public static ReasonWritableCompositeSerializer get() {
    return instance;
  }

  @Override
  public ByteBuffer toByteBuffer(ReasonWritable obj) {
    if (obj == null) {
      return null;
    }
	Composite composite = new Composite();
	composite.addComponent(0, obj.getTerm() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(1, obj.getIsLink() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(2, obj.getIsHashtag() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	composite.addComponent(3, obj.getIsTerm() , BooleanSerializer.get(), "BooleanType", ComponentEquality.EQUAL);
	ByteBuffer byteBuffer = new CompositeSerializer().toByteBuffer(composite);
	return byteBuffer;
  }

  @Override
  public ReasonWritable fromByteBuffer(ByteBuffer byteBuffer) {
    if (byteBuffer == null) {
      return null;
    }
	ReasonWritable obj = new ReasonWritable();
	Composite decomposed = new CompositeSerializer().fromByteBuffer(TBaseHelper.rightSize(byteBuffer));
	obj.setTerm(decomposed.get(0, StringSerializer.get()));
	obj.setIsLink(decomposed.get(1, BooleanSerializer.get()));
	obj.setIsHashtag(decomposed.get(2, BooleanSerializer.get()));
	obj.setIsTerm(decomposed.get(3, BooleanSerializer.get()));
	return obj;
  }

  @Override
  public ComparatorType getComparatorType() {
    return BYTESTYPE;
  }

}
