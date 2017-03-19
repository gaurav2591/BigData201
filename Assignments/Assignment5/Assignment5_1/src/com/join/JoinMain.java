package com.join;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class JoinMain {

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();
		//conf.set("mapreduce.output.textoutputformat.separator", ",");
        Job job = Job.getInstance(conf);
        job.setJarByClass(JoinMain.class);
        //job.setOutputKeyClass(Text.class);
        //job.setOutputValueClass(LongWritable.class);
        job.setMapperClass(JoinMapper.class);
        //job.setReducerClass(JoinReducer.class);
        
       job.addCacheFile(new Path("/inputs/mr_inputs/assignment5/module5_ex1_f1").toUri());
        //job.addCacheFile(new Path(args[3]).toUri());
        
        job.setNumReduceTasks(0);
        
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        job.waitForCompletion(true);
        
    }
}
