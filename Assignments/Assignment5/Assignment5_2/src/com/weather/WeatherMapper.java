package com.weather;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WeatherMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		  if(value.toString().trim().length() > 0){
	        String[] values = value.toString().split("\\s+");
	        if((values != null) && values.length>0){
	        String day = values[1];
	        String maxTemp = values[5];
	        String minTemp = values[6];
	        float maxTmp = Float.parseFloat(maxTemp);
	        float minTmp = Float.parseFloat(minTemp);
	        String finalDec = null;
	        if(maxTmp > 42){
	        	finalDec = "Hot Day";
	        }else if(minTmp < 8){
	        	finalDec = "Cold Day";
	        }
	        
	        if(finalDec != null){
	        	context.write(new Text(day), new Text(finalDec));	
	        }
	        }
		  }
	    }

}
