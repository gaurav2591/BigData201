package com.FilterCounter;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class FilterCounter {
	
	private enum COUNTERS{
		FILTERED_OUT_RECORDS
	}
	
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(FilterCounter.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.setMapperClass(FilterCounterMapper.class);
        
        job.setNumReduceTasks(0);
        
        
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(NullWritable.class);
        int exitCode = job.waitForCompletion(true) ? 0 : 1;
        if(exitCode == 0){
        	Counters counters = job.getCounters();
        
        	System.out.println("Filtered out records : "+counters.findCounter(COUNTERS.FILTERED_OUT_RECORDS).getValue());
        }
        System.exit(exitCode);
       
    }
	
	public static class FilterCounterMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
		
		  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		        String record = value.toString();
		        String[] fields = record.split("\\s+");
		        if(Integer.parseInt(fields[1]) < 10 || Integer.parseInt(fields[1]) > 50){
		        	context.getCounter(COUNTERS.FILTERED_OUT_RECORDS).increment(1L);
		        	context.write(value, NullWritable.get());
		        }
		    }

	}
	

}
