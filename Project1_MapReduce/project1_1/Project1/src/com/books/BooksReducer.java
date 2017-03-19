package com.books;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class BooksReducer extends Reducer<Text, LongWritable, Text, LongWritable> {
    
   public void reduce(Text key, Iterable<LongWritable> values,Context context) throws IOException, InterruptedException {
       int sum = 0;
       for (LongWritable val : values) {
    	   sum += val.get();
       }
       context.write(key, new LongWritable(sum));
   }
    
}