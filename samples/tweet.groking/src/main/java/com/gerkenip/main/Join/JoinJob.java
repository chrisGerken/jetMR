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
package com.gerkenip.main.Join;

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


public class JoinJob {
	
	public static void main(String[] args) {

		String join_input_links = "file:///home/";
		String join_input_hashtags = "file:///home/";
		String join_input_terms = "file:///home/";
		String join_input_tweets = "file:///home/";
		String join_output_write = "file:///home/";

		String countBase = "file";

		Properties prop = new Properties();
		try {
			if (args.length > 0) {
				InputStream stream = new FileInputStream(args[0]);
				prop.loadFromXML(stream);
				join_input_links = prop.getProperty("Join.input.Links",join_input_links);
				join_input_hashtags = prop.getProperty("Join.input.Hashtags",join_input_hashtags);
				join_input_terms = prop.getProperty("Join.input.Terms",join_input_terms);
				join_input_tweets = prop.getProperty("Join.input.Tweets",join_input_tweets);
				join_output_write = prop.getProperty("Join.output.Write",join_output_write);

				countBase = prop.getProperty("count.base.dir",countBase);

				prop.list(System.out);
			}
		} catch (Exception e) {
			System.out.println("Properties file not found. Will use default properties instead.");
		}
		
		try {
			Configuration conf = new Configuration();

			conf.set("Join.input.Links",join_input_links);
			conf.set("Join.input.Hashtags",join_input_hashtags);
			conf.set("Join.input.Terms",join_input_terms);
			conf.set("Join.input.Tweets",join_input_tweets);
			conf.set("Join.output.Write",join_output_write);

			conf.set("count.base.dir",countBase);

				// Begin custon config logic

				// End custon config logic

			Job job = new Job(conf, "com.gerkenip.main.Join.JoinJob : ");

			job.setJarByClass(JoinJob.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(InterestingJoinWritable.class);

				// Configure mapper for input Links
			MultipleInputs.addInputPath(job, new Path(join_input_links), SequenceFileInputFormat.class, LinksMapper.class);


				// Configure mapper for input Hashtags
			MultipleInputs.addInputPath(job, new Path(join_input_hashtags), SequenceFileInputFormat.class, HashtagsMapper.class);


				// Configure mapper for input Terms
			MultipleInputs.addInputPath(job, new Path(join_input_terms), SequenceFileInputFormat.class, TermsMapper.class);


				// Configure mapper for input Tweets
			MultipleInputs.addInputPath(job, new Path(join_input_tweets), SequenceFileInputFormat.class, TweetsMapper.class);

	        job.setReducerClass(WriteReducer.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	        
	        Path outPath;
	        FileSystem dfs;


	        outPath = new Path(join_output_write);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "Write", WriteConcurrentOutputFormat.class, Text.class, TweetWritable.class);

	        
	        outPath = new Path(countBase+"/Main/Join");
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
