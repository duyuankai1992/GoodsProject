package com.yz.dao;

import java.util.List;

import com.yz.entity.Goods;

public interface GoodsDAO {
	/**
	 * �����Ʒ
	 * @param g   ����ĳһ����Ʒ����
	 */
   public boolean addGoods(Goods g);
   
   /**
    * ��ѯ������Ʒ
    * @return   ��Ʒ�б�
    */
   public List<Goods> findAll();
   
   /**
    * ��������ɾ����һ����¼
    * @param id   ��Ʒ���
    * @return   �Ƿ�ɾ���ɹ�
    */
   public boolean deleteGoodsById(int id);
   
   /**
    * �����������£�һ����¼
    * @param id   ��Ʒ���
    * @return   �Ƿ�ɾ���ɹ�
    */
   public boolean updateGoodsById(Goods g);
   
   /**
    * ��ѯ�����һ����¼
    * @param id
    * @return
    */
   public Goods findById(int id);
}
