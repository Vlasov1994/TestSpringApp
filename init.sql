create sequence sys_sq;
create table org_legal_form (
    id integer default nextval('sys_sq'),
    name varchar(40),
    ident varchar(40),
    constraint org_legal_form_pk primary key(id)
);
create table client (
    id integer default nextval('sys_sq'),
    name varchar(40),
    short_name varchar(40),
    address varchar(40),
    org_legal_form_id integer,
    constraint client_pk primary key(id),
    foreign key (org_legal_form_id) references org_legal_form (id)
);
create table bank (
    id integer default nextval('sys_sq'),
    name varchar(40),
    bic varchar(40),
    constraint bank_pk primary key(id)
);
create table contribution (
    id integer default nextval('sys_sq'),
    name varchar(40),
    client_id integer,
    bank_id integer,
    opening_date date,
    percent real,
    term_in_months integer,
    constraint contribution_pk primary key(id),
    foreign key (client_id) references client (id),
    foreign key (bank_id) references bank (id)
);
---------------------------------------------------------------------------
insert into org_legal_form (name, ident)
values ('Хозяйственные товарищества', 'Business_partnerships');
insert into org_legal_form (name, ident)
values ('Акционерные общества', 'Joint_stock_companies');
insert into org_legal_form (name, ident)
values ('Общества с ограниченной ответственностью', 'Limited_liability_companies');
---------------------------------------------------------------------------
insert into client (name, short_name, address, org_legal_form_id)
values ('Tom Ford', 'T.F.', 'London', 2) ;
insert into client (name, short_name, address, org_legal_form_id)
values ('Tomas Edison', 'T.E.', 'Pittsburgh', 1);
insert into client (name, short_name, address, org_legal_form_id)
values ('William Shakespeare', 'W.S.', 'Chicago', 2);
insert into client (name, short_name, address, org_legal_form_id)
values ('Garry Trueman', 'G.T.', 'Denver', 3);
---------------------------------------------------------------------------
insert into bank (name, bic)
values ('Tinkoff', '000000001');
insert into bank (name, bic)
values ('Raiffeisen bank', '000000002');
---------------------------------------------------------------------------
insert into contribution (name, client_id, bank_id, opening_date, percent, term_in_months)
values ('contribution_1', 4, 9, '1994-11-13', 10, 360);
insert into contribution (name, client_id, bank_id, opening_date, percent, term_in_months)
values ('contribution_2', 5, 8, '2000-12-01', 15, 120);
insert into contribution (name, client_id, bank_id, opening_date, percent, term_in_months)
values ('contribution_3', 6, 8, '1999-07-05', 20, 8);
insert into contribution (name, client_id, bank_id, opening_date, percent, term_in_months)
values ('contribution_4', 7, 9, '2001-11-11', 125, 1);
insert into contribution (name, client_id, bank_id, opening_date, percent, term_in_months)
values ('contribution_5', 7, 8, '2020-03-25', 30, 12);
---------------------------------------------------------------------------