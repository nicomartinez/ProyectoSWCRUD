/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     21/05/2015 1:10:27 p. m.                     */
/*==============================================================*/


drop table if exists DISPOSITIVO;

drop table if exists HISTORIA_IPS;

drop table if exists INFORMACION;

drop table if exists PROCEDIMIENTO;

drop table if exists SEDE;

drop table if exists SERVICIO;

drop table if exists USUARIO;

/*==============================================================*/
/* Table: DISPOSITIVO                                           */
/*==============================================================*/
create table DISPOSITIVO
(
   ID_DISPOSITIVO       int not null,
   ID_PROCEDIMIENTO     int,
   NOMBRE_DISPOSITIVO   varchar(30) not null,
   COSTO_DISPOSITIVO    varchar(20) not null,
   DESCRIPCION          varchar(100) not null,
   primary key (ID_DISPOSITIVO)
);

/*==============================================================*/
/* Table: HISTORIA_IPS                                          */
/*==============================================================*/
create table HISTORIA_IPS
(
   ID_HISTORIA          int not null,
   ID_USUARIO           int,
   DESCRIPCION_HISTORIA varchar(100) not null,
   primary key (ID_HISTORIA)
);

/*==============================================================*/
/* Table: INFORMACION                                           */
/*==============================================================*/
create table INFORMACION
(
   ID_SEDE              int not null,
   DESCRIPCION_INFORMACION varchar(100) not null,
   TIPO_INFORMACION     varchar(1) not null,
   primary key (ID_SEDE)
);

/*==============================================================*/
/* Table: PROCEDIMIENTO                                         */
/*==============================================================*/
create table PROCEDIMIENTO
(
   ID_PROCEDIMIENTO     int not null,
   NOMBRE_PROCEDIMIENTO varchar(30) not null,
   primary key (ID_PROCEDIMIENTO)
);

/*==============================================================*/
/* Table: SEDE                                                  */
/*==============================================================*/
create table SEDE
(
   ID_SEDE              int not null,
   NOMBRE_SEDE          varchar(30) not null,
   DIRECCION_SEDE       varchar(20) not null,
   primary key (ID_SEDE)
);

/*==============================================================*/
/* Table: SERVICIO                                              */
/*==============================================================*/
create table SERVICIO
(
   ID_PROCEDIMIENTO     int not null,
   ID_SEDE              int not null,
   primary key (ID_PROCEDIMIENTO, ID_SEDE)
);

/*==============================================================*/
/* Table: USUARIO                                               */
/*==============================================================*/
create table USUARIO
(
   ID_USUARIO           int not null,
   ID_SEDE              int not null,
   ID_HISTORIA          int,
   NOMBRE_USUARIO       varchar(30) not null,
   primary key (ID_USUARIO)
);

alter table DISPOSITIVO add constraint FK_RELATIONSHIP_10 foreign key (ID_PROCEDIMIENTO)
      references PROCEDIMIENTO (ID_PROCEDIMIENTO) on delete restrict on update restrict;

alter table HISTORIA_IPS add constraint FK_RELATIONSHIP_7 foreign key (ID_USUARIO)
      references USUARIO (ID_USUARIO) on delete restrict on update restrict;

alter table INFORMACION add constraint FK_RELATIONSHIP_8 foreign key (ID_SEDE)
      references SEDE (ID_SEDE) on delete restrict on update restrict;

alter table SERVICIO add constraint FK_RELATIONSHIP_11 foreign key (ID_SEDE)
      references SEDE (ID_SEDE) on delete restrict on update restrict;

alter table SERVICIO add constraint FK_RELATIONSHIP_12 foreign key (ID_PROCEDIMIENTO)
      references PROCEDIMIENTO (ID_PROCEDIMIENTO) on delete restrict on update restrict;

alter table USUARIO add constraint FK_RELATIONSHIP_5 foreign key (ID_SEDE)
      references SEDE (ID_SEDE) on delete restrict on update restrict;

alter table USUARIO add constraint FK_RELATIONSHIP_6 foreign key (ID_HISTORIA)
      references HISTORIA_IPS (ID_HISTORIA) on delete restrict on update restrict;

