package com.reduceSideJoin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;


public class ReduceSideJoin {

	
	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(ReduceSideJoin.class);
        job.setReducerClass(ReduceSideJoinReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class,StoreLocationsMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class,StoreSalesMapper.class);
        
        Path outputPath = new Path(args[2]);
        FileOutputFormat.setOutputPath(job, outputPath);
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
       
    }
	
	public static class StoreLocationsMapper extends Mapper<LongWritable, Text, Text, Text> {
		
		  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			  String[] valuesArray = value.toString().split(",");
			  context.write(new Text(valuesArray[0]), new Text("storeLoc\t"+valuesArray[1]));
			  
		  }

	}
	
	public static class StoreSalesMapper extends Mapper<LongWritable, Text, Text, Text> {
		
		  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			  String[] valuesArray = value.toString().split(",");
			  context.write(new Text(valuesArray[0]), new Text("storeSal\t"+valuesArray[1]));
		  }

	}
	
	public static class ReduceSideJoinReducer extends Reducer<Text, Text, Text, Text> {
	    
		   public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		       String value = null;
		       double total = 0;
		       for (Text val : values) {
		    	   String[] data = val.toString().split("\t");
		   		   if(data[0].equalsIgnoreCase("storeLoc")){
		   			   	value = data[1];
		   		   }else{
		   			    total += Double.parseDouble(data[1]);
		   		   }
		       }
		       context.write(new Text(key), new Text(value+"\t"+total));
		   }
		    
		}
	

}
