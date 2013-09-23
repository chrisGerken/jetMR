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

import com.gerkenip.writable.HashtagId;

import me.prettyprint.cassandra.serializers.*;
import me.prettyprint.hector.api.beans.AbstractComposite.ComponentEquality;
import me.prettyprint.hector.api.beans.Composite;
import me.prettyprint.hector.api.ddl.ComparatorType;

/**
 * Converts bytes to HashtagId and back again
 * 
 */
public final class HashtagIdCompositeSerializer extends AbstractSerializer<HashtagId> {

  private static final HashtagIdCompositeSerializer instance = new HashtagIdCompositeSerializer();

  public static HashtagIdCompositeSerializer get() {
    return instance;
  }

  @Override
  public ByteBuffer toByteBuffer(HashtagId obj) {
    if (obj == null) {
      return null;
    }
	Composite composite = new Composite();
	composite.addComponent(0, obj.getHashtag() , StringSerializer.get(), "UTF8Type", ComponentEquality.EQUAL);
	ByteBuffer byteBuffer = new CompositeSerializer().toByteBuffer(composite);
	return byteBuffer;
  }

  @Override
  public HashtagId fromByteBuffer(ByteBuffer byteBuffer) {
    if (byteBuffer == null) {
      return null;
    }
	HashtagId obj = new HashtagId();
	Composite decomposed = new CompositeSerializer().fromByteBuffer(TBaseHelper.rightSize(byteBuffer));
	obj.setHashtag(decomposed.get(0, StringSerializer.get()));
	return obj;
  }

  @Override
  public ComparatorType getComparatorType() {
    return BYTESTYPE;
  }

}
