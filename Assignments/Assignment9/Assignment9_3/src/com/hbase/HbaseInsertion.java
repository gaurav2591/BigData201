package com.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseInsertion {
	
	public static void main(String[] args) throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		
		HTable hTable = new HTable(configuration, "employee");
		
		Put put = new Put(Bytes.toBytes("3"));
		
		put.add(Bytes.toBytes("personal"),Bytes.toBytes("name"),Bytes.toBytes("Sanjay"));
		
		put.add(Bytes.toBytes("personal"),Bytes.toBytes("city"),Bytes.toBytes("Jaipur"));
		
		put.add(Bytes.toBytes("official"),Bytes.toBytes("competency"),Bytes.toBytes("C5"));
		
		put.add(Bytes.toBytes("official"),Bytes.toBytes("mid"),Bytes.toBytes("101010"));
		
		hTable.put(put);
		
		hTable.close();
	}

}
