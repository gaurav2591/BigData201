package com.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.util.Bytes;

public class HbaseDeletion {
	
	public static void main(String[] args) throws IOException {
		Configuration configuration = HBaseConfiguration.create();
		
		HTable hTable = new HTable(configuration, "employee");
		
		Delete delete = new Delete(Bytes.toBytes("3"));
		
		delete.deleteColumn(Bytes.toBytes("personal"), Bytes.toBytes("city"));
		
		delete.deleteFamily(Bytes.toBytes("official"));
		
		hTable.delete(delete);;
		
		hTable.close();
	}

}
