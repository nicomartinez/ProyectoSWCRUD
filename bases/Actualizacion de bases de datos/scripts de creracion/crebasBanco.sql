/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     21/05/2015 12:33:45 p. m.                    */
/*==============================================================*/


drop table if exists CUENTA;

drop table if exists OPERACION;

drop table if exists VALOR;

/*==============================================================*/
/* Table: CUENTA                                                */
/*==============================================================*/
create table CUENTA
(
   NUMERO_CUENTA        varchar(20) not null,
   NOMBRE_TITULAR       varchar(30) not null,
   TIPO_CUENTA          varchar(1) not null,
   CLAVE_CUENTA         varchar(50) not null,
   primary key (NUMERO_CUENTA)
);

/*==============================================================*/
/* Table: OPERACION                                             */
/*==============================================================*/
create table OPERACION
(
   ID_OPERACION         int not null,
   CUENTA_TRANSACCION   varchar(20),
   TIPO_OPERACION       varchar(1) not null,
   primary key (ID_OPERACION)
);

/*==============================================================*/
/* Table: VALOR                                                 */
/*==============================================================*/
create table VALOR
(
   ID_OPERACION         int not null,
   NUMERO_CUENTA        varchar(20) not null,
   VALOR_OPERACION      int not null,
   primary key (ID_OPERACION, NUMERO_CUENTA)
);

alter table VALOR add constraint FK_RELATIONSHIP_13 foreign key (NUMERO_CUENTA)
      references CUENTA (NUMERO_CUENTA) on delete restrict on update restrict;

alter table VALOR add constraint FK_RELATIONSHIP_14 foreign key (ID_OPERACION)
      references OPERACION (ID_OPERACION) on delete restrict on update restrict;

