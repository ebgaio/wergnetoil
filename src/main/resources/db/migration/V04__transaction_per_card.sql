CREATE TABLE TRANSACTION (
    id_transaction BIGINT AUTO_INCREMENT,
    description VARCHAR(50) NOT NULL,
    value_Transaction DECIMAL(10.2) NOT NULL,
   	date_Credit DATE,
	date_Debit DATE,
	customer_id BIGINT NOT NULL,
   	KEY customer_id (customer_id),
   	bank_id BIGINT,
   	KEY bank_id (bank_id),
   	card_id BIGINT,
   	KEY card_id (card_id),
    CONSTRAINT customer_transaction_fk FOREIGN KEY (customer_id)
    REFERENCES customer (id_customer),
    CONSTRAINT bank_transaction_fk FOREIGN KEY (bank_id)
    REFERENCES bank (id_bank),
    CONSTRAINT card_transaction_fk FOREIGN KEY (card_id)
    REFERENCES card (id_card),
    PRIMARY KEY (id_transaction)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("Today is a beautiful day.", 16234.0, null, '2017-11-21', 1, 1, 1);
INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("I love you so much", 1240.0, '2017-02-20', null, 2, 2, 2);
INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("You is love of my live", 340.0, '2018-10-17', null, 1, 3, 1);
INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("I can do this.", 50.0, '2016-06-29', null, 2, 4, 2);
INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("You is the best.", 1850.0, '2017-09-26', null, 3, 3, 3);
INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("The first gas Station.", 50.0, null, '2017-07-28', 3, 1, 6);
INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("Sunshine.", 25.0, null, '2016-02-08', 4, 1, 4);
INSERT INTO TRANSACTION (description, value_Transaction, date_Credit, date_Debit, customer_id, bank_id, card_id) VALUES("Raise Universe.", 15.0, null, '2019-12-13', 5, 1, 5);
