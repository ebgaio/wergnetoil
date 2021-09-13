CREATE TABLE CUSTOMER (
	id_customer BIGINT PRIMARY KEY AUTO_INCREMENT,
	name_customer VARCHAR(30) NOT NULL,
	last_name VARCHAR(30) NOT NULL,
	active BOOLEAN NOT NULL,
	email VARCHAR(50) NOT NULL,
	phone VARCHAR(30),
    street1 VARCHAR(30),
    street2 VARCHAR(30),
    county VARCHAR(30),
    city VARCHAR(30),
    eir_code VARCHAR(30),
    country VARCHAR(30)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO CUSTOMER (name_customer, last_name, active, email, phone, street1, street2, county, city, eir_code, country) VALUES ('Evandro', 'Gaio', true, 'evandro@gmail.com', '3583125066', "5 OConnel", "Centre", "Dublin", "Dublin", "D6W", "Ireland");
INSERT INTO CUSTOMER (name_customer, last_name, active, email, phone, street1, street2, county, city, eir_code, country) VALUES ('Andressa', 'Finotti', true, 'andressa@gmail.com', '553732164272', "5 Port", "Donegal", "Dublin", "Dublin", "D6W", "Ireland");
INSERT INTO CUSTOMER (name_customer, last_name, active, email, phone, street1, street2, county, city, eir_code, country) VALUES ('Ana', 'Gois', true, 'ana@uol.com.br', '553432456874', "5 Tirol", "Clonsila", "Morris", "Dublin", "D2", "Ireland");
INSERT INTO CUSTOMER (name_customer, last_name, active, email, phone, street1, street2, county, city, eir_code, country) VALUES ('Paula', 'Mendes', true, 'paula@pop.com.br', '551132654782', "5 Lest", "Centre", "Dublin", "Dublin", "D3", "Ireland");
INSERT INTO CUSTOMER (name_customer, last_name, active, email, phone, street1, street2, county, city, eir_code, country) VALUES ('Oto', 'Costa', true, 'oto@ibm.com', '553136548521', "5 Kirney", "Centre", "Dublin", "Dublin", "D2", "Ireland");

