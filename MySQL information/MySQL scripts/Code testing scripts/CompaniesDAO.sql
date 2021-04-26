-- Testing MySQL commands for the CompaniesDAO, to then transfer into the Eclipse
SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image 
FROM coupon.coupons
LEFT JOIN coupon.customers_vs_coupons ON coupon.coupons.id = coupon.customers_vs_coupons.coupon_id
LEFT JOIN coupon.customers ON coupon.customers_vs_coupons.customer_id = coupon.customers.id
LEFT JOIN coupon.categories ON coupon.coupons.category_id = coupon.categories.id
WHERE coupon.customers.id=6;

SELECT coupon.coupons.id, coupon.coupons.company_id,coupon.categories.name, coupon.coupons.title, coupon.coupons.description, coupon.coupons.start_date, coupon.coupons.end_date,coupon.coupons.amount,coupon.coupons.price,coupon.coupons.image 
FROM coupon.coupons
LEFT JOIN coupon.companies ON coupon.coupons.company_id = coupon.companies.id
LEFT JOIN coupon.categories ON coupon.categories.id = coupon.coupons.category_id
WHERE coupon.companies.id=2;

SELECT *
FROM coupon.companies;

SELECT *
FROM coupon.companies
WHERE companies.id = 6;

SELECT coupon.companies.id
FROM coupon.companies
WHERE coupon.companies.email = 'lasa@gmail.com'
AND coupon.companies.password = '1234';

SELECT *
FROM coupon.companies
WHERE companies.email = 'lasa@gmail.com';


UPDATE coupon.companies  
SET coupon.companies.name ='company.getName()',  
coupon.companies.email = 'company.getPassword()',
coupon.companies.password = 'company.getPassword()' 
WHERE id =  company.getId();




SELECT * from coupon.companies;

DELETE coupon.customers_vs_coupons
FROM coupon.customers_vs_coupons
LEFT JOIN coupon.coupons ON coupon.coupons.id = coupon.customers_vs_coupons.coupon_id
LEFT JOIN coupon.companies ON coupon.coupons.company_id = coupon.companies.id
WHERE coupon.companies.id = 5;

DELETE 
FROM coupon.coupons
WHERE coupon.coupons.company_id = 5;

DELETE
FROM coupon.companies
WHERE coupon.companies.id=5;