CREATE TABLE CARD (
	id_card BIGINT AUTO_INCREMENT,
	card_number VARCHAR(20) NOT NULL,
	active BOOLEAN NOT NULL,
	balance DECIMAL(10.2) NOT NULL,
	customer_id BIGINT NOT NULL,
    KEY customer_id (customer_id),
    CONSTRAINT card_customer_fk FOREIGN KEY (customer_id)
    REFERENCES customer (id_customer),
    PRIMARY KEY (id_card)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

INSERT INTO CARD (card_number, active, balance, customer_id) VALUES("7813747679902724", true, 100.0, 1);
INSERT INTO CARD (card_number, active, balance, customer_id) VALUES("7294388306634386", true, 1400.0, 2);
INSERT INTO CARD (card_number, active, balance, customer_id) VALUES("7145393493136287", true, 0.0, 3);
INSERT INTO CARD (card_number, active, balance, customer_id) VALUES("7932812411723048", true, 530.0, 4);
INSERT INTO CARD (card_number, active, balance, customer_id) VALUES("7242457894562268", true, 50.0, 5);
INSERT INTO CARD (card_number, active, balance, customer_id) VALUES("7234563494564456", true, 2350.0, 3);
