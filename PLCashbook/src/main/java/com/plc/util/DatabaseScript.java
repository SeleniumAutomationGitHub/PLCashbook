package com.plc.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseScript {
	
	private Properties prop = new Properties();
	
	private Connection conn;
	private String mySqlDriver;
	private String serverName;
	private String databaseName;
	private String userName;
	private String pwd;
	private String query;
	
	public DatabaseScript() throws IOException{
		prop.load(new FileInputStream("DBDetails.properties"));
		mySqlDriver = prop.getProperty("mySqlDriverName");
		serverName = prop.getProperty("serveName");
		databaseName = prop.getProperty("database");
		userName = prop.getProperty("user");
		pwd = prop.getProperty("pass");
		query = prop.getProperty("query");
		
		//prop.store(new FileOutputStream("DBResult.properties"), "writing result");
		//prop.setProperty("pwd", "query");
	}
	
	public void retriveData() throws SQLException, ClassNotFoundException{
		try{
			Class.forName(mySqlDriver);
			conn = DriverManager.getConnection(serverName+databaseName,userName, pwd);
			
			Statement stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(query);
			
			while(rs.next()){
				System.out.println(rs.getString(1));
				System.out.println(rs.getString(2));
			}
		}finally{
			if(conn != null){
				conn.close();
			}
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		
		DatabaseScript ds = new DatabaseScript();
		System.out.println(ds.mySqlDriver);
		System.out.println(ds.serverName);
		System.out.println(ds.databaseName);
		System.out.println(ds.userName);
		System.out.println(ds.pwd);
		System.out.println(ds.query);
		System.out.println();
		ds.retriveData();
		
	}

}
