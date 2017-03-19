package com.books;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class BooksMapper extends Mapper<LongWritable, Text, Text, LongWritable> {
	
	  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		  String[] record = value.toString().split("\\^");
	       /* String isbn = record[0];*/
	        String yearOfPub = record[3];
	        System.out.println("year : "+yearOfPub+" Size : "+record.length);
	        if(!yearOfPub.equalsIgnoreCase("Year-Of-Publication")){
	        	context.write(new Text(yearOfPub), new LongWritable(1));
	        }
	    }

}
