package com.gerkenip.loadinteresting.Load;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class WriteTermsConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<Text, Text> {

	@Override
	public String getMultiOutputName() {
		return "writeTerms";
	}

}
