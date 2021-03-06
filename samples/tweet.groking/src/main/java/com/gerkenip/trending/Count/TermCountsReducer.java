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
package com.gerkenip.trending.Count;

// Begin imports

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.SortedMap;
import java.util.logging.Logger;

import org.apache.cassandra.db.IColumn;
import org.apache.cassandra.utils.ByteBufferUtil;
import org.apache.cassandra.thrift.Column;
import org.apache.cassandra.thrift.ColumnOrSuperColumn;
import org.apache.cassandra.thrift.Mutation;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.RawKeyValueIterator;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.OutputCommitter;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.StatusReporter;
import org.apache.hadoop.mapreduce.TaskAttemptID;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;

import com.gerkenip.writable.*;

// End imports

public class TermCountsReducer extends Reducer<Text,LongWritable, Text, LongWritable> {

	// Begin declarations 

	private MultipleOutputs mos;
	
	private String count_output_termcounts;
	
	private long	counterTermCounts = 0;

	// End declarations 

	@Override
	protected void setup(Reducer<Text,LongWritable, Text, LongWritable>.Context context)
			throws java.io.IOException, InterruptedException {

		// Begin setup logic
		
		mos = new MultipleOutputs(context);

		Configuration conf = context.getConfiguration();

		count_output_termcounts = conf.get("Count.output.TermCounts");
		if (!count_output_termcounts.endsWith("/")) {
			count_output_termcounts = count_output_termcounts + "/";
		}
		count_output_termcounts = count_output_termcounts + "part";

		// End setup logic

	}

	protected void reduce(Text key, Iterable<LongWritable> values,
			Reducer<Text,LongWritable, Text, LongWritable>.Context context) throws java.io.IOException, InterruptedException {

		// Begin reduce logic 

		try {

			Text outKey;
			LongWritable outValue;		
			
			for (LongWritable value : values) {
				
//				context.write(outKey, outValue);
				
			}

		} catch (Exception e) {
			context.getCounter("Exception",e.getMessage()).increment(1);
		}
		// End reduce logic 
		
	}

	@Override
	protected void cleanup(org.apache.hadoop.mapreduce.Reducer.Context context)
			throws IOException, InterruptedException {

		// Begin cleanup logic

		mos.close();

		context.write(new Text("Trending\tO\tCount\tTermCounts\t"+counterTermCounts), new Text(""));

		// End cleanup logic
		
	}
	

	private void writeTermCounts(Text key, LongWritable value) throws IOException, InterruptedException {
	
		mos.write("TermCounts", key, value, count_output_termcounts);
		counterTermCounts++;
		
	}

	// Begin custom methods 

	// End custom methods

/*	public Reducer<Text,LongWritable, Text, LongWritable>.Context testContext(Configuration conf,TaskAttemptID taid,
			RawKeyValueIterator rkvi, Counter counter1, Counter counter2, RecordWriter<Text, LongWritable> rw, 
			OutputCommitter oc, StatusReporter sr, RawComparator<Text> rc, Class<Text> arg9, Class<LongWritable> arg10) 
				throws IOException, InterruptedException {
	
		return null;	
//		return new Context(conf,taid, rkvi, counter1, counter2, rw, oc, sr, rc, arg9, arg10);

	}
*/

}
