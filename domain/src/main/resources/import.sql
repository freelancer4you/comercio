insert into leadingindex(id,name,isin,wkn,searchkey) values(1,'DAX','DE0008469008','846900','GDAXI');
insert into leadingindex(id,name,isin,wkn,searchkey) values(2,'DOW JONES',null,'969420','^DJI');

-- DAX
--insert into stock(id,name,isin,wkn,searchkey,currency) values(1,'DAX','DE0008469008','846900','^GDAXI','USD');
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(2,'Adidas AG','DE000A1EWWW0',null,'ADS.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(3,'Allianz','DE0008404005',null,'ALV.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(4,'BASF','DE000BASF111',null,'BAS.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(5,'Bayer','DE000BAY0017',null,'BAYN.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(6,'Beiersdorf','DE0005200000',null,'BEI.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(7,'BMW','DE0005190003',null,'BMW.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(8,'Commerzbank','DE000CBK1001',null,'CBK.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(9,'Continental','DE0005439004',null,'CON.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(10,'Daimler','DE0007100000',null,'DAI.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(11,'Deutsche Bank','DE0005140008',null,'DBK.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(12,'Deutsche Boerse','DE0005810055',null,'DB1.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(13,'Deutsche Post','DE0005552004',null,'DPW.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(14,'Deutsche Telekom','DE0005557508',null,'DTE.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(15,'E.ON','DE000ENAG999',null,'EOAN.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(16,'Fresenius','DE0005785604',null,'FME.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(17,'Fresenius Medical Care','DE0005785802',null,'FRE.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(18,'HeidelbergCement','DE0006047004',null,'HEI.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(19,'Henkel vz','DE0006048432',null,'HEN3.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(20,'Infineon','DE0006231004',null,'IFX.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(21,'Linde','DE0006483001',null,'LIN.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(22,'Lufthansa','DE0008232125',null,'LHA.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(23,'Merck','DE0006599905',null,'MRK.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(24,'Muenchener Rueck','DE0008430026',null,'MUV2.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(25,'ProSiebenSat1 Media','DE000PSM7770',null,'PSM.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(26,'RWE','DE0007037129',null,'RWE.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(27,'SAP','DE0007164600',null,'SAP.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(28,'Siemens','DE0007236101',null,'SIE.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(29,'thyssenkrupp','DE0007500001',null,'TKA.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(30,'Volkswagen vz','DE0007664039',null,'VNA.DE','USD',1);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(31,'Vonovia','DE000A1ML7J1',null,'VOW3.DE','USD',1);

-- DOW-Jones
--insert into stock(id,name,isin,wkn,searchkey,currency,leadingIndex) values(32,'DOW JONES',null,'969420','^DJI','USD');
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(33,'3M','US88579Y1010',null,'MMM','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(34,'American Express','US0258161092',null,'AXP','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(35,'Apple','US0378331005',null,'AAPL','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(36,'Boeing','US0970231058',null,'BA','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(37,'Caterpillar','US1491231015',null,'CAT','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(38,'Chevron','US1667641005',null,'CVX','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(39,'Cisco Systems','US17275R1023',null,'CSCO','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(40,'Coca-Cola','US1912161007',null,'KO','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(41,'DuPont','US2635341090',null,'DFT','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(42,'Exxon Mobil','US30231G1022',null,'XOM','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(43,'General Electric','US3696041033',null,'GE','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(44,'Goldman Sachs','US38141G1040',null,'GS','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(45,'Home Depot','US4370761029',null,'HD','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(46,'IBM','US4592001014',null,'IBM','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(47,'Intel','US4581401001',null,'INTC','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(48,'JohnsonJohnson','US4781601046',null,'JNJ','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(49,'JPMorgan','US46625H1005',null,'JPM','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(50,'McDonalds','US5801351017',null,'MCD','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(51,'MerckCo','US58933Y1055',null,'MRK','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(52,'Microsoft','US5949181045',null,'MSFT','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(53,'Nike','US6541061031',null,'NKE','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(54,'Pfizer','US7170811035',null,'PFE','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(55,'ProcterGamble','US7427181091',null,'PG','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(56,'Travelers Companies','US89417E1091',null,'TRV','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(57,'United Technologies','US9130171096',null,'UNH','NKE',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(58,'UnitedHealth Group','US91324P1021',null,'UTX','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(59,'Verizon','US92343V1044',null,'VZ','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(60,'Visa','US92826C8394',null,'V','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(61,'Walmart','US9311421039',null,'WMT','USD',2);
insert into stock(id,name,isin,wkn,searchkey,currency,leadingindex_id) values(62,'Walt Disney','US2546871060',null,'DIS','USD',2);


insert into bankaccount(id,institute, iban, bic) values (1,'DKB', 'DE89 3704 0044 0532 0130 00','DABAIE2D');
insert into bankaccount(id,institute, iban, bic) values (2,'Deutsche Bank', 'DE89 3894 0044 0532 0130 00','OKOYFIHH');
insert into bankaccount(id,institute, iban, bic) values (3,'Commerzbank', 'DE89 3894 0044 0532 7630 00','OKOYFIHH');
insert into bankaccount(id,institute, iban, bic) values (4,'Sparkasse', 'DE89 3894 0044 0532 0176 00','OKOYFIHH');
insert into bankaccount(id,institute, iban, bic) values (5,'Raiffeisenbank', 'DE89 3894 0044 1545 0130 00','OKOYFIHH');

insert into account_table(id,email, login, password) values (1,'frodo@gmx.de', 'frodo','1243');
insert into account_table(id,email, login, password) values (2,'goldi@gmx.de', 'goldi','123434');
insert into account_table(id,email, login, password) values (3,'müller@bayern.de', 'müller','123434');
insert into account_table(id,email, login, password) values (4,'neymar@real.de', 'neymar','123434');
insert into account_table(id,email, login, password) values (5,'klose@juventus.de', 'klose','123434');

insert into user_table(id,firstname, lastname,account_id,bankaccount_id) values (1,'Frodo', 'Baggings',1,1);
insert into user_table(id,firstname, lastname,account_id,bankaccount_id) values (2,'Andre', 'Goldmann',2,2);
insert into user_table(id,firstname, lastname,account_id,bankaccount_id) values (3,'Thomas', 'Müller',3,3);
insert into user_table(id,firstname, lastname,account_id,bankaccount_id) values (4,'Silva', 'Neymar',4,4);
insert into user_table(id,firstname, lastname,account_id,bankaccount_id) values (5,'Miroslav', 'Klose',5,5);

-- MR Dude --> BMW, Beiersdorf, RWE, OIL
-- Russelrunner --> DAX, 
-- Myrave --> OIL 
-- Die Katze --> Illumina
-- Schakar --> Fossil
-- Stormwaere --> GBP/JPY, Zalando
-- Blanchet,


 


