-- alter table cart_items drop foreign key FKpcttvuq4mxppo8sxggjtn5i2;
-- alter table cart_items drop foreign key FKiuhn9mru62vgqy1h0t1ggc3s7;
-- alter table cart_items drop foreign key FK1re40cjegsfvw58xrkdp6bac6;
-- alter table carts drop foreign key FKsegs11ngs12lqoy6n3hhlutua;
-- alter table orders drop foreign key FK92roa2i0wawsodws32yiuqk6c;
-- alter table products_categories drop foreign key FKqt6m2o5dly3luqcm00f5t4h2p;
-- alter table products_categories drop foreign key FKtj1vdea8qwerbjqie4xldl1el;

-- drop table if exists cart_items;
-- drop table if exists carts;
-- drop table if exists categories;
-- drop table if exists orders;
-- drop table if exists personas;
-- drop table if exists products;
-- drop table if exists products_categories;

create table cart_items
(
    id         bigint not null auto_increment,
    amount     integer,
    cart_id    bigint,
    order_id   bigint,
    product_id bigint,
    primary key (id)
) engine=InnoDB;

create table carts
(
    id         bigint not null auto_increment,
    persona_id bigint,
    primary key (id)
) engine=InnoDB;

create table categories
(
    id                  bigint not null auto_increment,
    description         varchar(650),
    image_name_category varchar(255),
    name_category       varchar(255),
    primary key (id)
) engine=InnoDB;

create table orders
(
    id         bigint not null auto_increment,
    address    varchar(500),
    comments   varchar(1000),
    created    datetime(6),
    status     varchar(255),
    telephone  bigint,
    total_sum  decimal(38, 2),
    updated    datetime(6),
    persona_id bigint,
    primary key (id)
) engine=InnoDB;

create table personas
(
    id        bigint       not null auto_increment,
    email     varchar(255) not null,
    last_name varchar(255) not null,
    name      varchar(255) not null,
    password  varchar(255),
    role      smallint,
    primary key (id)
) engine=InnoDB;

create table products
(
    id          bigint not null auto_increment,
    description varchar(1000),
    img         varchar(255),
    name        varchar(255),
    price       decimal(38, 2),
    primary key (id)
) engine=InnoDB;

create table products_categories
(
    product_id  bigint not null,
    category_id bigint not null
) engine=InnoDB;


alter table personas
    add constraint UK_lrw7flsg11d8nhgyvueqtnp8e unique (email);
alter table cart_items
    add constraint FKpcttvuq4mxppo8sxggjtn5i2c foreign key (cart_id) references carts (id);
alter table cart_items
    add constraint FKiuhn9mru62vgqy1h0t1ggc3s7 foreign key (order_id) references orders (id);
alter table cart_items
    add constraint FK1re40cjegsfvw58xrkdp6bac6 foreign key (product_id) references products (id);
alter table carts
    add constraint FKsegs11ngs12lqoy6n3hhlutua foreign key (persona_id) references personas (id);
alter table orders
    add constraint FK92roa2i0wawsodws32yiuqk6c foreign key (persona_id) references personas (id);
alter table products_categories
    add constraint FKqt6m2o5dly3luqcm00f5t4h2p foreign key (category_id) references categories (id);
alter table products_categories
    add constraint FKtj1vdea8qwerbjqie4xldl1el foreign key (product_id) references products (id);