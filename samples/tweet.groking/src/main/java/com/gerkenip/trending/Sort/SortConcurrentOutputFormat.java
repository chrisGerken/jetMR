package com.gerkenip.trending.Sort;

import org.apache.hadoop.io.Text;

import com.gerkenip.hadoop.platform.*;
import com.gerkenip.writable.*;

public class SortConcurrentOutputFormat extends ConcurrentTextOutputFormat<Text, Text> {

	@Override
	public String getMultiOutputName() {
		return "Sort";
	}

}
