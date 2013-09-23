package com.gerkenip.main.FilterByTerm;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class WriteConcurrentOutputFormat extends ConcurrentSequenceFileOutputFormat<ReasonWritable, ReasonWritable> {

	@Override
	public String getMultiOutputName() {
		return "Write";
	}

}
