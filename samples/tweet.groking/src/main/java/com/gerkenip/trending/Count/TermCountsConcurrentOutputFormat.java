package com.gerkenip.trending.Count;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class TermCountsConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<Text, Text> {

	@Override
	public String getMultiOutputName() {
		return "TermCounts";
	}

}
