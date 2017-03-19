package com.calculate;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CalculateMapper extends Mapper<LongWritable, Text, Text, Text>{

	public void map(LongWritable key, Text value, Context context){
		String record = value.toString();
		String[] fields = record.split("\t");
		try {
			/*context.write(new Text(new Text(fields[0])), new Text(fields[2]));*/
			Text val = new Text("COUNT:1,SUM:"+fields[2]+",MAX:"+fields[2]+",MIN:"+fields[2]);
			System.out.println("++++++++++++=Mapper: "+val);
			context.write(new Text(new Text(fields[0])), val);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	

}
