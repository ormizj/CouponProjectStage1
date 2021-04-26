-- Testing MySQL commands for the CustomersDAO, to then transfer into the Eclipse
SELECT * FROM coupon.customers LEFT JOIN coupon.customers_vs_coupons ON coupon.customers.id = coupon.customers_vs_coupons.customer_id WHERE coupon.customers.id=6;

SELECT coupon.customers_vs_coupons.coupon_id FROM coupon.customers LEFT JOIN coupon.customers_vs_coupons ON coupon.customers.id = coupon.customers_vs_coupons.customer_id WHERE coupon.customers.id=6;

SELECT *
FROM coupon.customers;

SELECT * FROM coupon.coupons
LEFT JOIN coupon.customers_vs_coupons ON coupon.coupons.id = coupon.customers_vs_coupons.coupon_id
LEFT JOIN coupon.customers ON coupon.customers_vs_coupons.customer_id = coupon.customers.id
LEFT JOIN coupon.categories ON coupon.coupons.category_id = coupon.categories.id
WHERE coupon.customers.id=6;

SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image 
FROM coupon.coupons
LEFT JOIN coupon.customers_vs_coupons ON coupon.coupons.id = coupon.customers_vs_coupons.coupon_id
LEFT JOIN coupon.customers ON coupon.customers_vs_coupons.customer_id = coupon.customers.id
LEFT JOIN coupon.categories ON coupon.coupons.category_id = coupon.categories.id
WHERE coupon.customers.id=6;

SELECT *
FROM coupon.customers
WHERE coupon.customers.id = 4;

SELECT coupon.customers.id
FROM coupon.customers
WHERE coupon.customers.email = 'levi@gmail.com'
AND coupon.customers.password = '4321';

SELECT *
FROM coupon.customers
WHERE customers.email = 'levi@gmail.com';


UPDATE coupon.customers  
SET coupon.customers.first_name ='test',  
coupon.customers.last_name = 'test',
coupon.customers.email = 'test',
coupon.customers.password = 'test' 
WHERE id =  5;



SELECT * from coupon.customers_vs_coupons
WHERE customer_id = 5;

SELECT *
FROM coupon.customers_vs_coupons
LEFT JOIN coupon.customers ON coupon.customers.id = coupon.customers_vs_coupons.customer_id
WHERE coupon.customers.id = 5;

SELECT * FROM coupon.customers;

DELETE
FROM coupon.customers
WHERE coupon.c.id=5;


DELETE coupon.customers_vs_coupons
FROM coupon.customers_vs_coupons
LEFT JOIN coupon.customers ON coupon.customers.id = coupon.customers_vs_coupons.customer_id
WHERE coupon.customers.id = 5;

