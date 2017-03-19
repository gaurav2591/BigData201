package com.filterRecords;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class FilterRecordsMapper extends Mapper<LongWritable, Text, Text, Text>{

	public void map(LongWritable key, Text value, Context context){
		String rec = value.toString();
		try {
			if(rec.length()>=15){
				context.write(new Text(rec), new Text());
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	

}
