connect to cs421;

insert into worldcup values(2023, 'Canada');
insert into worldcup values(2001, 'Columbia');
insert into worldcup values(2018,'Germany');
insert into worldcup values(2004,'England');
insert into worldcup values(2008, 'Belgium');
insert into worldcup values(2012, 'Mexico');

insert into teams values('Brazil', 'brazil.com', 'Group A', 'TeamBrazil');
insert into teams values('France', 'france.com', 'Group B', 'TeamFrance');
insert into teams values('Belgium','belgium.com','Group C','TeamBelgium');
insert into teams values('England','england.com','Group D','TeamEngland');
insert into teams values('Mexico','mexico.com','Group E','TeamMexico');
insert into teams values('Argentina','argentina.com','Group A','TeamArgentina');
insert into teams values('Morocco','morocco.com','Group B','TeamMorocco');
insert into teams values('Algeria','algeria.com','Group C','TeamAlgeria');
insert into teams values('Canada', 'canada.ca', 'Group A', 'TeamCanada');

insert into stadium values('Bell Centre', 'Montreal', 20000);
insert into stadium values('Olympic Stadium','Montreal',40000);
insert into stadium values('Commonwealth Stadium','Edmonton',35000);
insert into stadium values('Rogers Centre','Toronto',41500);
insert into stadium values('Mosaic Stadium','Regina',30000);

insert into matches values(01, 'Quarter Final', 120, 4, '2023-02-18', 'Bell Centre', 'Brazil', 'France');
insert into matches values(02,'Semi Final',90,2,'2023-02-19','Olympic Stadium','Algeria','Mexico');
insert into matches values(06, 'Group Stage', 100, 1, '2023-02-15', 'Bell Centre', 'Canada', 'France');
insert into matches values(07, 'Quarter Final', 90, 0, '2023-02-17', 'Olympic Stadium', 'Algeria', 'Canada');
insert into matches values(10,'Group Stage',90,3,'2023-01-12','Mosaic Stadium','Belgium','Argentina');
insert into matches values(08, 'Semi- Final', 120, 1,'2023-02-28', 'Bell Centre', 'Canada', 'Brazil');

insert into participant values(200, 'Brazil Player 1');
insert into participant values(201,'France Player 1');
insert into participant values(202,'England Player 1');
insert into participant values(203,'Mexican Player 1');
insert into participant values(204,'Algeria Player 1');
insert into participant values(300,'England Coach');
insert into participant values(301,'Brazil Coach');
insert into participant values(302,'Argentina Coach');
insert into participant values(303,'Belgium Coach');
insert into participant values(304,'Argentina Coach');
insert into participant values(100,'English Referee 1');
insert into participant values(101,'Mexican Referee 1');
insert into participant values(102,'Argentinian Referee 1');
insert into participant values(103,'French Referee 1');
insert into participant values(104,'Belgian Referee 1');
insert into participant values(105, 'Moroccan Referee 1');
insert into participant values(222, 'Christine Sinclair');
insert into participant values(205, 'France Player 2');
insert into participant values(206,'Argentinian Player 1');
insert into participant values(207, 'Belgian Player 1');
insert into participant values(106,'Belgian Referee 1');
insert into participant values(208, 'Argentinian Player 2');
insert into participant values(209, 'Belgian Player 2');
insert into participant values(107, 'French Referee 1');

insert into referees values(100, 5, 'England');
insert into referees values(101,6,'Mexico');
insert into referees values(102,7,'Argentina');
insert into referees values(103,8,'France');
insert into referees values(104,9,'Belgium');
insert into referees values(105,10,'Morocco');
insert into referees values(106, 2, 'Belgium');
insert into referees values(107, 1, 'France');

insert into enforcerule values(100, 01, 'Head Referee');
insert into enforcerule values(101,02,'Assistant  Referee');
insert into enforcerule values(100, 06, 'Head Referee');
insert into enforcerule values(101, 07, 'Head Referee');
insert into enforcerule values(106,10,'Head Referee');
insert into enforcerule values(107,10,'Assistant Referee');
insert into enforcerule values(101,08,'Head Referee');

insert into competitor values(200, '2000-01-01');
insert into competitor values(201,'2004-01-02');
insert into competitor values(202,'2000-07-01');
insert into competitor values(203,'2002-04-12');
insert into competitor values(204,'2001-05-11');
insert into competitor values(300,'2003-01-10');
insert into competitor values(301,'2002-02-15');
insert into competitor values(302,'2004-05-15');
insert into competitor values(303,'2001-08-19');
insert into competitor values(304,'2002-03-16');
insert into competitor values(222, '1983-06-12');
insert into competitor values(205,'1997-06-15');
insert into competitor values(206,'2000-07-25');
insert into competitor values(207,'2000-08-25');
insert into competitor values(208, '1990-04-17');
insert into competitor values(209, '1960-09-29');

insert into players values(200, 'GoalKeeper', 01,'Brazil');
insert into players values(201,'Defender',02,'France');
insert into players values(202,'Midfielder',03,'Algeria');
insert into players values(203,'Attacker',04, 'Mexico');
insert into players values(204,'Defender',05, 'Algeria');
insert into players values(222, 'Attacker', 12, 'Canada');
insert into players values(205, 'Defender', 06, 'France');
insert into players values(206,'Attacker',07,'Argentina');
insert into players values(207,'GoalKeeper',01,'Belgium');
insert into players values(208,'Midfielder',16,'Argentina');
insert into players values(209,'Midfielder',15,'Belgium');

