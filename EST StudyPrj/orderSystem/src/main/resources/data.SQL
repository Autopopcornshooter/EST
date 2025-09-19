-- Product 데이터 추가
insert into Product (product_name, price)
values ('손톱깎이', 5000);
insert into Product (product_name, price)
values ('샴푸', 15000);
insert into Product (product_name, price)
values ('에어프라이어', 32000);
insert into Product (product_name, price)
values ('텀블러', 17000);
insert into Product (product_name, price)
values ('키보드', 80000);
insert into Product (product_name, price)
values ('공기청정기', 300000);
insert into Product (product_name, price)
values ('수세미', 6000);

--Delivery 데이터 추가
insert into Delivery (destination, deliver_company)
values ('서울', '우체국');
insert into Delivery (destination, deliver_company)
values ('부산', 'CJ');
insert into Delivery (destination, deliver_company)
values ('천안', '쿠팡');
insert into Delivery (destination, deliver_company)
values ('양원', '쿠팡');
insert into Delivery (destination, deliver_company)
values ('공주', '로젠');
insert into Delivery (destination, deliver_company)
values ('전주', '롯데');
insert into Delivery (destination, deliver_company)
values ('인천', '일양로지스');

--order 데이터 추가
insert into Orders (orderer_name, payment_method, delivery_id)
values ('홍길동', '롯데카드', 3);
insert into Orders (orderer_name, payment_method, delivery_id)
values ('김철수', '신한카드', 5);
insert into Orders (orderer_name, payment_method, delivery_id)
values ('윤희수', '삼성카드', 2);
insert into Orders (orderer_name, payment_method, delivery_id)
values ('양관식', '롯데카드', 7);

--orderList 데이터 추가
insert into Order_List (product_id, order_id)
values (1,1);
insert into Order_List (product_id, order_id)
values (3,1);
insert into Order_List (product_id, order_id)
values (4,1);
insert into Order_List (product_id, order_id)
values (6,2);
insert into Order_List (product_id, order_id)
values (4,3);
insert into Order_List (product_id, order_id)
values (5,3);
insert into Order_List (product_id, order_id)
values (6,4);
insert into Order_List (product_id, order_id)
values (1,4);
insert into Order_List (product_id, order_id)
values (2,4);
insert into Order_List (product_id, order_id)
values (3,4);
