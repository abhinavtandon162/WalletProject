package com.cg.wallet.dao;

import java.util.Map;

import com.cg.wallet.dao.Collection;
import com.cg.wallet.exception.BankAccountException;
import com.cg.wallet.dto.Customer;

public class BankAccountDAOImpl implements BankAccountDAO {

Map<String,Customer> customerMap;
	
	public BankAccountDAOImpl(){
		customerMap = Collection.createCollection();
	}
	
	@Override
	public void createAccount(Customer customer) {
		// TODO Auto-generated method stub
		customerMap.put(customer.getMobileNo(),customer);
		
	}

	@Override
	public void deposit(String mobileNo, double amount) {
		// TODO Auto-generated method stub
		Customer customer = customerMap.get(mobileNo);
		if(customer != null){
			double updateAmount = customer.getInitialBalance();
			updateAmount =updateAmount + amount;
			String name = customer.getName();
			String newMobileNo = customer.getMobileNo();
			int age = customer.getAge();
			
			customer.setAge(age);
			customer.setInitialBalance(updateAmount);
			customer.setName(name);
			customer.setMobileNo(newMobileNo);
			
			customerMap.put(newMobileNo, customer);
			System.out.println("Amount deposited in Account");
		}
		else{
			System.out.println("Mobile number not found, please enter valid mobile number!");
		}
	}

	@Override
	public void withdraw(String mobileNo, double withdrawAmount) {
		// TODO Auto-generated method stub
		Customer customer = customerMap.get(mobileNo);
		if(customer != null){
			double amount = customer.getInitialBalance();	
			
			String name = customer.getName();
			String newMobileNo = customer.getMobileNo();
			int age = customer.getAge();
			
			if(amount - withdrawAmount > 0)
				amount = amount - withdrawAmount;
			
			
			customer.setAge(age);
			customer.setInitialBalance(amount);
			customer.setName(name);
			customer.setMobileNo(newMobileNo);
			
			customerMap.put(newMobileNo, customer);
			System.out.println("Amount withdrawn from Account");
		}
		else{
			System.out.println("Mobile number not found, please enter valid mobile number!");
		}
		
	}

	@Override
	public double checkBalance(String mobileNo) {
		// TODO Auto-generated method stub
		Customer custCheckBalance = customerMap.get(mobileNo);
		double amount = custCheckBalance.getInitialBalance();
		return amount;
		
	}

	@Override
	public void fundTransfer(String sender, String reciever, double amount) {
		// TODO Auto-generated method stub
		
		String name, newMobileNo;
		int age;
		double amountFund;
		
		Customer custSender =  customerMap.get(sender);
		Customer custReciever = customerMap.get(reciever);
		
		double recieverAmount = custReciever.getInitialBalance();
		double senderAmount = custSender.getInitialBalance();
		if(!(senderAmount - amount > 0)){
			System.err.println("Invalid Amount! \nEntered Amount exceeds the Balance amount");
		}
		else{
			recieverAmount =recieverAmount + amount;
			senderAmount =senderAmount - amount;
			System.out.println("Fund Transferred to reciever Account");
		
		name = custSender.getName();
		newMobileNo = custSender.getMobileNo();
		age = custSender.getAge();
		amountFund = senderAmount;
		
		custSender.setAge(age);
		custSender.setInitialBalance(senderAmount);
		custSender.setMobileNo(newMobileNo);
		custSender.setName(name);
		
		customerMap.put(newMobileNo, custSender);
		
		name = custReciever.getName();
		newMobileNo = custReciever.getMobileNo();
		age = custReciever.getAge();
		amountFund = recieverAmount;
		
		custReciever.setAge(age);
		custReciever.setInitialBalance(recieverAmount);
		custReciever.setMobileNo(newMobileNo);
		custReciever.setName(name);
		
		customerMap.put(newMobileNo, custReciever);
		}
		
		
	}

	@Override
	public boolean validateAccount(String mobileNo) throws BankAccountException {
		// TODO Auto-generated method stub
		Customer customer = customerMap.get(mobileNo);
		if(customer == null)
			return false;
		return true;
	}

}
