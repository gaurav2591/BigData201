package com.join;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class JoinMapper extends Mapper<LongWritable, Text, Text, Text> {
	
	private Map<String, String> map = new HashMap<String,String>();
	
	protected void setup(Context context) throws IOException, InterruptedException{
		super.setup(context);
		
		 URI[] cacheFiles = context.getCacheFiles();
		 Path path1 = new Path(cacheFiles[0]);
		 System.out.println("++++++++++"+path1);
		 if(path1.getName().equalsIgnoreCase("module5_ex1_f1")){
			 System.out.println("+++++++++==FOUND FILE");
			 BufferedReader bufferedReader = new BufferedReader(new FileReader(path1.getName()));
			 String line = bufferedReader.readLine();
			 while(line != null){
				 System.out.println("+++++++++==INSIDE LOOP");
				 String[] fields = line.split("\t");
				 String storeId = fields[0];
				 String storeName = fields[1];
				 map.put(storeId, storeName);
				 line = bufferedReader.readLine();
			 }
			 bufferedReader.close();
			 
		 }
		 
		 if(map.isEmpty()){
			 throw new IOException("Data is not loaded into the map");
		 }
	
	}
	
	  public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
	        String record = value.toString();
	        String[] fields = record.split("\t");
	        String storeId = fields[0];
	        String storeName = map.get(storeId);
	        context.write(new Text(record), new Text(storeName));
	    }

}
