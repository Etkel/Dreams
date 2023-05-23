insert into personas (email, last_name, name, password, role)
values ('admin@gmail.com', 'admin', 'admin', '$2a$12$cAINsHmVway0LYk4atd5D.TG.zEIK1oCBPvJAAjuZCjA/n6/PKjAS', 0);
insert into carts (persona_id) values (1);

insert into personas (email, last_name, name, password, role)
values ('user@gmail.com', 'user', 'user', '$2a$12$iPyF.X6vnQIr2D4bvNjzy.M2Y6c2WyXSd/OvV6dE7hHC1VXaQYAA2', 1);
insert into carts (persona_id) values (2);

insert into personas (email, last_name, name, password, role)
values ('ollan@gmail.com', 'Jonsan', 'Ollan', '$2a$12$oFRjTOr8ZZU91DuD.LbSPuGDSi1tBkIhvCNByP63TUlexvtqN3fuG', 1);
insert into carts (persona_id) values (3);