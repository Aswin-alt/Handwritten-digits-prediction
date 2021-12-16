package com.aswin.model;

public class Stock {

	private int stockId;
	private String stockName;
	private int stockAvailable;
	private double stockPrice;
	private double totalAmount;
	
	public Stock() {
		
	}
	
	public Stock(int stockId, String stockName, int stockAvailable, double stockPrice) {
		
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockAvailable = stockAvailable;
		this.stockPrice = stockPrice;
	}
	
	public Stock(int stockId, String stockName, double stockPrice) {
		
		this.stockId = stockId;
		this.stockName = stockName;
		this.stockPrice = stockPrice;
	}

	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}

	public int getStockAvailable() {
		return stockAvailable;
	}

	public void setStockAvailable(int stockAvailable) {
		this.stockAvailable = stockAvailable;
	}

	public double getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(double stockPrice) {
		this.stockPrice = stockPrice;
	}
	
	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
}
