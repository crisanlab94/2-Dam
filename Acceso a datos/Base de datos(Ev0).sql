create database Ev0;
use Ev0;
create table usuario (
idUsuario Varchar(15) primary key,
nombre varchar (15),
email varchar (20),
fecha date
);

create table canal (
idCanal varchar (15) primary key,
titulo varchar (15),
fechaCreacion date,
idUsuario varchar (15),
constraint fk_idUsuario foreign key (idUsuario) references usuario (idUsuario)
);

create table sigueCanal(
idUsuario varchar (15),
idCanal varchar (15),
constraint fk_idUsuarioCanal foreign key  (idUsuario) references usuario (idUsuario),
constraint fk_idCanal foreign key  (idCanal) references canal (idCanal)
);

create table retransmision(
idRetransmision varchar (15) primary key,
titulo varchar (15),
fechaConHora datetime,
duracion float,
idCanal varchar (15),
constraint fk_idCanalRetransmision foreign key  (idCanal) references canal (idCanal)
);

alter table usuario add streamer boolean;