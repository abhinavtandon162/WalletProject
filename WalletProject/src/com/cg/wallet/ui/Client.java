package com.cg.wallet.ui;

import java.util.Scanner;

import com.cg.wallet.dto.Customer;
import com.cg.wallet.exception.BankAccountException;
import com.cg.wallet.service.BankAccountServiceImpl;

public class Client {

	static BankAccountServiceImpl serviceImpl = new BankAccountServiceImpl();
	  
	static Scanner sc = new Scanner(System.in);

	static String name;
	static String mobileNo;
	static int age;
	static double amount;
	static Customer customer;
	public static void main(String args[]) throws BankAccountException{
		

		int ch = 0;
		while(true){
			System.out.println("1.Add Customer Details");
			System.out.println("2.Deposit amount");
			System.out.println("3.Withdraw Amount");
			System.out.println("4.Funds Transfer");
			System.out.println("5.Check Customer Balance");
			System.out.println("6.Exit");
			System.out.println("Enter your choice : ");
			ch = sc.nextInt();

			switch(ch){
			case 1 : addCustomer();break;
			case 2 : deposit(); break;					
			case 3 : withdraw(); break;				
			case 4 : fundTransfer();break;
			case 5 : checkBalance(); break;
			case 6 : exit();break;
			default : System.out.println("Invalid input!");
			}

		}

	}
	private static void exit() {
		System.out.println("Application End");
		System.exit(0);

	}
	private static int checkBalance() throws BankAccountException {

		System.out.println("Enter the moible id to check balance");
		mobileNo = sc.next();
		if(serviceImpl.validateAccount(mobileNo))
		{
		System.out.println("Current Amount is Rs."+serviceImpl.checkBalance(mobileNo));	
		}
        System.out.println("Mobile Number not found!");
		return 0;

	}
	private static int fundTransfer() throws BankAccountException {
		String mobileNoReciever;
		System.out.println("Enter your mobile number : ");
		mobileNo = sc.next();

		System.out.println("Enter the amount you want to transfer : ");
		amount = sc.nextDouble();

		System.out.println("Enter receivers mobile number : ");
		mobileNoReciever = sc.next();
		if(mobileNo.equals(mobileNoReciever)){								
			System.out.println("Both numbers are same!");
			return 0;
		}
		if((serviceImpl.validateMoileNo(mobileNo) )&& (serviceImpl.validateMoileNo(mobileNoReciever)) && (serviceImpl.validateAmount(amount)))
		{
			if((serviceImpl.validateAccount(mobileNoReciever)) && (serviceImpl.validateAccount(mobileNo)))
				serviceImpl.fundTransfer(mobileNo, mobileNoReciever, amount);
		}

		return 0;


	}
	private static int withdraw() throws BankAccountException {
		System.out.println("Enter your mobile number : ");
		mobileNo = sc.next();

		System.out.println("Enter the amount you want to withdraw : ");
		amount = sc.nextDouble();
		if(serviceImpl.validateMoileNo(mobileNo) && serviceImpl.validateAmount(amount)){
			if(serviceImpl.validateAccount(mobileNo))
				serviceImpl.withdraw(mobileNo, amount);
		}
		return 0;

	}
	private static int deposit() throws BankAccountException {
		System.out.println("Enter your mobile number : ");
		mobileNo = sc.next();

		System.out.println("Enter the amount you want to deposit");
		amount = sc.nextDouble();
		if(serviceImpl.validateMoileNo(mobileNo)&& serviceImpl.validateAmount(amount)){
			if(serviceImpl.validateAccount(mobileNo))
				serviceImpl.deposit(mobileNo, amount);
		}
		return 0;

	}
	private static int addCustomer() throws BankAccountException {
		customer = new Customer();						

		System.out.println("Enter customer name : ");
		name = sc.next();
		if(!serviceImpl.validateName(name))
		{
			System.err.println("Invalid Name!");
			return 0;
		}
		System.out.println("Enter mobile no. : ");
		mobileNo = sc.next();
		if(!serviceImpl.validateMoileNo(mobileNo))
		{
			System.err.println("Invalid Mobile Number");
			return 0;
		}
		else if(serviceImpl.validateAccount(mobileNo))
		{
			System.err.println("Customer already exist with this number!");
			return 0;
		}
		else
			System.out.println("Enter age : ");
		age = sc.nextInt();
		if(serviceImpl.validateAge(age))
			System.out.println("Enter initial amount : ");
		amount = sc.nextDouble();
		if(!serviceImpl.validateAmount(amount))
		{
			System.err.println("Invalid Amount!");
			return 0;
		}
		else{	
			customer.setName(name);
			customer.setMobileNo(mobileNo);
			customer.setAge(age);
			customer.setInitialBalance(amount);

			serviceImpl.createAccount(customer);

			System.out.println("Customer added successfully");
		}
		return 0;
	}

}
