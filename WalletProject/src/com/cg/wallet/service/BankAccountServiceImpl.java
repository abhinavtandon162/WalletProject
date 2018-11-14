package com.cg.wallet.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.wallet.dao.BankAccountDAO;
import com.cg.wallet.dao.BankAccountDAOImpl;
import com.cg.wallet.dto.Customer;
import com.cg.wallet.exception.BankAccountException;

public class BankAccountServiceImpl implements BankAccountService {

BankAccountDAO dao  = new BankAccountDAOImpl();
	
	
	@Override
	public void createAccount(Customer customer) {
		dao.createAccount(customer);		
	}

	@Override
	public void deposit(String mobileNo, double amount) {
		dao.deposit(mobileNo, amount);
		
	}

	@Override
	public void withdraw(String mobileNo, double amount) {
		dao.withdraw(mobileNo, amount);
		
	}

	@Override
	public double checkBalance(String mobileNo) {
		return dao.checkBalance(mobileNo);
	}

	@Override
	public void fundTransfer(String sender, String reciever, double amount) {
		dao.fundTransfer(sender, reciever, amount);
		}

	@Override
	public boolean validateName(String name) throws BankAccountException {
				if(name == null)
			throw new BankAccountException("Null value found");
		Pattern p = Pattern.compile("[A-Z]{1}[a-z]{2,10}");
		Matcher m = p.matcher(name); 
		if(m.matches())
			return m.matches();
		System.out.println("Invalid format of Name!");
		return false;
		
	}

	@Override
	public boolean validateAge(int age)  throws BankAccountException {
		try{
			if(age < 0)
				throw new BankAccountException("Age cannot be a negative number");
			else if(age > 0 && age < 18)
				throw new BankAccountException("Age is less then 18");
			else if(age >17)
				return true;
			
		
	} catch (Exception e) {
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public boolean validateMoileNo(String mobileNo) throws BankAccountException{
		try{
			// TODO Auto-generated method stub
			if(mobileNo == null)
				throw new BankAccountException("Null value found");
			Pattern p = Pattern.compile("[6789]{1}[0-9]{9}");
			Matcher m = p.matcher(mobileNo);
			if(m.matches())
				return m.matches(); 
				System.err.println("Mobile Number is Invalid");
				
			
	} catch (Exception e) {
		e.printStackTrace();
	}
		return false;
	}

	@Override
	public boolean validateAmount(double amount) throws BankAccountException {
		if(amount == 0)
			throw new BankAccountException("Null value found");
		String am = String.valueOf(amount);
		if(am.matches("\\d{3,9}\\.\\d{0,4}"))
			return (am.matches("\\d{3,9}\\.\\d{0,4}"));
			System.out.println("Invalid Amount!");
		return false;
	}

	@Override
	public boolean validateAccount(String mobileNo) throws BankAccountException {
		
		return dao.validateAccount(mobileNo);
	}

}
