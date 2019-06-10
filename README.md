# java bank example

### Database Design

Connect to soctt account with SQL*PLUS and create the following table.

	create table bankaccount (
		accountno varchar2(50),
		owner varchar2(20) not null,
		balance number,
		kind varchar2(10),
		constraint PK_BANKACCOUNT primary key(accountno),
		constraint CK_BANKACCOUNT_NORMAL 
			CHECK (balance &gt;= CASE WHEN kind='NORMAL' THEN 0 END),
		constraint CK_BANKACCOUNT_KIND CHECK (kind in ('NORMAL', 'MINUS'))
	);  
	
	create table transaction (
	    transactiondate timestamp,
	    kind varchar2(10),
	    amount number,
	    balance number,
	    accountno varchar2(50),
	    constraint FK_TRANSACTION FOREIGN KEY(accountno)
	    	REFERENCES bankaccount(accountno)
	);


Create a trigger that accumulates data in the transaction history table whenever your account balance changes.

	create or replace trigger bank_trigger
	after insert or update of balance on bankaccount
	for each row
	begin
		if :new.balance &gt; :old.balance then
			insert into transaction 
			values 
			(
				systimestamp,
				'DEPOSIT',
				:new.balance - :old.balance,
				:new.balance,
				:old.accountno
			);
		end if;
		if :new.balance &lt; :old.balance then
			insert into transaction 
			values 
			(
				systimestamp,
				'WITHDRAW',
				:old.balance - :new.balance,
				:new.balance,
				:old.accountno
			);
		end if;
	end;
	/

### Have to do
Add the logging library and the Oracle JDBC driver to your classpath.
Download the latest distribution from the following address: http://www.slf4j.org/download.html
After unpacking, add the slf4j-api-1.7.25.jar and slf4j-simple-1.7.25.jar files to the classpath.

