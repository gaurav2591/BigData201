package com.lineCount;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class LineCountReducer extends Reducer<Text,LongWritable,Text,LongWritable>{
	
	public void reduce(Text key, Iterable<LongWritable> values,Context context){
		long noOfLines = 0;
		for(LongWritable line : values){
			noOfLines += line.get();
		}
		try {
			context.write(new Text("Number of lines"),new LongWritable(noOfLines) );
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}


}
