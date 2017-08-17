package com.yz.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;

/*
 * 1.读取属性文件根据key，或者所对应的值
 * 2.设置给dataSource
 * 
 * 步骤：
 * 1.加载jar包
 * 2.定义属性文件
 * 3.定义工具类
 * 4.在工具类中定义BasicDataSource对象
 * 5.读取属性文件并给DataSource对象设置值
 * 6.创建连接方法并测试
 */
public class JDBCPool {
	// 此类管理连接池
	private static BasicDataSource datasource = null;

	static {
		datasource = new BasicDataSource();
		try {
			init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void init() throws IOException {
		Properties prop = new Properties();
		// 准备流对象
		InputStream inputStream = JDBCPool.class.getClassLoader()
				.getResourceAsStream("db.properties");
		prop.load(inputStream);
		// 得到值 获取属性文件的值，并设置给datasource对象
		datasource.setDriverClassName(prop.getProperty("driverClass"));
		datasource.setUrl(prop.getProperty("url"));
		datasource.setUsername(prop.getProperty("user"));
		datasource.setPassword(prop.getProperty("password"));
		// 判断设置值
		String initSize = prop.getProperty("initsize");
		String maxIdle = prop.getProperty("maxIdle");
		String minIdle = prop.getProperty("minIdle");
		String maxActive = prop.getProperty("maxActive");
		String maxWait = prop.getProperty("maxWait");
		// 设置初始化连接值
		if (initSize != null) {
			datasource.setInitialSize(Integer.parseInt(initSize));
		}
		// 设置最大空闲个数
		if (maxIdle != null) {
			datasource.setMaxIdle(Integer.parseInt(maxIdle));
		}

		// 设置最小的空闲个数
		if (minIdle != null) {
			datasource.setMinIdle(Integer.parseInt(minIdle));
		}

		// 设置最大的连接个数
		if (maxActive != null) {
			datasource.setMaxActive(Integer.parseInt(maxActive));
		}

		// 设置等待时间
		if (maxWait != null) {
			datasource.setMaxWait(Integer.parseInt(maxWait));
		}

	}

	// 创建连接对象
	public static Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	// 定义关闭方法
	public static void closeResource(Connection conn, Statement stat,
			ResultSet rs) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (stat != null) {
			try {
				stat.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// 测试连接
	public static void main(String[] args) {
		try {
			Connection conn = JDBCPool.getConnection();
			System.out.println(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
