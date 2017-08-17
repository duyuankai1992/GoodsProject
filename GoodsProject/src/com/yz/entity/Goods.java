package com.yz.entity;

public class Goods {
	private int goodsId;
	public int getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}

	private String goodsName;
	private double goodsPrice;
	private int goodsNumber;
	
	

	public Goods(int goodsId, String goodsName, double goodsPrice,
			int goodsNumber) {
		super();
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodsPrice = goodsPrice;
		this.goodsNumber = goodsNumber;
	}
	
	public Goods() {
		// TODO Auto-generated constructor stub
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getGoodsPrice() {
		return goodsPrice;
	}

	public void setGoodsPrice(double goodsPrice) {
		this.goodsPrice = goodsPrice;
	}

	public int getGoodsNumber() {
		return goodsNumber;
	}

	public void setGoodsNumber(int goodsNumber) {
		this.goodsNumber = goodsNumber;
	}

	@Override
	public String toString() {
		return "Goods [goodsName=" + goodsName + ", goodsPrice=" + goodsPrice
				+ ", goodsNumber=" + goodsNumber + "]";
	}

}
