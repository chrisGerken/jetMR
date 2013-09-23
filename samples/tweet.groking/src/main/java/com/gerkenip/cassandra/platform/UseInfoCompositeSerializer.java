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

import com.gerkenip.writable.UseInfo;

import me.prettyprint.cassandra.serializers.*;
import me.prettyprint.hector.api.beans.AbstractComposite.ComponentEquality;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.ddl.ComparatorType;

/**
 * Converts bytes to UseInfo and back again
 * 
 */
public final class UseInfoCompositeSerializer extends AbstractSerializer<UseInfo> {

  private static final UseInfoCompositeSerializer instance = new UseInfoCompositeSerializer();

  public static UseInfoCompositeSerializer get() {
    return instance;
  }

  @Override
  public ByteBuffer toByteBuffer(UseInfo obj) {
    if (obj == null) {
      return null;
    }
	Composite composite = new Composite();
	composite.addComponent(0, obj.getHandle() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(1, obj.getTweetId() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	composite.addComponent(2, obj.getTimestamp() , DateSerializer.get(), "DateType", ComponentEquality.EQUAL);
	ByteBuffer byteBuffer = new CompositeSerializer().toByteBuffer(composite);
	return byteBuffer;
  }

  @Override
  public UseInfo fromByteBuffer(ByteBuffer byteBuffer) {
    if (byteBuffer == null) {
      return null;
    }
	UseInfo obj = new UseInfo();
	Composite decomposed = new CompositeSerializer().fromByteBuffer(TBaseHelper.rightSize(byteBuffer));
	obj.setHandle(decomposed.get(0, StringSerializer.get()));
	obj.setTweetId(decomposed.get(1, StringSerializer.get()));
	obj.setTimestamp(decomposed.get(2, DateSerializer.get()));
	return obj;
  }

  @Override
  public ComparatorType getComparatorType() {
    return BYTESTYPE;
  }

}
