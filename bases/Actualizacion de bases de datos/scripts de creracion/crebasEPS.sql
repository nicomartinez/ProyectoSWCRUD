/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     21/05/2015 1:25:47 p. m.                     */
/*==============================================================*/


drop table if exists AFILIADO;

drop table if exists CITA;

drop table if exists CUOTA;

drop table if exists DESCRIPCION;

drop table if exists DIA;

drop table if exists DOCTOR;

drop table if exists FARMACIA;

drop table if exists HISTORIA;

drop table if exists HORA;

drop table if exists IPS;

drop table if exists LABORATORIO;

drop table if exists LUGAR;

drop table if exists RECETA;

drop table if exists SEDE;

/*==============================================================*/
/* Table: AFILIADO                                              */
/*==============================================================*/
create table AFILIADO
(
   ID_AFILIADO          int not null,
   NOMBRE_AFILIADO      varchar(80) not null,
   DOCUMENTO_AFILIADO   int not null,
   DIRECCION_AFILIADO   varchar(50) not null,
   TELEFONO_AFILIADO    varchar(20) not null,
   ESTADO_AFILIADO      varchar(1) not null,
   TIPO_DOCUMENTO       varchar(2) not null,
   primary key (ID_AFILIADO)
);

/*==============================================================*/
/* Table: CITA                                                  */
/*==============================================================*/
create table CITA
(
   ID_IPS               int not null,
   ID_DOCTOR            char(10) not null,
   ID_HORA              int not null,
   ID_AFILIADO          int,
   primary key (ID_IPS, ID_DOCTOR, ID_HORA)
);

/*==============================================================*/
/* Table: CUOTA                                                 */
/*==============================================================*/
create table CUOTA
(
   ID_AFILIADO          int not null,
   ID_CUOTA             int not null,
   VALOR_CUOTA          int not null,
   FECHA_CUOTA          date not null,
   primary key (ID_AFILIADO, ID_CUOTA)
);

/*==============================================================*/
/* Table: DESCRIPCION                                           */
/*==============================================================*/
create table DESCRIPCION
(
   ID_IPS               int not null,
   ID_DOCTOR            char(10) not null,
   ID_HORA              int not null,
   ID_AFILIADO          int not null,
   ID_HISTORIA          int not null,
   DETALLES             varchar(100) not null,
   primary key (ID_IPS, ID_DOCTOR, ID_HORA, ID_AFILIADO, ID_HISTORIA)
);

/*==============================================================*/
/* Table: DIA                                                   */
/*==============================================================*/
create table DIA
(
   ID_DIA               int not null,
   ID_DOCTOR            char(10) not null,
   NOMBRE_DIA           varchar(10) not null,
   primary key (ID_DIA)
);

/*==============================================================*/
/* Table: DOCTOR                                                */
/*==============================================================*/
create table DOCTOR
(
   ID_DOCTOR            char(10) not null,
   NOMBRE_DOCTOR        char(10) not null,
   primary key (ID_DOCTOR)
);

/*==============================================================*/
/* Table: FARMACIA                                              */
/*==============================================================*/
create table FARMACIA
(
   ID_FARMACIA          int not null,
   ID_SEDE              int not null,
   DIRECCION_FARMACIA   varchar(20) not null,
   NOMBRE_FARMACIA      varchar(20) not null,
   primary key (ID_FARMACIA)
);

/*==============================================================*/
/* Table: HISTORIA                                              */
/*==============================================================*/
create table HISTORIA
(
   ID_AFILIADO          int not null,
   ID_HISTORIA          int not null,
   primary key (ID_AFILIADO, ID_HISTORIA)
);

/*==============================================================*/
/* Table: HORA                                                  */
/*==============================================================*/
create table HORA
(
   ID_HORA              int not null,
   ID_DIA               int not null,
   HORA_DISPONIBLE      time not null,
   primary key (ID_HORA)
);

/*==============================================================*/
/* Table: IPS                                                   */
/*==============================================================*/
create table IPS
(
   ID_IPS               int not null,
   ID_LUGAR             int not null,
   NOMBRE_IPS           varchar(20) not null,
   DIRECCION_IPS        varchar(30) not null,
   primary key (ID_IPS)
);

