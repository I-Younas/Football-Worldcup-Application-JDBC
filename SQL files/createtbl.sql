connect to cs421;

create table WorldCup(
year INT not null check (year >= 0),
HostCountry VARCHAR(30),
PRIMARY KEY (year)
);

create table Teams(
Country varchar(30) not null,
websiteURL varchar(30),
groupName varchar(30),
OrganizationName varchar(30),
primary key (Country)
);

create table Stadium(
name varchar(30) not null,
location varchar(30),
capacity int  check (capacity >= 0),
primary key (name)
);

create table Matches(
mID int not null check (mid > 0 and mID < 65),
round varchar(30),
length int check (length <= 120),
numberOFYellowCards int check (numberOFYellowCards >= 0),
dateAndTime date check (dateAndTime >= '2023-01-01' and dateAndTime <= '2023-12-31'),
name varchar(30) not null,
country1 varchar(30) not null,
country2 varchar(30) not null,
primary key (mID),
foreign key (name) references Stadium,
foreign key (country1) references Teams,
foreign key (country2) references Teams
);

create table Participant(
pID int not null check (pid > 0),
name varchar(30),
primary key (pID)
);

create table Referees(
pID int not null check (pid > 0),
yearsOfExperience int check (yearsOfExperience >= 0),
country varchar(30),
primary key (pID),
foreign key (pID) references Participant
);

create table EnforceRule(
pID int not null check (pid > 0),
mID int not null check (mid > 0 and mid < 65),
role varchar(30),
primary key (pID, mID),
foreign key (pID) references Referees,
foreign key (mID) references Matches
);

create table Competitor(
pID int not null check (pid > 0),
dateOfBirth date,
primary key (pID),
foreign key (pID) references Participant
);

create table Players(
pID int not null check (pid > 0),
position varchar(30),
shirtNumber int check (shirtnumber < 100), --2 digits
country varchar(30),
primary key (pID),
foreign key (pID) references Competitor,
foreign key (country) references Teams
);

create table Tickets(
tID int not null check (tid > 0),
price int check (price >= 0),
mID int not null check ( mid > 0 and mid < 65),
primary key (tID),
foreign key (mID) references Matches
);

create table Coaches (
pID int not null check (pid > 0),
role varchar(30),
country varchar(30) not null,
primary key (pID),
foreign key (pID) references Competitor,
foreign key (country) references Teams
);

create table Seats(
seatNumber int not null check (seatNumber >= 0),
name varchar(30) not null,
type varchar(30),
isReserved boolean,
primary key (seatNumber,name),
foreign key (name) references Stadium
);

create table Customer(
email varchar(30) not null,
phoneNumber varchar(30),
name varchar(30),
primary key (email)
);

create table Goals(
mID int not null check (mid > 0 and mid < 65),
occurrence int not null check (occurrence >= 1),
isPenalty boolean,
primary key (mID, occurrence),
foreign key (mID) references Matches
);

create table Reserve(
tID int not null check (tid > 0),
email varchar(30) not null,
seatNumber int not null,
name varchar(30) not null,
dateBought date,
primary key (tID),
foreign key (email) references Customer,
foreign key (seatNumber,name) references Seats,
foreign key (tID) references Tickets
);

create table PlayerStats(
mID int not null check (mid > 0 and mid < 65),
pID int not null check (pid > 0),
StartingTime int check (startingtime >= 0),
RedCardTime int check(redcardtime >= 0 and redcardtime <= 120),
ExactPosition varchar(30),
LeavingTime int check (LeavingTime >= 0 and LeavingTime <= 120),
primary key(mID,pID),
foreign key (mID) references Matches,
foreign key (pID) references Players
);

create table Played(
Year int not null check (year >= 0),
mID int not null check (mid > 0 and mid < 65),
primary key (Year,mID),
foreign key (Year) references WorldCup,
foreign key (mID) references Matches
);

create table Participate(
Year int not null check (year >= 0),
Country varchar(30) not null,
primary key(Year,Country),
foreign key (Year) references WorldCup,
foreign key (Country) references Teams
);

create table ScoredFor(
mID int not null check (mid > 0 and mid < 65),
Occurrence int not null check (Occurrence > 0),
Country varchar(30) not null,
primary key (mID,Occurrence,Country),
foreign key (mID,Occurrence) references Goals,
foreign key (Country) references Teams
);

create Table ScoredBy (
mID int not null check (mid > 0 and mid < 65),
Occurrence int not null check (Occurrence > 0),
pID int not null check (pid > 0),
primary key (mID,Occurrence,pID),
foreign key (mID) references Matches,
foreign key (mID,Occurrence) references Goals
);
