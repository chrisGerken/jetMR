package com.gerkenip.main.ReadTweets;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class WriteTermsConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<LinkId, LinkId> {

	@Override
	public String getMultiOutputName() {
		return "WriteTerms";
	}

}
