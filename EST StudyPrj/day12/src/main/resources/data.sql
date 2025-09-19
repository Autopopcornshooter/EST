--Subscription 데이터 추가
insert into subscription (content_name) values('컨텐츠1');
insert into subscription (content_name) values('컨텐츠2');
insert into subscription (content_name) values('컨텐츠3');
-- member 데이터 추가
insert into member (name,  subscription_id) values ('홍길동', 1);
insert into member (name,  subscription_id) values ('김철수', 1);
insert into member (name,  subscription_id) values ('이영희', 2);
insert into member (name,  subscription_id) values ('손흥민', 3);