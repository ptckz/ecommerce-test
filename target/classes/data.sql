INSERT INTO product (name, value) values 
('Iphone X', 5599),
('Iphone SE', 2599),
('Iphone SE', 2599),
('Iphone 8', 4599),
('Iphone 7', 3499);

INSERT INTO item (quantity, product_id) values
(1, 1),
(3, 4);


INSERT INTO cart (amount) values
(13396);

INSERT INTO cart_item (cart_id, item_id) values
(1, 1),
(1, 2);

/*
DROP TABLE CART;
DROP TABLE CART_ITEMS;
DROP TABLE ITEM;
DROP TABLE PRODUCT;

*/