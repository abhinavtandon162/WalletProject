package com.cg.wallet.dto;

public class Customer {

	private String cusName;
	private String cusMobileNo;
	private int cusAge;
	private double cusInitialBalance;
	
	public String getName() {
		return cusName;
	}
	public void setName(String name) {
		this.cusName = name;
	}
	
	public String getMobileNo() {
		return cusMobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.cusMobileNo = mobileNo;
	}
	
	public int getAge() {
		return cusAge;
	}
	public void setAge(int age) {
		this.cusAge = age;
	}
	
	public double getInitialBalance() {
		return cusInitialBalance;
	}
	public void setInitialBalance(double initialBalance) {
		this.cusInitialBalance = initialBalance;
	}
	
}
