package com.gerkenip.main.Join;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class WriteConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<Text, Text> {

	@Override
	public String getMultiOutputName() {
		return "Write";
	}

}
