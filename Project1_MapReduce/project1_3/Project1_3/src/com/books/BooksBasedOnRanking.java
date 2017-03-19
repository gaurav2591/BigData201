package com.books;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


public class BooksBasedOnRanking {

	public static void main(String[] args) throws IllegalArgumentException, IOException, ClassNotFoundException, InterruptedException {
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(BooksBasedOnRanking.class);
        job.setReducerClass(ReduceSideJoinReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        
        MultipleInputs.addInputPath(job, new Path(args[0]), TextInputFormat.class,BooksMapper.class);
        MultipleInputs.addInputPath(job, new Path(args[1]), TextInputFormat.class,BooksRankingMapper.class);
        
        Path outputPath = new Path(args[2]);
        FileOutputFormat.setOutputPath(job, outputPath);
        
        System.exit(job.waitForCompletion(true) ? 0 : 1);
       
    }
	
	public static class BooksMapper extends Mapper<LongWritable, Text, Text, Text> {
		
		  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			  String[] valuesArray = value.toString().split("\\^");
			  if(valuesArray[3].equalsIgnoreCase("2002")){
				  context.write(new Text(valuesArray[0]), new Text("books\t1"));				  
			  }
			  
		  }

	}
	
	public static class BooksRankingMapper extends Mapper<LongWritable, Text, Text, Text> {
		
		  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			  String[] valuesArray = value.toString().split("\\^");
			  if(!valuesArray[2].equalsIgnoreCase("Book-Rating")){
				  context.write(new Text(valuesArray[1]), new Text("booksRanking\t"+valuesArray[2]));				  
			  }
		  }

	}
	
	public static class ReduceSideJoinReducer extends Reducer<Text, Text, Text, Text> {
		
		private static Map<String,Integer> map = new HashMap<String,Integer>();
		
		public void setup(Context context){
		  map.put("0", 0);
		  map.put("1", 0);
		  map.put("2", 0);
		  map.put("3", 0);
		  map.put("4", 0);
		  map.put("5", 0);
		  map.put("6", 0);
		  map.put("7", 0);
		  map.put("8", 0);
		  map.put("9", 0);
		  map.put("10", 0);
		}
	    
		   public void reduce(Text key, Iterable<Text> values,Context context) throws IOException, InterruptedException {
		       List<String> ranking = new ArrayList<String>();
		       long total = 0;
		       for (Text val : values) {
		    	   String[] data = val.toString().split("\t");
		   		   if(data[0].equalsIgnoreCase("books")){
		   			   	total += Integer.parseInt(data[1]);
		   		   }else{
		   			//ranking = data[1];
		   			ranking.add(data[1]);
		   		   }
		   		
		       }
		       if((!ranking.isEmpty()) && (total > 0)){
		    	   for(String str : ranking){
		    		   int increnetedValue = map.get(str) + 1;
				    	  map.put(str, increnetedValue);
		    	   }
			    	  /*int increnetedValue = map.get(ranking) + 1;
			    	  map.put(ranking, increnetedValue);*/
			     }
		      
		       
		      // context.write(new Text(ranking), new Text(String.valueOf(total)));
		   }
		   
		   public void cleanup(Context context) throws IOException, InterruptedException{
			   for(Map.Entry<String, Integer> entry : map.entrySet()){
				   context.write(new Text(entry.getKey()), new Text(entry.getValue().toString()));
			   }
		   }
		    
		}
	

}
