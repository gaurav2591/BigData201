package com.lineCount;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LineCountMapper extends Mapper<LongWritable, Text, Text, LongWritable>{

	public void map(LongWritable key, Text value, Context context){
		try {
			context.write(new Text("NoOfLines"), new LongWritable(1));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	

}
