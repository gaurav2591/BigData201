package com.partitioner;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class PartitionerReducer extends Reducer<Text, Text, Text, Text> {
    
   public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
       int max = -1;
       for (Text val : values) {
    	   String[] data = val.toString().split(",");
   		   int marks = Integer.parseInt(data[1]);
    	   if(marks > max){
    		   max = marks;
    	   }
       }
       context.write(new Text(key), new Text(String.valueOf(max)));
   }
    
}