
CREATE TABLE MEMBER(
	ID 		VARCHAR(50) PRIMARY KEY, 
	PWD		VARCHAR(50) NOT NULL,
	NAME	VARCHAR(50) NOT NULL,
	EMAIL	VARCHAR(50) UNIQUE,
	AUTH	DECIMAL(1) NOT NULL
);