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


public class LoadJob {
	
	public static void main(String[] args) {

		String load_input_read = "file:///home/";
		String load_output_writelinks = "file:///home/";
		String load_output_writeterms = "file:///home/";
		String load_output_writehashtags = "file:///home/";

		String countBase = "file";

		Properties prop = new Properties();
		try {
			if (args.length > 0) {
				InputStream stream = new FileInputStream(args[0]);
				prop.loadFromXML(stream);
				load_input_read = prop.getProperty("Load.input.Read",load_input_read);
				load_output_writelinks = prop.getProperty("Load.output.writeLinks",load_output_writelinks);
				load_output_writeterms = prop.getProperty("Load.output.writeTerms",load_output_writeterms);
				load_output_writehashtags = prop.getProperty("Load.output.writeHashtags",load_output_writehashtags);

				countBase = prop.getProperty("count.base.dir",countBase);

				prop.list(System.out);
			}
		} catch (Exception e) {
			System.out.println("Properties file not found. Will use default properties instead.");
		}
		
		try {
			Configuration conf = new Configuration();

			conf.set("Load.input.Read",load_input_read);
			conf.set("Load.output.writeLinks",load_output_writelinks);
			conf.set("Load.output.writeTerms",load_output_writeterms);
			conf.set("Load.output.writeHashtags",load_output_writehashtags);

			conf.set("count.base.dir",countBase);

				// Begin custon config logic

				// End custon config logic

			Job job = new Job(conf, "com.gerkenip.loadinteresting.Load.LoadJob : ");

			job.setJarByClass(LoadJob.class);

			job.setMapOutputKeyClass(Text.class);
			job.setMapOutputValueClass(InterestingWritable.class);

				// Configure mapper for input Read
			MultipleInputs.addInputPath(job, new Path(load_input_read), TextInputFormat.class, ReadMapper.class);

	        job.setReducerClass(writeLinksReducer.class);
	        job.setOutputKeyClass(Text.class);
	        job.setOutputValueClass(Text.class);
	        job.setOutputFormatClass(TextOutputFormat.class);
	        
	        Path outPath;
	        FileSystem dfs;


	        outPath = new Path(load_output_writelinks);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteLinksConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "writeLinks", WriteLinksConcurrentOutputFormat.class, Text.class, InterestingToken.class);


	        outPath = new Path(load_output_writeterms);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteTermsConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "writeTerms", WriteTermsConcurrentOutputFormat.class, Text.class, InterestingToken.class);


	        outPath = new Path(load_output_writehashtags);
			SequenceFileOutputFormat.setOutputPath(job, outPath);
	        dfs = FileSystem.get(outPath.toUri(), conf);
	        if (dfs.exists(outPath)) {
	          dfs.delete(outPath, true);
	        }
            new WriteHashtagsConcurrentOutputFormat().configure(job.getConfiguration());
            MultipleOutputs.addNamedOutput(job, "writeHashtags", WriteHashtagsConcurrentOutputFormat.class, Text.class, InterestingToken.class);

	        
	        outPath = new Path(countBase+"/LoadInteresting/Load");
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
