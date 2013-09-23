package com.gerkenip.main.ReadTweets;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class WriteTweetsConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<Text, Text> {

	@Override
	public String getMultiOutputName() {
		return "WriteTweets";
	}

}
