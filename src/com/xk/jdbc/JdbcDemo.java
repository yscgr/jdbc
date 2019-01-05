package com.xk.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class JdbcDemo {
	@SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
		Connection ct=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			//1加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//2创建连接
			ct = DriverManager.getConnection("jdbc:mysql://localhost:3306/"
					+ "userdb?characterEncoding=utf-8", "root", "123");
			//创建sql对象
			ps=ct.prepareStatement("insert into user values(?,?,?,?,?,?)");
			ps.setString(1, "8");
			ps.setString(2, "司徒");
			ps.setString(3, "123456");
			ps.setString(4, "1");
			ps.setString(5, "sit@126.com");
			ps.setString(6, "admin");
			//执行sql语句
			ps.executeUpdate();
			ps = ct.prepareStatement("select * from user");
			//ps = ct.prepareStatement("select * from user");
			rs = ps.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString("id")+""
						+rs.getString("username")
						+rs.getString("password")+""
						+rs.getString("grade")+""
						+rs.getString("email")+""
						+rs.getString("identity"));
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			
		}finally {
			if(rs!=null) {
				rs.close();
			}
			if (ps!=null) {
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if(ct!=null) {
				try {
					ct.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
}
