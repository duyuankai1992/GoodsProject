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
 * 1.��ȡ�����ļ�����key����������Ӧ��ֵ
 * 2.���ø�dataSource
 * 
 * ���裺
 * 1.����jar��
 * 2.���������ļ�
 * 3.���幤����
 * 4.�ڹ������ж���BasicDataSource����
 * 5.��ȡ�����ļ�����DataSource��������ֵ
 * 6.�������ӷ���������
 */
public class JDBCPool {
	// ����������ӳ�
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
		// ׼��������
		InputStream inputStream = JDBCPool.class.getClassLoader()
				.getResourceAsStream("db.properties");
		prop.load(inputStream);
		// �õ�ֵ ��ȡ�����ļ���ֵ�������ø�datasource����
		datasource.setDriverClassName(prop.getProperty("driverClass"));
		datasource.setUrl(prop.getProperty("url"));
		datasource.setUsername(prop.getProperty("user"));
		datasource.setPassword(prop.getProperty("password"));
		// �ж�����ֵ
		String initSize = prop.getProperty("initsize");
		String maxIdle = prop.getProperty("maxIdle");
		String minIdle = prop.getProperty("minIdle");
		String maxActive = prop.getProperty("maxActive");
		String maxWait = prop.getProperty("maxWait");
		// ���ó�ʼ������ֵ
		if (initSize != null) {
			datasource.setInitialSize(Integer.parseInt(initSize));
		}
		// ���������и���
		if (maxIdle != null) {
			datasource.setMaxIdle(Integer.parseInt(maxIdle));
		}

		// ������С�Ŀ��и���
		if (minIdle != null) {
			datasource.setMinIdle(Integer.parseInt(minIdle));
		}

		// �����������Ӹ���
		if (maxActive != null) {
			datasource.setMaxActive(Integer.parseInt(maxActive));
		}

		// ���õȴ�ʱ��
		if (maxWait != null) {
			datasource.setMaxWait(Integer.parseInt(maxWait));
		}

	}

	// �������Ӷ���
	public static Connection getConnection() throws SQLException {
		return datasource.getConnection();
	}

	// ����رշ���
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

	// ��������
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
