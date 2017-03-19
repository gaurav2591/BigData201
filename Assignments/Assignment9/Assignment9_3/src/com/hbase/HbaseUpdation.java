package com.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseUpdation {
	
	public static void main(String[] args) throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		
		HTable hTable = new HTable(configuration, "employee");
		
		Put put = new Put(Bytes.toBytes("3"));
		
		put.add(Bytes.toBytes("personal"),Bytes.toBytes("name"),Bytes.toBytes("Rakesh"));
		
		put.add(Bytes.toBytes("personal"),Bytes.toBytes("city"),Bytes.toBytes("Lucknow"));
		
		hTable.put(put);
		
		hTable.close();
	}

}
