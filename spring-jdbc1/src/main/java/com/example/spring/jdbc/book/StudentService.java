package com.example.spring.jdbc.book;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

public class StudentService {
	public StudentService() {
		// TODO Auto-generated constructor stub
	}
	public Connection connectDB() throws IOException{
		Connection connection = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=LAPTOP-RD08C87L\\SERVER_CSDL;databaseName=JBCD;encrypt=true;trustServerCertificate=true;","sa","130613Ami");
            
		} catch(Exception e) {
			e.getStackTrace();
		}
		return connection;
	}
}
