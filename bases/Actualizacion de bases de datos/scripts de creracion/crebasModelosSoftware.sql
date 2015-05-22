/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     21/05/2015 12:59:47 p. m.                    */
/*==============================================================*/


drop table if exists CITA;

drop table if exists CLIENTE;

drop table if exists DISPOSITIVO;

drop table if exists EPS;

drop table if exists FARMACIA;

drop table if exists HISTORIA_CLINICA;

drop table if exists IPS;

drop table if exists LABORATORIO;

drop table if exists PROCEDIMIENTO;

drop table if exists REVISION;

drop table if exists TRANSACCION;

/*==============================================================*/
/* Table: CITA                                                  */
/*==============================================================*/
create table CITA
(
   ID_CITA              int not null,
   ID_EPS               int not null,
   FECHA_CITA           datetime not null,
   LUGAR_CITA           int not null,
   primary key (ID_CITA)
);

/*==============================================================*/
/* Table: CLIENTE                                               */
/*==============================================================*/
create table CLIENTE
(
   ID_CLIENTE           int not null,
   ID_EPS               int not null,
   NOMBRE_CLIENTE       varchar(30) not null,
   CONTRASENIA          varchar(50) not null,
   primary key (ID_CLIENTE)
);

/*==============================================================*/
/* Table: DISPOSITIVO                                           */
/*==============================================================*/
create table DISPOSITIVO
(
   ID_DISPOSITIVO       int not null,
   ID_IPS               int not null,
   ID_PROCEDIMIENTO     int not null,
   NOMBRE_DISPOSITIVO   varchar(30) not null,
   DESCRIPCION_DISPOSITIVO varchar(100) not null,
   primary key (ID_DISPOSITIVO)
);

/*==============================================================*/
/* Table: EPS                                                   */
/*==============================================================*/
create table EPS
(
   ID_EPS               int not null,
   NOMBRE_EPS           varchar(30) not null,
   primary key (ID_EPS)
);

/*==============================================================*/
/* Table: FARMACIA                                              */
/*==============================================================*/
create table FARMACIA
(
   ID_FARMACIA          int not null,
   ID_EPS               int not null,
   DIRECCION_FARMACIA   varchar(20) not null,
   NOMBRE_FARMACIA      varchar(20) not null,
   UBICACION_FARMACIA   varchar(20) not null,
   primary key (ID_FARMACIA)
);

/*==============================================================*/
/* Table: HISTORIA_CLINICA                                      */
/*==============================================================*/
create table HISTORIA_CLINICA
(
   ID_USUARIO           int not null,
   ID_IPS               int not null,
   DESCRIPCION          varchar(100) not null,
   primary key (ID_USUARIO)
);

/*==============================================================*/
/* Table: IPS                                                   */
/*==============================================================*/
create table IPS
(
   ID_IPS               int not null,
   ID_CLIENTE           int,
   NOMBRE_IPS           varchar(30) not null,
   primary key (ID_IPS)
);

/*==============================================================*/
/* Table: LABORATORIO                                           */
/*==============================================================*/
create table LABORATORIO
(
   ID_LABORATORIO       int not null,
   ID_EPS               int not null,
   NOMBRE_LABORATORIO   varchar(20) not null,
   DIRECCION_LABORATORIO varchar(20) not null,
   UBICACION_LABORATORIO varchar(20) not null,
   primary key (ID_LABORATORIO)
);

/*==============================================================*/
/* Table: PROCEDIMIENTO                                         */
/*==============================================================*/
create table PROCEDIMIENTO
(
   ID_IPS               int not null,
   ID_PROCEDIMIENTO     int not null,
   NOMBRE_PROCEDIMIENTO varchar(30) not null,
   primary key (ID_IPS, ID_PROCEDIMIENTO)
);

/*==============================================================*/
/* Table: REVISION                                              */
/*==============================================================*/
create table REVISION
(
   ID_CLIENTE           int not null,
   ID_USUARIO           int not null,
   primary key (ID_CLIENTE, ID_USUARIO)
);

/*==============================================================*/
/* Table: TRANSACCION                                           */
/*==============================================================*/
create table TRANSACCION
(
   ID_CLIENTE           int not null,
   ID_TRAMSACCION       int not null,
   VALOR_TRANSACCION    int not null,
   FECHA_TRANSACCION    date not null,
   primary key (ID_CLIENTE, ID_TRAMSACCION)
);

alter table CITA add constraint FK_RELATIONSHIP_12 foreign key (ID_EPS)
      references EPS (ID_EPS) on delete restrict on update restrict;

alter table CLIENTE add constraint FK_RELATIONSHIP_4 foreign key (ID_EPS)
      references EPS (ID_EPS) on delete restrict on update restrict;

alter table DISPOSITIVO add constraint FK_RELATIONSHIP_8 foreign key (ID_IPS, ID_PROCEDIMIENTO)
      references PROCEDIMIENTO (ID_IPS, ID_PROCEDIMIENTO) on delete restrict on update restrict;

alter table FARMACIA add constraint FK_RELATIONSHIP_13 foreign key (ID_EPS)
      references EPS (ID_EPS) on delete restrict on update restrict;

alter table HISTORIA_CLINICA add constraint FK_RELATIONSHIP_10 foreign key (ID_IPS)
      references IPS (ID_IPS) on delete restrict on update restrict;

alter table IPS add constraint FK_RELATIONSHIP_7 foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE) on delete restrict on update restrict;

alter table LABORATORIO add constraint FK_RELATIONSHIP_11 foreign key (ID_EPS)
      references EPS (ID_EPS) on delete restrict on update restrict;

alter table PROCEDIMIENTO add constraint FK_RELATIONSHIP_9 foreign key (ID_IPS)
      references IPS (ID_IPS) on delete restrict on update restrict;

alter table REVISION add constraint FK_RELATIONSHIP_5 foreign key (ID_USUARIO)
      references HISTORIA_CLINICA (ID_USUARIO) on delete restrict on update restrict;

alter table REVISION add constraint FK_RELATIONSHIP_6 foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE) on delete restrict on update restrict;

alter table TRANSACCION add constraint FK_RELATIONSHIP_1 foreign key (ID_CLIENTE)
      references CLIENTE (ID_CLIENTE) on delete restrict on update restrict;

