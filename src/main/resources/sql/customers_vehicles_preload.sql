#
#
# <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<IT IS IMPORTANT TO ADD CUSTOMERS FIRST>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#

# <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADD CUSTOMERS: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

# INSERT INTO car_service.customers (CUSTOMER_NAME, TAX_NUMBER, COUNTRY, CITY, POSTAL_CODE, STREET, CUSTOMER_EMAIL, CUSTOMER_PHONE_NO, CUSTOMER_WEBSITE, ACTIVE_CLIENT, BLOCKED_PAYMENT, PAYMENT_METHOD, TAX_VALUE, CONTACT_PERSON_NAME, CONTACT_PERSON_EMAIL, CONTACT_PERSON_PHONE )
# VALUES
#     ('PolAvServ Ltd.', '1234567890', 'Poland', 'Warsaw', '00-001', 'ul. Solidarności 1', 'info@polavserv.com', '+48123456789', 'www.polavserv.com', true, false, 'CASH', 'TWENTY_THREE', 'John Smith', 'john.smith@example.com', '+48123456789'),
#     ('PolEnGroup Ltd.', '2345678901', 'Poland', 'Krakow', '30-001', 'ul. Grodzka 2', 'contact@polengroup.com', '+48234567890', 'www.polengroup.com', true, false, 'THREE_DAY_TRANSFER', 'TWENTY_THREE', 'Anna Johnson', 'anna.johnson@example.com', '+48234567890'),
#     ('PolNatRail Ltd.', '3456789012', 'Poland', 'Poznan', '60-001', 'ul. Dworcowa 3', 'contact@polnatrail.com', '+48345678901', 'www.polnatrail.com', true, false, 'SEVEN_DAY_TRANSFER', 'ZERO', 'Michael Williams', 'michael.williams@example.com', '+48345678901'),
#     ('PolOilGas Ltd.', '4567890123', 'Poland', 'Wroclaw', '50-001', 'ul. Gazowa 4', 'contact@poloilgas.com', '+48456789012', 'www.poloilgas.com', true, false, 'FOURTEEN_DAY_TRANSFER', 'ZERO', 'Jessica Brown', 'jessica.brown@example.com', '+48456789012'),
#     ('PolOilRef Ltd.', '5678901234', 'Poland', 'Gdansk', '80-001', 'ul. Rafineryjna 5', 'info@poloilref.com', '+48567890123', 'www.poloilref.com', true, false, 'BY_CARD', 'TWENTY_THREE', 'Matthew Davis', 'matthew.davis@example.com', '+48567890123'),
#     ('PolCopper Ltd.', '6789012345', 'Poland', 'Lodz', '90-001', 'ul. Miedziana 6', 'contact@polcopper.com', '+48678901234', 'www.polcopper.com', true, false, 'CONFIRMED_TRANSFER', 'ZERO', 'Emily Wilson', 'emily.wilson@example.com', '+48678901234'),
#     ('PolInsurGrp Ltd.', '7890123456', 'Poland', 'Szczecin', '70-001', 'ul. Ubezpieczeniowa 7', 'contact@polinsurgrp.com', '+48789012345', 'www.polinsurgrp.com', true, false, 'CASH', 'TWENTY_THREE', 'Daniel Taylor', 'daniel.taylor@example.com', '+48789012345'),
#     ('PolBank Ltd.', '8901234567', 'Poland', 'Katowice', '40-001', 'ul. Bankowa 8', 'info@polbank.com', '+48890123456', 'www.polbank.com', true, false, 'THREE_DAY_TRANSFER', 'ZERO', 'Olivia Martinez', 'olivia.martinez@example.com', '+48890123456'),
#     ('PolPetrochem Ltd.', '9012345678', 'Poland', 'Gdynia', '81-001', 'ul. Rafineryjna 9', 'contact@polpetrochem.com', '+48901234567', 'www.polpetrochem.com', true, false, 'SEVEN_DAY_TRANSFER', 'TWENTY_THREE', 'David Brown', 'david.brown@example.com', '+48901234567'),
#     ('PolFertilizer Ltd.', '0123456789', 'Poland', 'Bydgoszcz', '85-001', 'ul. Nawozowa 10', 'info@polfer.com', '+48012345678', 'www.polfer.com', true, false, 'BY_CARD', 'ZERO', 'Sophia Taylor', 'sophia.taylor@example.com', '+48012345678');
#
#
#
# <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<ADD VEHICLES: >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
#
# INSERT INTO car_service.vehicles (REGISTRATION_NO, VIN, MANUFACTURER, MODEL, PRODUCTION_YEAR, MILEAGE, ENGINE_TYPE, GEARBOX_TYPE, ADDITIONAL_INFO, CUSTOMER)
# VALUES
#     ('ABC123', '1HGCM8A633A004452', 'Toyota', 'Camry', '2010', 150000, 'PETROL', 'AUTOMATIC', 'Excellent condition, one owner, regular maintenance', 2),
#     ('DEF456', '3VWRC7AK99M049139', 'Volkswagen', 'Jetta', '2015', 80000, 'DIESEL', 'MANUAL', 'Low mileage, good fuel efficiency', 2),
#     ('GHI789', '5YFBURAE8EP066127', 'Toyota', 'Corolla', '2018', 40000, 'PETROL', 'AUTOMATIC', 'Like new, accident-free', 3),
#     ('JKL012', '1G1BC5AM6G7265005', 'Chevrolet', 'Cruze', '2016', 90000, 'PETROL', 'MANUAL', 'Sporty look, great handling', 3),
#     ('MNO345', '2C3CCAAG5KH532994', 'Chrysler', '300', '2019', 30000, 'PETROL', 'AUTOMATIC', 'Luxurious interior, powerful engine', 4),
#     ('PQR678', 'JHMGE8A39CC006888', 'Honda', 'Fit', '2012', 120000, 'PETROL', 'SEMI_AUTOMATIC', 'Compact design, reliable performance', 4),
#     ('STU901', '1FADP3A20GL288775', 'Ford', 'Focus', '2014', 95000, 'PETROL', 'MANUAL', 'Comfortable ride, spacious interior', 4),
#     ('VWX234', 'WAUZZZAE84A358663', 'Audi', 'A4', '2005', 180000, 'DIESEL', 'AUTOMATIC', 'Solid build quality, premium features', 4),
#     ('YZA567', '1FTFW1AF0EKE42554', 'Ford', 'F-150', '2017', 70000, 'PETROL', 'AUTOMATIC', 'Robust truck, towing capabilities', 5),
#     ('BCD890', '5YJSA1A14FF083442', 'Tesla', 'Model S', '2019', 25000, 'ELECTRIC', 'AUTOMATIC', 'Cutting-edge electric vehicle technology', 5),
#     ('ZBC123', '1HGCM8AZ33A004332', 'Toyota', 'Camry', '2010', 150000, 'PETROL', 'AUTOMATIC', 'Excellent condition, one owner, regular maintenance', 2),
#     ('ZEF456', '3VWRC7AZ99M049229', 'Volkswagen', 'Jetta', '2015', 80000, 'DIESEL', 'MANUAL', 'Low mileage, good fuel efficiency', 2),
#     ('ZHI789', '5YFBURAZ8EP066117', 'Toyota', 'Corolla', '2018', 40000, 'PETROL', 'AUTOMATIC', 'Like new, accident-free', 3),
#     ('ZKL012', '1G1BC5AZ6G7265PP5', 'Chevrolet', 'Cruze', '2016', 90000, 'PETROL', 'MANUAL', 'Sporty look, great handling', 3),
#     ('ZNO345', '2C3CCAAZ5KH532OO4', 'Chrysler', '300', '2019', 30000, 'PETROL', 'AUTOMATIC', 'Luxurious interior, powerful engine', 4),
#     ('ZQR678', 'JHMGE8AZ9CC006II8', 'Honda', 'Fit', '2012', 120000, 'PETROL', 'SEMI_AUTOMATIC', 'Compact design, reliable performance', 4),
#     ('ZTU901', '1FADP3AZ0GL288UU5', 'Ford', 'Focus', '2014', 95000, 'PETROL', 'MANUAL', 'Comfortable ride, spacious interior', 1),
#     ('ZWX234', 'WAUZZZAZ84A358YY3', 'Audi', 'A4', '2005', 180000, 'DIESEL', 'AUTOMATIC', 'Solid build quality, premium features', 6),
#     ('ZZA567', '1FTFW1AZ0EKE42TT4', 'Ford', 'F-150', '2017', 70000, 'PETROL', 'AUTOMATIC', 'Robust truck, towing capabilities', 7),
#     ('ZZZ890', '5YJSA1AZ4FF083RR2', 'Tesla', 'Model S', '2019', 25000, 'ELECTRIC', 'AUTOMATIC', 'Cutting-edge electric vehicle technology', 8),
#     ('AZZ123', '1HGCM8A633A004EE2', 'Toyota', 'Camry', '2010', 150000, 'PETROL', 'AUTOMATIC', 'Excellent condition, one owner, regular maintenance', 2),
#     ('DZZ456', '3VWRC7AK99M049WW9', 'Volkswagen', 'Jetta', '2015', 80000, 'DIESEL', 'MANUAL', 'Low mileage, good fuel efficiency', 1),
#     ('GZZ789', '5YFBURAE8EP066QQ7', 'Toyota', 'Corolla', '2018', 40000, 'PETROL', 'AUTOMATIC', 'Like new, accident-free', 7),
#     ('JZZ012', '1G1BC5AM6G7265615', 'Chevrolet', 'Cruze', '2016', 90000, 'PETROL', 'MANUAL', 'Sporty look, great handling', 8),
#     ('MZZ345', '2C3CCAAG5KH532LL4', 'Chrysler', '300', '2019', 30000, 'PETROL', 'AUTOMATIC', 'Luxurious interior, powerful engine', 9),
#     ('PZZ678', 'JHMGE8A39CC006KK8', 'Honda', 'Fit', '2012', 120000, 'PETROL', 'SEMI_AUTOMATIC', 'Compact design, reliable performance', 9),
#     ('SZZ901', '1FADP3A20GL288JJ5', 'Ford', 'Focus', '2014', 95000, 'PETROL', 'MANUAL', 'Comfortable ride, spacious interior', 4),
#     ('VZZ234', 'WAUZZZAE84A358HH3', 'Audi', 'A4', '2005', 180000, 'DIESEL', 'AUTOMATIC', 'Solid build quality, premium features', 4),
#     ('YZZZ67', '1FTFW1AF0EKE42GG4', 'Ford', 'F-150', '2017', 70000, 'PETROL', 'AUTOMATIC', 'Robust truck, towing capabilities', 5),
#     ('BCZZ90', '5YJSA1A14FF083FF2', 'Tesla', 'Model S', '2019', 25000, 'ELECTRIC', 'AUTOMATIC', 'Cutting-edge electric vehicle technology', 5),
#     ('ZBCZ23', '1HGCM8AZ33A004DD2', 'Toyota', 'Camry', '2010', 150000, 'PETROL', 'AUTOMATIC', 'Excellent condition, one owner, regular maintenance', 2),
#     ('ZEFZ56', '3VWRC7AZ99M049SS9', 'Volkswagen', 'Jetta', '2015', 80000, 'DIESEL', 'MANUAL', 'Low mileage, good fuel efficiency', 2),
#     ('ZHIZ89', '5YFBURAZ8EP066AA7', 'Toyota', 'Corolla', '2018', 40000, 'PETROL', 'AUTOMATIC', 'Like new, accident-free', 3),
#     ('ZKLZ12', '1G1BC5AZ6G7265MM5', 'Chevrolet', 'Cruze', '2016', 90000, 'PETROL', 'MANUAL', 'Sporty look, great handling', 3),
#     ('ZNOZ45', '2C3CCAAZ5KH532NN4', 'Chrysler', '300', '2019', 30000, 'PETROL', 'AUTOMATIC', 'Luxurious interior, powerful engine', 4),
#     ('ZQRZ78', 'JHMGE8AZ9CC006BB8', 'Honda', 'Fit', '2012', 120000, 'PETROL', 'SEMI_AUTOMATIC', 'Compact design, reliable performance', 4),
#     ('ZTUZ01', '1FADP3AZ0GL288VV5', 'Ford', 'Focus', '2014', 95000, 'PETROL', 'MANUAL', 'Comfortable ride, spacious interior', 1),
#     ('ZWXZ34', 'WAUZZZAZ84A358CC3', 'Audi', 'A4', '2005', 180000, 'DIESEL', 'AUTOMATIC', 'Solid build quality, premium features', 6),
#     ('ZZAZ67', '1FTFW1AZ0EKE42XX4', 'Ford', 'F-150', '2017', 70000, 'PETROL', 'AUTOMATIC', 'Robust truck, towing capabilities', 7),
#     ('ZCDZ90', '5YJSA1AZ4FF083ZZ2', 'Tesla', 'Model S', '2019', 25000, 'ELECTRIC', 'AUTOMATIC', 'Cutting-edge electric vehicle technology', 8);