/*==============================================================*/
/* Table: LABORATORIO                                           */
/*==============================================================*/
create table LABORATORIO
(
   ID_LABORATORIO       int not null,
   ID_SEDE              int not null,
   NOMBRE_LABORATORIO   char(10) not null,
   DIRECCION_LABORATORIO char(10) not null,
   primary key (ID_LABORATORIO)
);

/*==============================================================*/
/* Table: LUGAR                                                 */
/*==============================================================*/
create table LUGAR
(
   ID_LUGAR             int not null,
   NOMBRE_LUGAR         varchar(30) not null,
   primary key (ID_LUGAR)
);

/*==============================================================*/
/* Table: RECETA                                                */
/*==============================================================*/
create table RECETA
(
   ID_IPS               int not null,
   ID_DOCTOR            char(10) not null,
   ID_HORA              int not null,
   ID_FARMACIA          int not null,
   ID_MEDICAMENTO       int not null,
   primary key (ID_IPS, ID_DOCTOR, ID_HORA, ID_FARMACIA)
);

/*==============================================================*/
/* Table: SEDE                                                  */
/*==============================================================*/
create table SEDE
(
   ID_SEDE              int not null,
   ID_LUGAR             int not null,
   NOMBRE_SEDE          varchar(50) not null,
   DIRECCION_SEDE       varchar(40) not null,
   primary key (ID_SEDE)
);

alter table CITA add constraint FK_RELATIONSHIP_14 foreign key (ID_HORA)
      references HORA (ID_HORA) on delete restrict on update restrict;

alter table CITA add constraint FK_RELATIONSHIP_18 foreign key (ID_AFILIADO)
      references AFILIADO (ID_AFILIADO) on delete restrict on update restrict;

alter table CITA add constraint FK_RELATIONSHIP_19 foreign key (ID_DOCTOR)
      references DOCTOR (ID_DOCTOR) on delete restrict on update restrict;

alter table CITA add constraint FK_RELATIONSHIP_20 foreign key (ID_IPS)
      references IPS (ID_IPS) on delete restrict on update restrict;

alter table CUOTA add constraint FK_RELATIONSHIP_3 foreign key (ID_AFILIADO)
      references AFILIADO (ID_AFILIADO) on delete restrict on update restrict;

alter table DESCRIPCION add constraint FK_RELATIONSHIP_16 foreign key (ID_IPS, ID_DOCTOR, ID_HORA)
      references CITA (ID_IPS, ID_DOCTOR, ID_HORA) on delete restrict on update restrict;

alter table DESCRIPCION add constraint FK_RELATIONSHIP_17 foreign key (ID_AFILIADO, ID_HISTORIA)
      references HISTORIA (ID_AFILIADO, ID_HISTORIA) on delete restrict on update restrict;

alter table DIA add constraint FK_RELATIONSHIP_10 foreign key (ID_DOCTOR)
      references DOCTOR (ID_DOCTOR) on delete restrict on update restrict;

alter table FARMACIA add constraint FK_RELATIONSHIP_5 foreign key (ID_SEDE)
      references SEDE (ID_SEDE) on delete restrict on update restrict;

alter table HISTORIA add constraint FK_RELATIONSHIP_15 foreign key (ID_AFILIADO)
      references AFILIADO (ID_AFILIADO) on delete restrict on update restrict;

alter table HORA add constraint FK_RELATIONSHIP_13 foreign key (ID_DIA)
      references DIA (ID_DIA) on delete restrict on update restrict;

alter table IPS add constraint FK_RELATIONSHIP_22 foreign key (ID_LUGAR)
      references LUGAR (ID_LUGAR) on delete restrict on update restrict;

alter table LABORATORIO add constraint FK_RELATIONSHIP_6 foreign key (ID_SEDE)
      references SEDE (ID_SEDE) on delete restrict on update restrict;

alter table RECETA add constraint FK_RELATIONSHIP_11 foreign key (ID_IPS, ID_DOCTOR, ID_HORA)
      references CITA (ID_IPS, ID_DOCTOR, ID_HORA) on delete restrict on update restrict;

alter table RECETA add constraint FK_RELATIONSHIP_12 foreign key (ID_FARMACIA)
      references FARMACIA (ID_FARMACIA) on delete restrict on update restrict;

alter table SEDE add constraint FK_RELATIONSHIP_21 foreign key (ID_LUGAR)
      references LUGAR (ID_LUGAR) on delete restrict on update restrict;

alter table AFILIADO add constraint CK_RELATIONSHIP check ( ESTADO_AFILIADO in ('A','I'));