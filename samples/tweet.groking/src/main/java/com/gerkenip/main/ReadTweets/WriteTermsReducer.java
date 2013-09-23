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
package com.gerkenip.main.ReadTweets;

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

public class WriteTermsReducer extends Reducer<Text,TweetWritable, LinkId, UseInfo> {

	// Begin declarations 

	private MultipleOutputs mos;
	
	private String readtweets_output_writeterms;
	private String readtweets_output_writelinks;
	private String readtweets_output_writehashtags;
	private String readtweets_output_writetweets;
	
	private long	counterWriteTerms = 0;
	private long	counterWriteLinks = 0;
	private long	counterWriteHashtags = 0;
	private long	counterWriteTweets = 0;

	// End declarations 

	@Override
	protected void setup(Reducer<Text,TweetWritable, LinkId, UseInfo>.Context context)
			throws java.io.IOException, InterruptedException {

		// Begin setup logic
		
		mos = new MultipleOutputs(context);

		Configuration conf = context.getConfiguration();

		readtweets_output_writeterms = conf.get("ReadTweets.output.WriteTerms");
		if (!readtweets_output_writeterms.endsWith("/")) {
			readtweets_output_writeterms = readtweets_output_writeterms + "/";
		}
		readtweets_output_writeterms = readtweets_output_writeterms + "part";
		readtweets_output_writelinks = conf.get("ReadTweets.output.WriteLinks");
		if (!readtweets_output_writelinks.endsWith("/")) {
			readtweets_output_writelinks = readtweets_output_writelinks + "/";
		}
		readtweets_output_writelinks = readtweets_output_writelinks + "part";
		readtweets_output_writehashtags = conf.get("ReadTweets.output.WriteHashtags");
		if (!readtweets_output_writehashtags.endsWith("/")) {
			readtweets_output_writehashtags = readtweets_output_writehashtags + "/";
		}
		readtweets_output_writehashtags = readtweets_output_writehashtags + "part";
		readtweets_output_writetweets = conf.get("ReadTweets.output.WriteTweets");
		if (!readtweets_output_writetweets.endsWith("/")) {
			readtweets_output_writetweets = readtweets_output_writetweets + "/";
		}
		readtweets_output_writetweets = readtweets_output_writetweets + "part";

		// End setup logic

	}

	protected void reduce(Text key, Iterable<TweetWritable> values,
			Reducer<Text,TweetWritable, LinkId, UseInfo>.Context context) throws java.io.IOException, InterruptedException {

		// Begin reduce logic 

		try {

			LinkId outKey;
			UseInfo outValue;		
			
			for (TweetWritable value : values) {
				
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

		context.write(new Text("Main\tO\tReadTweets\tWriteTerms\t"+counterWriteTerms), new Text(""));
		context.write(new Text("Main\tO\tReadTweets\tWriteLinks\t"+counterWriteLinks), new Text(""));
		context.write(new Text("Main\tO\tReadTweets\tWriteHashtags\t"+counterWriteHashtags), new Text(""));
		context.write(new Text("Main\tO\tReadTweets\tWriteTweets\t"+counterWriteTweets), new Text(""));

		// End cleanup logic
		
	}
	

	private void writeWriteTerms(LinkId key, UseInfo value) throws IOException, InterruptedException {
	
		mos.write("WriteTerms", key, value, readtweets_output_writeterms);
		counterWriteTerms++;
		
	}

	private void writeWriteLinks(TermId key, UseInfo value) throws IOException, InterruptedException {
	
		mos.write("WriteLinks", key, value, readtweets_output_writelinks);
		counterWriteLinks++;
		
	}

	private void writeWriteHashtags(HashtagId key, UseInfo value) throws IOException, InterruptedException {
	
		mos.write("WriteHashtags", key, value, readtweets_output_writehashtags);
		counterWriteHashtags++;
		
	}

	private void writeWriteTweets(Text key, TweetWritable value) throws IOException, InterruptedException {
	
		mos.write("WriteTweets", key, value, readtweets_output_writetweets);
		counterWriteTweets++;
		
	}

	// Begin custom methods 

	// End custom methods

/*	public Reducer<Text,TweetWritable, LinkId, UseInfo>.Context testContext(Configuration conf,TaskAttemptID taid,
			RawKeyValueIterator rkvi, Counter counter1, Counter counter2, RecordWriter<LinkId, UseInfo> rw, 
			OutputCommitter oc, StatusReporter sr, RawComparator<Text> rc, Class<Text> arg9, Class<TweetWritable> arg10) 
				throws IOException, InterruptedException {
	
		return null;	
//		return new Context(conf,taid, rkvi, counter1, counter2, rw, oc, sr, rc, arg9, arg10);

	}
*/

}
