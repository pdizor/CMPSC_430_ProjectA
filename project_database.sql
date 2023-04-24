# sql file for project

drop table Auto_Loan;
drop table Mortgage_Loan;
drop table Personal_Loan;
drop table Loan;
drop table Customer;

create table Customer(
	ID int,
	name varchar(20) not null,
	primary key (ID)
	);

create table Loan(
	loan_ID int,
	customer_id int,
	loan_amount numeric(10, 2) not null,
	num_payments int not null,
	interest_rate numeric(2, 2) not null 
		check (interest_rate > 0 and interest_rate <= 1),
	amount_paid numeric(10, 2),
	start_date varchar(20) not null,
	end_date varchar(20) not null,
	loan_type varchar(10) not null
		check (loan_type like 'Auto' or 
		       loan_type like 'Mortgage' or
               loan_type like 'Personal'),
	primary key (loan_ID),
	foreign key (customer_ID) references Customer(ID) ON DELETE CASCADE
	);
	
create table Auto_Loan(
	loan_ID int,
	make varchar(20) not null,
	model varchar(20) not null,
	manufac_year numeric(4, 0) not null,
	primary key (loan_ID),
	foreign key (loan_ID) references Loan ON DELETE CASCADE
	);
	
create table Mortgage_Loan(
	loan_ID int,
	house_address varchar(100) not null,
	house_area int not null
		check (house_area > 0),
	num_bedrooms int not null
		check (num_bedrooms >= 0),
	num_bathrooms int not null
		check (num_bathrooms >= 0),
	house_price numeric(10, 2)
		check (house_price >= 0),
	primary key (loan_ID),
	foreign key (loan_ID) references Loan ON DELETE CASCADE
	);
	
create table Personal_Loan(
	loan_ID int,
	loan_purpose varchar(250) not null,
	primary key (loan_ID),
	foreign key (loan_ID) references Loan ON DELETE CASCADE
	);
	
# queries, inserts, etc. to follow