insert into tickets values(600,500,01);
insert into tickets values(601,700,02);
insert into tickets values(602,700,02);
insert into tickets values(603,700,02);
insert into tickets values(604,500,06);
insert into tickets values(605, 1000, 07);
insert into tickets values(606, 400, 10);
insert into tickets values(607, 400, 10);
insert into tickets values(608, 400, 10);
insert into tickets values(609, 700, 08);


insert into coaches values(300,'Head Coach','England');
insert into coaches values(301,'Assistant Coach','Brazil');
insert into coaches values(302,'Head Coach','Argentina');
insert into coaches values(303,'Head Coach','Belgium');
insert into coaches values(304,'Head Coach','Argentina');


insert into seats values(01,'Bell Centre','Standard',TRUE);
insert into seats values(02,'Olympic Stadium','Executive',TRUE);
insert into seats values(03,'Commonwealth Stadium','Standard',TRUE);
insert into seats values(04,'Rogers Centre','Executive',TRUE);
insert into seats values(05,'Mosaic Stadium','Standard',FALSE);
insert into seats values(03,'Olympic Stadium','Standard',TRUE);
insert into seats values(04,'Olympic Stadium','Standard',TRUE);
insert into seats values(01,'Mosaic Stadium','Standard',TRUE);
insert into seats values(02,'Mosaic Stadium','Standard',TRUE);

insert into customer values('x@a.ca','123456789','Max');
insert into customer values('a@x.ca','155555789','Alex');
insert into customer values('z@y.ca','123222289','Tom');
insert into customer values('h@m.ca','432156789','Smith');
insert into customer values('p@o.ca','999956789','Hailey');

insert into goals values(01,1,TRUE);
insert into goals values(02,1,FALSE);
insert into goals values(06,1,FALSE);
insert into goals values(07,1,TRUE);
insert into goals values(10, 1, FALSE);
insert into goals values(10, 2, FALSE);
insert into goals values(10, 3, TRUE);
insert into goals values(10, 4, TRUE);
insert into goals values(08,1,TRUE);

insert into reserve values(600, 'x@a.ca', 01,'Bell Centre', '2023-02-19');
insert into reserve values(601, 'a@x.ca', 02, 'Olympic Stadium', '2023-01-04');
insert into reserve values(602, 'z@y.ca', 03, 'Olympic Stadium', '2023-01-04');
insert into reserve values(603, 'h@m.ca', 04, 'Olympic Stadium', '2023-01-04');
insert into reserve values(604, 'p@o.ca', 01, 'Bell Centre', '2023-02-10');
insert into reserve values(605, 'x@a.ca', 02,'Olympic Stadium', '2023-02-01');
insert into reserve values(606,'x@a.ca',01,'Mosaic Stadium','2023-01-12');
insert into reserve values(607,'h@m.ca',02,'Mosaic Stadium','2023-01-11');

insert into playerstats values(01,200,0,NULL,'Goalkeeper',90);
insert into playerstats values(01,201,0,51,'Right Back',51);
insert into playerstats values(02,202,0,NULL,'Central Midfielder',90);
insert into playerstats values(02,203,0,NULL,'Striker',80);
insert into playerstats values(07,204,0,NULL,'Right Back',70);
insert into playerstats values(06, 222, 0, NULL, 'Striker', 90);
insert into playerstats values(07,222,0,NULL,'Striker',90);
insert into playerstats values(10,206,0,NULL,'Striker',90);
insert into playerstats values(10,207,0,NULL,'Goalkeeper',90);
insert into playerstats values(10, 208, 0, 5, 'Central Midfielder', 5 );
insert into playerstats values(10, 209, 0, NULL, 'Central Midfielder', 90);
insert into playerstats values(08,222,0,NULL,'Striker',120);
insert into playerstats values(08, 200, 0, NULL, 'GoalKeeper', 120);

insert into played values(2023,01);
insert into played values(2023,02);
insert into played values(2023,06);
insert into played values(2023,07);
insert into played values(2023,10);
insert into played values(2023,08);


insert into participate values(2023,'Canada');
insert into participate values(2023,'France');
insert into participate values(2023,'Morocco');
insert into participate values(2023,'Argentina');
insert into participate values(2023,'Mexico');
insert into participate values(2023,'Belgium');
insert into participate values(2023,'Brazil');
insert into participate values(2023,'Algeria');
insert into participate values(2023, 'England');

insert into scoredfor values(01,1,'Brazil');
insert into scoredfor values(02,1,'Algeria');
insert into scoredfor values(06,1,'Canada');
insert into scoredfor values(07,1, 'Algeria');
insert into scoredfor values(10, 1, 'Belgium');
insert into scoredfor values(10, 2, 'Argentina');
insert into scoredfor values(10, 3, 'Belgium');
insert into scoredfor values(10,4,'Belgium');
insert into scoredfor values(08,1,'Canada');

insert into scoredby values(01,1,200);
insert into scoredby values(02,1,204);
insert into scoredby values(06,1,222);
insert into scoredby values(07,01,204);
insert into scoredby values(10,1,207);
insert into scoredby values(10,2,206);
insert into scoredby values(10,3,209);
insert into scoredby values(10,4,207);
insert into scoredby values(08,1,222);

