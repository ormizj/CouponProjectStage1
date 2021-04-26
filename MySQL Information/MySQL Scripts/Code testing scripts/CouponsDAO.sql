-- Testing MySQL commands for the CouponsDAO, to then transfer into the Eclipse
SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image 
FROM coupon.coupons
LEFT JOIN coupon.categories ON coupon.categories.id = coupon.coupons.category_id;

SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image 
FROM coupon.coupons
LEFT JOIN coupon.categories ON coupon.categories.id = coupon.coupons.category_id
WHERE coupon.coupons.id=5;

SELECT id
FROM coupon.categories
WHERE coupon.categories.name = 'SPORT';



SELECT *
FROM coupon.coupons
WHERE coupon.coupons.id = 5;

UPDATE coupon.coupons
SET coupon.coupons.category_id = 5,
coupon.coupons.title = 'test',
coupon.coupons.description = 'test',
coupon.coupons.start_date = '00-00-00',
coupon.coupons.end_date = '00-00-00',
coupon.coupons.amount = '5',
coupon.coupons.price = '10',
coupon.coupons.image = 'test'
WHERE id = 5;


SELECT id 
FROM coupon.categories  
WHERE coupon.categories.name = 'GAMING';



SELECT *
FROM coupon.coupons
WHERE coupon.coupons.id = 5;

UPDATE coupon.coupons
SET coupon.coupons.amount = coupon.coupons.amount -1
WHERE coupon.coupons.id=5;


SELECT *
FROM coupon.coupons
LEFT JOIN customers_vs_coupons ON customers_vs_coupons.coupon_id = coupons.id
WHERE coupon.coupons.id = 4;

SELECT *
FROM coupon.coupons
WHERE coupon.coupons.id = 4;


DELETE coupon.customers_vs_coupons
FROM coupon.customers_vs_coupons
LEFT JOIN coupon.coupons ON customers_vs_coupons.coupon_id = coupons.id
WHERE coupon.coupons.id = 4;

DELETE coupon.coupons
FROM coupon.coupons
WHERE coupon.coupons.id = 4;



