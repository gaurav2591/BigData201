package com.calculate;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CalculateReducer extends Reducer<Text,Text,Text,Text>{
	
	
	public void reduce(Text key, Iterable<Text> values,Context context){
		int count = 0;
		double sum = 0;
		double max = 0;
		double min = 0;
		System.out.println("++++++++++++++++++"+values);
		for(Text line : values){
			System.out.println("++++++++++++++Inside loop1");
			String lineStr = line.toString();
			String[] lineArray = lineStr.split(",");
			for(String field : lineArray){
				System.out.println("+++++++++++++++++++Inside loop2");
				String[] eachField = field.split(":");
				if(eachField[0].equalsIgnoreCase("COUNT")){
					count += Integer.parseInt(eachField[1]);
				}else if(eachField[0].equalsIgnoreCase("SUM")){
					sum += Double.parseDouble(eachField[1]);
				}else if (eachField[0].equalsIgnoreCase("MAX")){
					if(max < Double.parseDouble(eachField[1])){
						max = Double.parseDouble(eachField[1]);
					}
					
				}else if(eachField[0].equalsIgnoreCase("MIN")){
					if(max >  Double.parseDouble(eachField[1])){
						min = Double.parseDouble(eachField[1]);
					}
					
				}
			}
			/*count++;*/
			//double val = Double.parseDouble(line.toString());
		/*	if(val > max){
				max = val;
			}
			if(val < max){
				
				min = val;
			}
			
			
		}
		if(min == 0){
			min = max;
		}*/
		
		
	}
		if(min == 0){
			min = max;
		}
		try {
			context.write(new Text(key),new Text("COUNT:"+count+",SUM:"+sum+",MAX:"+max+",MIN:"+min));
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}


}
}
