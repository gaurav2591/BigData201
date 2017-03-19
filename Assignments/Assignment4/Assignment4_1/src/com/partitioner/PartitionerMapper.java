package com.partitioner;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class PartitionerMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	        String record = value.toString();
	        String[] fields = record.split("\\s+");
	        context.write(new Text(fields[2]), new Text(fields[1]+","+fields[3]));
	    }

}
