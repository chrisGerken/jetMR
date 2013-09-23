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

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.SequenceFileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.cassandra.hadoop.ColumnFamilyInputFormat;
import org.apache.cassandra.hadoop.ColumnFamilyOutputFormat;
import org.apache.cassandra.hadoop.ConfigHelper;
import org.apache.cassandra.thrift.SlicePredicate;
import org.apache.cassandra.thrift.SliceRange;
import org.apache.cassandra.utils.ByteBufferUtil;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.SortedMap;
import org.apache.cassandra.db.IColumn;

import com.gerkenip.writable.*;


public class ReadTweetsJob {
	
	public static void main(String[] args) {

		String readtweets_input_parse = "file:///home/";
		String readtweets_output_writeterms = "file:///home/";
		String readtweets_output_writelinks = "file:///home/";
		String readtweets_output_writehashtags = "file:///home/";
		String readtweets_output_writetweets = "file:///home/";

		String countBase = "file";

		Properties prop = new Properties();
		try {
			if (args.length > 0) {
				InputStream stream = new FileInputStream(args[0]);
				prop.loadFromXML(stream);
				readtweets_input_parse = prop.getProperty("ReadTweets.input.Parse",readtweets_input_parse);
				readtweets_output_writeterms = prop.getProperty("ReadTweets.output.WriteTerms",readtweets_output_writeterms);
				readtweets_output_writelinks = prop.getProperty("ReadTweets.output.WriteLinks",readtweets_output_writelinks);
				readtweets_output_writehashtags = prop.getProperty("ReadTweets.output.WriteHashtags",readtweets_output_writehashtags);
				readtweets_output_writetweets = prop.getProperty("ReadTweets.output.WriteTweets",readtweets_output_writetweets);

				countBase = prop.getProperty("count.base.dir",countBase);

				prop.list(System.out);
			}
		} catch (Exception e) {
			System.out.println("Properties file not found. Will use default properties instead.");
		}
		
		try {
			Configuration conf = new Configuration();

			conf.set("ReadTweets.input.Parse",readtweets_input_parse);
			conf.set("ReadTweets.output.WriteTerms",readtweets_output_writeterms);
			conf.set("ReadTweets.output.WriteLinks",readtweets_output_writelinks);
			conf.set("ReadTweets.output.WriteHashtags",readtweets_output_writehashtags);
			conf.set("ReadTweets.output.WriteTweets",readtweets_output_writetweets);

			conf.set("count.base.dir",countBase);

				// Begin custon config logic

				// End custon config logic

			Job job = new Job(conf, "com.gerkenip.main.ReadTweets.ReadTweetsJob : ");

			job.setJarByClass(ReadTweetsJob.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(TweetWritable.class);

				// Configure mapper for input Parse
			MultipleInputs.addInputPath(job, new Path(readtweets_input_parse), TextInputFormat.class, ParseMapper.class);

	        job.setReducerClass(WriteTermsReducer.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	        
	        Path outPath;
	        FileSystem dfs;


	        outPath = new Path(readtweets_output_writeterms);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteTermsConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "WriteTerms", WriteTermsConcurrentOutputFormat.class, LinkId.class, UseInfo.class);


	        outPath = new Path(readtweets_output_writelinks);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteLinksConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "WriteLinks", WriteLinksConcurrentOutputFormat.class, TermId.class, UseInfo.class);


	        outPath = new Path(readtweets_output_writehashtags);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteHashtagsConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "WriteHashtags", WriteHashtagsConcurrentOutputFormat.class, HashtagId.class, UseInfo.class);


	        outPath = new Path(readtweets_output_writetweets);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteTweetsConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "WriteTweets", WriteTweetsConcurrentOutputFormat.class, Text.class, TweetWritable.class);

	        
	        outPath = new Path(countBase+"/Main/ReadTweets");
			TextOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }

	        job.setNumReduceTasks(1);

			job.waitForCompletion(true);

		} catch (Exception e) {
			System.out.println(e.toString());
				// Handle Exception...
		} finally {

		}

	} 
}
