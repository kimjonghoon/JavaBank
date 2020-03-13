# java bank example

### Database Design

Connect to soctt account with SQL*PLUS and create the following tables.

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
Download the Oracle JDBC driver(ojdbc6.jar) from the link below.
https://www.oracle.com/database/technologies/jdbcdriver-ucp-downloads.html#license-lightbox

Download the latest slf4j-api-x.x.xx.jar and slf4j-simple-x.x.xx.jar from the link below.
http://www.slf4j.org/download.html

Add ojdbc6.jar, slf4j-api-x.x.xx.jar and slf4j-simple-x.x.xx.jar files to the jars/ directory.

src/
├── net
│   └── java_school
│       └── bank
│            ├── Account.java
│            ├── Bank.java
│            ├── BankDao.java
│            ├── BankUi.java
│            ├── ShinhanBank.java
│            ├── ShinhanBankDao.java
│            └── Transaction.java
├── simplelogger.properties
jars/
├── ojdbc6.jar
├── slf4j-api-1.7.30.jar
└── slf4j-simple-1.7.30.jar

### Compile

CP=jars/slf4j-api-1.7.30.jar
CP+=:jars/slf4j-simple-1.7.30.jar
javac -cp $CP -d out -sourcepath src $(find src -name "*.java")

### Copy properties to out directory

cp src/simplelogger.properties out/

### Run

CP+=:jars/ojdbc6.jar
java -cp $CP:out net.java_school.bank.BankUi
