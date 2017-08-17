package com.yz.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yz.dao.GoodsDAO;
import com.yz.entity.Goods;
import com.yz.util.JDBCPool;

public class GoodsDAOImpl implements GoodsDAO {

	@Override
	public boolean addGoods(Goods g) {
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = JDBCPool.getConnection();
			String sql = "insert into goods values(seq_goods.NEXTVAL,?,?,?)";
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, g.getGoodsName());
			pstat.setDouble(2, g.getGoodsPrice());
			pstat.setInt(3, g.getGoodsNumber());
			// ִ��
			int result = pstat.executeUpdate();
			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCPool.closeResource(conn, pstat, null);
		}
		return false;
	}

	@Override
	public List<Goods> findAll() {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		List<Goods> lists = null;
		try {
			conn = JDBCPool.getConnection();
			String sql = "select * from goods";
			pstat = conn.prepareStatement(sql);
			// ִ��
			rs = pstat.executeQuery();
			lists = new ArrayList<Goods>();
			while (rs.next()) {
				Goods goods = new Goods();
				goods.setGoodsId(rs.getInt("gid"));
				goods.setGoodsName(rs.getString("gname"));
				goods.setGoodsPrice(rs.getDouble("gprice"));
				goods.setGoodsNumber(rs.getInt("gnumber"));
				// ��ӵ�������
				lists.add(goods);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCPool.closeResource(conn, pstat, null);
		}
		return lists;
	}

	@Override
	public boolean deleteGoodsById(int id) {
		Connection conn = null;
		PreparedStatement pstat = null;

		try {
			conn = JDBCPool.getConnection();
			String sql = "delete from goods where gid=?";
			pstat = conn.prepareStatement(sql);
			// ���ռλ��
			pstat.setInt(1, id);
			// ִ��ɾ������
			int result = pstat.executeUpdate();
			// �ж�Resultֵ
			if (result >= 1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCPool.closeResource(conn, pstat, null);
		}
		return false;
	}

	@Override
	public boolean updateGoodsById(Goods g) {
		Connection conn = null;
		PreparedStatement pstat = null;
		try {
			conn = JDBCPool.getConnection();
			String sql = "update goods set gname=?,gprice=?,gnumber=? where gid=?";
			pstat = conn.prepareStatement(sql);
			// ����ռλ��
			pstat.setString(1, g.getGoodsName());
			pstat.setDouble(2, g.getGoodsPrice());
			pstat.setInt(3, g.getGoodsNumber());
			pstat.setInt(4, g.getGoodsId());
			// ִ�и��²���
			int result = pstat.executeUpdate();
			if (result >= 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCPool.closeResource(conn, pstat, null);
		}
		return false;
	}

	@Override
	public Goods findById(int id) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		Goods goods = null;
		try {
			conn = JDBCPool.getConnection();
			String sql = "select * from goods where gid=?";
			pstat = conn.prepareStatement(sql);
			pstat.setInt(1, id);
			// ִ��
			rs = pstat.executeQuery();
			goods = new Goods();
			while (rs.next()) {
				goods.setGoodsId(rs.getInt("gid"));
				goods.setGoodsName(rs.getString("gname"));
				goods.setGoodsPrice(rs.getDouble("gprice"));
				goods.setGoodsNumber(rs.getInt("gnumber"));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCPool.closeResource(conn, pstat, null);
		}
		return goods;
	}

}
