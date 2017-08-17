package com.yz.dao;

import java.util.List;

import com.yz.entity.Goods;

public interface GoodsDAO {
	/**
	 * 添加商品
	 * @param g   具体某一个商品对象
	 */
   public boolean addGoods(Goods g);
   
   /**
    * 查询所有商品
    * @return   商品列表
    */
   public List<Goods> findAll();
   
   /**
    * 根据主键删除，一条记录
    * @param id   商品编号
    * @return   是否删除成功
    */
   public boolean deleteGoodsById(int id);
   
   /**
    * 根据主键更新，一条记录
    * @param id   商品编号
    * @return   是否删除成功
    */
   public boolean updateGoodsById(Goods g);
   
   /**
    * 查询具体的一条记录
    * @param id
    * @return
    */
   public Goods findById(int id);
}
