package net.java_school.bank;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Transaction implements Serializable {

	private String transactionDate;
	private String transactionTime;
	private String kind;
	private double amount;
	private double balance;

	public Transaction() {}

	public Transaction(String transactionDate,
			String transactionTime,
			String kind,
			double amount,
			double balance) {

		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;        
		this.kind = kind;
		this.amount = amount;
		this.balance = balance;
	}

	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(transactionDate);
		sb.append("|");
		sb.append(transactionTime);
		sb.append("|");
		sb.append(kind);
		sb.append("|");
		sb.append(amount);
		sb.append("|");
		sb.append(balance);

		return sb.toString();
	}

}