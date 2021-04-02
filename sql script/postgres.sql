-- Database: MobileCall

-- DROP DATABASE "MobileCall";
delete from transfert;
delete from MobileMoneyReel;
delete from CreditReel;
delete from ForfaitAchete;
delete from ForfaitReel;

CREATE DATABASE "MobileCall"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'French_France.1252'
    LC_CTYPE = 'French_France.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "MobileCall"
    IS 'Base relationnelle avec transaction';

------------------------------TABLES------------------------------------
create table operateur(
	id serial PRIMARY KEY,
	code varchar(50),
	nom varchar(50)
);

insert into operateur (code,nom) values('034','Telma');
insert into operateur (code,nom) values('021','DunCall');
insert into operateur (code,nom) values('029','BonCall');
insert into operateur (code,nom) values('069','VoKall');

create table Offre (
	id serial PRIMARY KEY,
	idoperateur integer,
	nom varchar(50) ,
	priorite integer,
	duree integer,
	prix real
);

insert into Offre(idoperateur,nom,priorite,duree,prix) values (0,'Telma Mora',10,1,500);
insert into Offre(idoperateur,nom,priorite,duree,prix) values (0,'Test 2000 Ar',10,1,500);

create table InfoOffre (
	id serial PRIMARY KEY,
	idOffre integer,
	nom varchar(50),
	valeur integer,
	FOREIGN KEY(idOffre) REFERENCES Offre(id)
);

insert into InfoOffre(idOffre,nom,valeur) values (1,'Appel',500);
insert into InfoOffre(idOffre,nom,valeur) values (1,'internet',10);
insert into InfoOffre(idOffre,nom,valeur) values (1,'SMS',10);

insert into InfoOffre(idOffre,nom,valeur) values (2,'Appel',1000);
insert into InfoOffre(idOffre,nom,valeur) values (2,'internet',20);
insert into InfoOffre(idOffre,nom,valeur) values (2,'SMS',20);

create table Tarif (
	id serial PRIMARY KEY,
	idOffre integer,
	destination varchar(50),
	type varchar(50),
	valeur integer,
	foreign key(idOffre) references Offre(id)
);

insert into tarif(idOffre,destination,type,valeur) values (1,'meme operateur','Appel',1);
insert into tarif(idOffre,destination,type,valeur) values (1,'autre operateur','Appel',2);

create table Transfert (
	id serial PRIMARY KEY,
	iduser integer ,
	montant real,
	typeTransfert varchar(20),
	dateTransfert TIMESTAMP,
	status VARCHAR(20)
);

create table ForfaitAchete(
	id serial PRIMARY KEY,
	idUser integer,
	idOffre real,
	dateAchat TIMESTAMP
);

create table ForfaitReel(
	id serial PRIMARY KEY,
	iduser integer ,
	idForfaitAchete integer,
	type VARCHAR(50),
	reste TIME
);

create table MobileMoneyReel(
	id serial PRIMARY KEY,
	idUser integer,
	montantMMoneyReel real,
	dateMMoneyReel TIMESTAMP
);

create table CreditReel(
	id serial PRIMARY KEY,
	idUser integer,
	montantCreditReel real,
	dateCreditReel TIMESTAMP
);
create table Typeinfo(
	id serial PRIMARY KEY,
	nom VARCHAR(30),
	type VARCHAR(30)
);

insert into Typeinfo(nom,type) values ('appel','appel');
insert into Typeinfo(nom,type) values ('SMS','SMS');
insert into Typeinfo(nom,type) values ('internet','internet');

Typeinfo(id,nom,type)  (0,facebobaka,internet),(0,Appel,Appel),(2,in

select * from MobileMoneyReel where iduser = '1' and dateMMoneyReel=(select max(dateMMoneyReel)from MobileMoneyReel) and id=(select max(id) from MobileMoneyReel)

select MobileMoneyReel.* from MobileMoneyReel INNER JOIN(select max(id) as maxId,iduser,max(dateMMoneyReel) as maxdate from MobileMoneyReel group by iduser) ms on MobileMoneyReel.id=ms.maxId and MobileMoneyReel.iduser=ms.iduser  and MobileMoneyReel.dateMMoneyReel=ms.maxdate

insert into transfert(iduser,montant,typetransfert,datetransfert,status) values(1,5000000,'credit','2021-03-03','null')

insert into Creditreel (iduser,montantcreditreel,datecreditreel) values(1,5000000,'2021-03-03')