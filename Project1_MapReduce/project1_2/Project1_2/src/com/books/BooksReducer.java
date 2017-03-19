package com.books;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BooksReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
	
	private static LongWritable maxValue = new LongWritable(0);
	private static Text maxKey = new Text("");
    
   public void reduce(Text key, Iterable<LongWritable> values,Context context) throws IOException, InterruptedException {
       long sum = 0;
       for (LongWritable val : values) {
    	   sum += val.get();
       }
       if(sum > maxValue.get() && !key.toString().equalsIgnoreCase("0")){
    	   maxValue.set(sum);
    	   maxKey.set(key);
       }
   }
   
   public void cleanup(Context context) throws IOException, InterruptedException{
	   context.write(maxKey, maxValue);
   }
    
}