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
package com.gerkenip.trending.Sort;

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


public class SortJob {
	
	public static void main(String[] args) {

		String sort_input_passthru = "hdfs://localhost:9000/tmp/SocialLinks/Links";
		String sort_output_sort = "hdfs://localhost:9000/tmp/SocialLinks/Links";

		String countBase = "file";

		Properties prop = new Properties();
		try {
			if (args.length > 0) {
				InputStream stream = new FileInputStream(args[0]);
				prop.loadFromXML(stream);
				sort_input_passthru = prop.getProperty("Sort.input.Passthru",sort_input_passthru);
				sort_output_sort = prop.getProperty("Sort.output.Sort",sort_output_sort);

				countBase = prop.getProperty("count.base.dir",countBase);

				prop.list(System.out);
			}
		} catch (Exception e) {
			System.out.println("Properties file not found. Will use default properties instead.");
		}
		
		try {
			Configuration conf = new Configuration();

			conf.set("Sort.input.Passthru",sort_input_passthru);
			conf.set("Sort.output.Sort",sort_output_sort);

			conf.set("count.base.dir",countBase);

				// Begin custon config logic

				// End custon config logic

			Job job = new Job(conf, "com.gerkenip.trending.Sort.SortJob : ");

			job.setJarByClass(SortJob.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(LongWritable.class);

				// Configure mapper for input Passthru
			MultipleInputs.addInputPath(job, new Path(sort_input_passthru), SequenceFileInputFormat.class, PassthruMapper.class);

	        job.setReducerClass(SortReducer.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	        
	        Path outPath;
	        FileSystem dfs;


	        outPath = new Path(sort_output_sort);
			TextOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new SortConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "Sort", SortConcurrentOutputFormat.class, Text.class, Text.class);

	        
	        outPath = new Path(countBase+"/Trending/Sort");
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
