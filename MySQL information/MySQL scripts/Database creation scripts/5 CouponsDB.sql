-- Inserting values for the coupons table
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (1,2,'TV','Discount 50%','2010-01-01','2020-01-01',20,325.7,'ELECTRICITY.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (1,1,'Cake','1+1','2020-01-01','2023-01-01',100,29.99,'FOOD.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (2,4,'Resort free joiner','+1 Person For Free','2020-02-08','2021-02-21',0,3000.0,'VACATION.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (2,5,'Fitness GYM','Equipment Rental','2020-01-03','2023-01-03',20,32.99,'SPORT.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (3,7,'Gaming','Computer Chair 50% OFF!','2020-10-11','2023-10-11',7,29.99,'GAMING.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (3,6,'CarX10ris','Car 10% Off + Free Air Freshener','2007-02-10','2013-02-10',3,399.99,'AUTOMOBILE.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (4,2,'Bulbs','6 PEC + 6 PEC LED For Free','2021-01-12','2024-01-12',6,6.0,'ELECTRICITY.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (4,2,'Radio','Radio + Bluetooth Extension','2019-12-01','2021-12-01',19,19.19,'ELECTRICITY.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (5,3,'Michelin Voucher','1 + 1 Guest For Free','2020-02-05','2022-02-05',5,49.99,'RESTAURANT.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (5,8,'Trip to france','All included 3 days in Paris','2020-12-01','2021-12-01',90,499.99,'ATTRACTION.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (6,5,'Macabi Football Game','Free Ticket','2018-05-20','2022-05-20',320,0.00,'SPORT.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (6,7,'Used PC','90% Off Second Hand','2020-02-20','2030-02-20',3,120.99,'GAMING.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (7,1,'Falafel Haderch','+1 Free Falafel Ball','2020-03-23','2050-03-23',99999,0.01,'FOOD.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (7,4,'Ski Trip In The Alphs','Free Flight','2021-10-10','2025-10-10',925,99.99,'VACATION.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (8,3,'Shipodi Hatikva','Extra Tahini','2015-09-08','2025-10-09',1000,0.1,'RESTAURANT.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (8,1,'Rami Levi','100 Shekels For Food In Porim','2020-02-25','2025-02-26',99,85.01,'FOOD.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (9,6,'Yosi\'s Garage','10,000 Kilometers Treatment','2019-02-02','2023-03-03',20,582.57,'AUTOMOBILE.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (9,3,'Gordon Ramsay','Free Meal With The Chef','2022-02-01','2022-02-10',1,9999.99,'RESTAURANT.image');

INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (10,2,'New Hair Drier','20% Discount','2020-08-01','2023-07-02',50,10.50,'ELECTRICITY.image');
INSERT INTO coupon.coupons (company_id,category_id,title,description,start_date,end_date,amount,price,image) 
VALUES (10,8,'Hermon Mountain Ski Day','A Full Day Entry With Equipment','2019-01-20','2024-01-20',100,150.00,'ATTRACTION.image');

SELECT * FROM coupon.coupons;