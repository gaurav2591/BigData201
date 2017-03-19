package com.filter;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FilterMapper extends Mapper<LongWritable, Text, Text, Text>{

	public void map(LongWritable key, Text value, Context context){
		String record = value.toString();
		String[] fields = record.split("\t");
		
		if(Double.parseDouble(fields[2]) <= 20){
			try {
				context.write(value, new Text());
			} catch (IOException | InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	

}
