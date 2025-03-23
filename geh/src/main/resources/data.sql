CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START WITH 1 INCREMENT BY 1;

CREATE TABLE producto (
                          id BIGINT PRIMARY KEY,
                          nombre VARCHAR(255) NOT NULL,
                          precio DECIMAL(10,2) NOT NULL
);

insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Chocolate - Semi Sweet, Calets', 522.8);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Tea - Vanilla Chai', 421.09);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Dikon', 159.99);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Shallots', 73.79);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Foil Wrap', 110.78);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Curry Powder Madras', 348.49);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Jolt Cola', 699.84);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Tart Shells - Sweet, 3', 260.28);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Squid - U - 10 Thailand', 461.87);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Sword Pick Asst', 776.98);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Wine - White, Cooking', 781.07);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Chilli Paste, Ginger Garlic', 217.2);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Gin - Gilbeys London, Dry', 705.33);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Sesame Seed Black', 199.62);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Fish - Scallops, Cold Smoked', 452.2);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Cheese - Sheep Milk', 821.57);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Bay Leaf', 985.37);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Nori Sea Weed - Gold Label', 603.08);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Soup - Campbells, Cream Of', 676.5);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Tamarillo', 914.28);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Dehydrated Kelp Kombo', 703.0);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Cheese - Bakers Cream Cheese', 953.44);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Compound - Passion Fruit', 9.09);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Carrots - Jumbo', 326.75);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Wine - Niagara,vqa Reisling', 428.62);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Foil Wrap', 361.74);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Macaroons - Homestyle Two Bit', 957.49);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Shrimp - Baby, Warm Water', 530.23);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Sardines', 954.87);
insert into producto (id, nombre, precio) values (NEXTVAL('hibernate_sequence'), 'Dried Peach', 749.25);
