package com.partitioner;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class PartitionerPart extends Partitioner<Text, Text>{

	@Override
	public int getPartition(Text key, Text value, int noOfReducers) {
		String[] values = value.toString().split(",");
		int age = Integer.parseInt(values[0]);
		if(age < 20){
			return 0;
		}else if ((age >= 20) && (age <= 50)){
			return 1;
		}else{
			return 2;
		}
		
	}

}
