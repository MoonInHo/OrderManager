INSERT INTO account ( user_id, password, name, phone, role) VALUES ( 'test123', 'testPassword123', '김코딩', '010-1234-5678', 'USER');

INSERT INTO store ( address, address_detail, business_number, business_type, industry, representative_name, store_contact, store_name, store_number, account_id ) VALUES ( '경기 김포시 풍무로146번길 14', 'YJ프라자 2층', 1234567890, '음식점', '양식', '문지호', '0507-1310-9498', '브런치페리아', 1234, 1 );

INSERT INTO menu ( menu_name, price, store_id ) VALUES ( '콥 샐러드', 11900, 1 );
INSERT INTO menu ( menu_name, price, store_id ) VALUES ( '애플 고르곤졸라 파니니', 12500, 1 );
INSERT INTO menu ( menu_name, price, store_id ) VALUES ( '알리오 올리오', 11900, 1 );
INSERT INTO menu ( menu_name, price, store_id ) VALUES ( '삼겹살 필라프', 11900, 1 );
INSERT INTO menu ( menu_name, price, store_id ) VALUES ( '트러플 스테이크 크림 리조또', 16900, 1 );

INSERT INTO customer ( address, contact ) VALUES ( '경기도 김포시 풍무로 128 1층 로비', '010-1234-1234' );
INSERT INTO customer ( address, contact ) VALUES ( '경기도 김포시 풍무로 128 웰라움시티', '010-5678-5678' );

INSERT INTO orders ( store_id, order_time_stamp, total_price, customer_id, customer_request, order_state, order_type, payment_type ) VALUES ( 1 ,TO_CHAR(NOW(), 'YY-MM-DD HH24:MI:SS'), '35700', 1, '스푼포크x', '대기중', '배달', '선결제' );

INSERT INTO order_detail ( order_id, menu_id, quantity, total_menu_price ) VALUES ( 1, 1, 2, 23800 );
INSERT INTO order_detail ( order_id, menu_id, quantity, total_menu_price ) VALUES ( 1, 2, 1, 12500 );
INSERT INTO order_detail ( order_id, menu_id, quantity, total_menu_price ) VALUES ( 1, 3, 2, 23800 );

UPDATE PURCHASE_ORDER
SET total_price = (
    SELECT SUM(total_menu_price)
    FROM order_detail
    WHERE order_id = purchase_order.id
)
WHERE id = 1;

INSERT INTO delivery ( id, order_id, company, driver_name, driver_phone, delivery_tips, delivery_state ) VALUES ( 1, 1, '바로고', '김배달', '010-8282-8282', 3500, '배차' );