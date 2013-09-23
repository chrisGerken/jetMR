package com.gerkenip.main.ReadTweets;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class WriteHashtagsConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<HashtagId, HashtagId> {

	@Override
	public String getMultiOutputName() {
		return "WriteHashtags";
	}

}
