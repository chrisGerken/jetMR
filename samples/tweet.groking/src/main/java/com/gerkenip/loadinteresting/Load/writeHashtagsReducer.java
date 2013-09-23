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
package com.gerkenip.loadinteresting.Load;

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

public class writeHashtagsReducer extends Reducer<Text,InterestingWritable, Text, InterestingToken> {

	// Begin declarations 

	private MultipleOutputs mos;
	
	private String load_output_writelinks;
	private String load_output_writeterms;
	private String load_output_writehashtags;
	
	private long	counterwriteLinks = 0;
	private long	counterwriteTerms = 0;
	private long	counterwriteHashtags = 0;

	// End declarations 

	@Override
	protected void setup(Reducer<Text,InterestingWritable, Text, InterestingToken>.Context context)
			throws java.io.IOException, InterruptedException {

		// Begin setup logic
		
		mos = new MultipleOutputs(context);

		Configuration conf = context.getConfiguration();

		load_output_writelinks = conf.get("Load.output.writeLinks");
		if (!load_output_writelinks.endsWith("/")) {
			load_output_writelinks = load_output_writelinks + "/";
		}
		load_output_writelinks = load_output_writelinks + "part";
		load_output_writeterms = conf.get("Load.output.writeTerms");
		if (!load_output_writeterms.endsWith("/")) {
			load_output_writeterms = load_output_writeterms + "/";
		}
		load_output_writeterms = load_output_writeterms + "part";
		load_output_writehashtags = conf.get("Load.output.writeHashtags");
		if (!load_output_writehashtags.endsWith("/")) {
			load_output_writehashtags = load_output_writehashtags + "/";
		}
		load_output_writehashtags = load_output_writehashtags + "part";

		// End setup logic

	}

	protected void reduce(Text key, Iterable<InterestingWritable> values,
			Reducer<Text,InterestingWritable, Text, InterestingToken>.Context context) throws java.io.IOException, InterruptedException {

		// Begin reduce logic 

		try {

			Text outKey;
			InterestingToken outValue;		
			
			for (InterestingWritable value : values) {
				
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

		context.write(new Text("LoadInteresting\tO\tLoad\twriteLinks\t"+counterwriteLinks), new Text(""));
		context.write(new Text("LoadInteresting\tO\tLoad\twriteTerms\t"+counterwriteTerms), new Text(""));
		context.write(new Text("LoadInteresting\tO\tLoad\twriteHashtags\t"+counterwriteHashtags), new Text(""));

		// End cleanup logic
		
	}
	

	private void writewriteLinks(Text key, InterestingToken value) throws IOException, InterruptedException {
	
		mos.write("writeLinks", key, value, load_output_writelinks);
		counterwriteLinks++;
		
	}

	private void writewriteTerms(Text key, InterestingToken value) throws IOException, InterruptedException {
	
		mos.write("writeTerms", key, value, load_output_writeterms);
		counterwriteTerms++;
		
	}

	private void writewriteHashtags(Text key, InterestingToken value) throws IOException, InterruptedException {
	
		mos.write("writeHashtags", key, value, load_output_writehashtags);
		counterwriteHashtags++;
		
	}

	// Begin custom methods 

	// End custom methods

/*	public Reducer<Text,InterestingWritable, Text, InterestingToken>.Context testContext(Configuration conf,TaskAttemptID taid,
			RawKeyValueIterator rkvi, Counter counter1, Counter counter2, RecordWriter<Text, InterestingToken> rw, 
			OutputCommitter oc, StatusReporter sr, RawComparator<Text> rc, Class<Text> arg9, Class<InterestingWritable> arg10) 
				throws IOException, InterruptedException {
	
		return null;	
//		return new Context(conf,taid, rkvi, counter1, counter2, rw, oc, sr, rc, arg9, arg10);

	}
*/

}
