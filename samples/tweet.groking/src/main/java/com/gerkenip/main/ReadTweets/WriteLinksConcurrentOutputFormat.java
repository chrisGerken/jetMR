package com.gerkenip.main.ReadTweets;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class WriteLinksConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<TermId, TermId> {

	@Override
	public String getMultiOutputName() {
		return "WriteLinks";
	}

}
