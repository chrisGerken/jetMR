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
package com.gerkenip.main.FilterByLink;

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


public class FilterByLinkJob {
	
	public static void main(String[] args) {

		String filterbylink_input_readinteresting = "file:///home/";
		String filterbylink_input_readlinks = "file:///home/";
		String filterbylink_output_write = "file:///home/";

		String countBase = "file";

		Properties prop = new Properties();
		try {
			if (args.length > 0) {
				InputStream stream = new FileInputStream(args[0]);
				prop.loadFromXML(stream);
				filterbylink_input_readinteresting = prop.getProperty("FilterByLink.input.ReadInteresting",filterbylink_input_readinteresting);
				filterbylink_input_readlinks = prop.getProperty("FilterByLink.input.ReadLinks",filterbylink_input_readlinks);
				filterbylink_output_write = prop.getProperty("FilterByLink.output.Write",filterbylink_output_write);

				countBase = prop.getProperty("count.base.dir",countBase);

				prop.list(System.out);
			}
		} catch (Exception e) {
			System.out.println("Properties file not found. Will use default properties instead.");
		}
		
		try {
			Configuration conf = new Configuration();

			conf.set("FilterByLink.input.ReadInteresting",filterbylink_input_readinteresting);
			conf.set("FilterByLink.input.ReadLinks",filterbylink_input_readlinks);
			conf.set("FilterByLink.output.Write",filterbylink_output_write);

			conf.set("count.base.dir",countBase);

				// Begin custon config logic

				// End custon config logic

			Job job = new Job(conf, "com.gerkenip.main.FilterByLink.FilterByLinkJob : ");

			job.setJarByClass(FilterByLinkJob.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(InterestingJoinWritable.class);

				// Configure mapper for input ReadInteresting
			MultipleInputs.addInputPath(job, new Path(filterbylink_input_readinteresting), SequenceFileInputFormat.class, ReadInterestingMapper.class);


				// Configure mapper for input ReadLinks
			MultipleInputs.addInputPath(job, new Path(filterbylink_input_readlinks), SequenceFileInputFormat.class, ReadLinksMapper.class);

	        job.setReducerClass(WriteReducer.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	        
	        Path outPath;
	        FileSystem dfs;


	        outPath = new Path(filterbylink_output_write);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "Write", WriteConcurrentOutputFormat.class, ReasonWritable.class, UseInfo.class);

	        
	        outPath = new Path(countBase+"/Main/FilterByLink");
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
