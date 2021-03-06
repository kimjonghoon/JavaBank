package net.java_school.bank;

import java.io.Serializable;
import java.util.List;

public interface Bank extends Serializable {

	public void addAccount(String accountNo, String name, String kind);

	public Account getAccount(String accountNo);

	public List<Account> findAccountByName(String name);

	public List<Account> getAccounts();

	public void deposit(String accountNo, double amount);

	public void withdraw(String accountNo, double amount);

	public void transfer(String from, String to, double amount);

	public List<Transaction> getTransactions(String accountNo); 

}