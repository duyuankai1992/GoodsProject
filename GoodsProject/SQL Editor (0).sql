
    create table "SCOTT"."GOODS"(
        "GID" NUMBER(4) not null,
       "GNAME" VARCHAR2(50) not null,
       "GPRICE" NUMBER(6,2) not null,
       "GNUMBER" NUMBER(4) not null,
        constraint "SYS_C0011427" primary key ("GID")
    );

    create unique index "SCOTT"."SYS_C0011427" on "SCOTT"."GOODS"("GID");
    
    
    create sequence seq_goods start with 1 increment by 1;
    