package com.company.biz.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
		private final static String ORACLE_NAME="oracle.jdbc.OracleDriver";
		private final static String ORACLE_URL="jdbc:oracle:thin:@localhost:1521:xe";
		private final static String ORACLE_ID="system";
		private final static String ORACLE_PWD="1234";
		
	
	public static Connection getConnection() {
		
		Connection conn=null;
		
		try {
			Class.forName(ORACLE_NAME);
			
			conn=DriverManager.getConnection(ORACLE_URL, ORACLE_ID, ORACLE_PWD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void close(ResultSet rs,PreparedStatement stmt,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void close(PreparedStatement stmt,Connection conn) {
		if(stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
