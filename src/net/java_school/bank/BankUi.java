package net.java_school.bank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class BankUi {

	private Bank bank = new MyBank();

	private String readCommandLine() throws IOException {
		InputStreamReader isr = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(isr);
		String input = br.readLine();
		return input;
	}

	public void startWork() {

		String menu = null;

		do {
			System.out.println(" 1 ** New Account registration    ");
			System.out.println(" 2 ** All Accounts    ");
			System.out.println(" 3 ** Deposit    ");
			System.out.println(" 4 ** Withdraw    ");
			System.out.println(" 5 ** Transfer    ");
			System.out.println(" 6 ** Transaction history    ");
			System.out.println(" q ** Quit    ");
			System.out.println(" ********************** ");
			System.out.print(">> ");

			try {
				menu = readCommandLine();

				String accountNo = null;
				String name = null;
				String kind = null;
				double amount = 0;

				if (menu.equals("1")) {
					//New Account registration
					System.out.print("Enter the account number of the account you want to create: ");
					accountNo = this.readCommandLine();
					System.out.print("Enter the owner name of the account you want to create: ");
					name = this.readCommandLine();
					System.out.print("Please select an account kind. noraml (n), minus (m): normal (n) : : ");
					kind = this.readCommandLine();
					if (kind != null && kind.equals("m")) {
						bank.addAccount(accountNo, name, Account.MINUS);						
					} else {
						bank.addAccount(accountNo, name, Account.NORMAL);
					}

				} else if (menu.equals("2")) {
					//All accounts
					List<Account> accounts = bank.getAccounts();
					for (Account account : accounts) {
						System.out.println(account);
					}
				} else if (menu.equals("3")) {
					//Deposit
					System.out.print("Please enter your account number: ");
					accountNo = this.readCommandLine();
					System.out.print("Please enter deposit amount: ");
					amount = Integer.parseInt(this.readCommandLine());
					bank.deposit(accountNo, amount);
				} else if (menu.equals("4")) {
					//Withdraw
					System.out.print("Please enter your account number: ");
					accountNo = this.readCommandLine();
					System.out.print("Please enter withdraw amount: ");
					amount = Integer.parseInt(this.readCommandLine());
					bank.withdraw(accountNo, amount);
				} else if (menu.equals("5")) {
					//Transfer
					System.out.print("Please enter your withdrawal account number: ");
					String from = this.readCommandLine();
					System.out.print("Please enter your deposit account number: ");
					String to = this.readCommandLine();
					System.out.print("Enter transfer amount: ");
					amount = Integer.parseInt(this.readCommandLine());
					bank.transfer(from, to, amount);
				} else if (menu.equals("6")) {
					//Transaction history
					System.out.print("Please enter your account number: ");
					accountNo = this.readCommandLine();
					List<Transaction> transactions = bank.getTransactions(accountNo);
					for (Transaction transaction : transactions) {
						System.out.println(transaction);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println();
		} while (!menu.equals("q"));

	}

	public static void main(String[] args) throws Exception {
		BankUi ui = new BankUi();
		ui.startWork();
	}

}
