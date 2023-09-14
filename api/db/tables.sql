
Create table if not exists public.customer(
    id serial primary key,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    balance NUMERIC(20,2) DEFAULT (100.0 + (random() * (100000.0 - 100.0)))
);

Create table if not exists public.company(
    id serial primary key NOT NULL,
    companyname VARCHAR(255)  NOT NULL,
    category VARCHAR(255) NOT NULL,
    balance NUMERIC(20,2) DEFAULT (100.0 + (random() * (100000.0 - 100.0)))
    );

Create table if not exists public.companyuser(
    id serial primary key,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL,
    balance NUMERIC(20,2) DEFAULT (100.0 + (random() * (100000.0 - 100.0))),
    companyid integer references company(id)
    );

Create table if not exists public.admin(
    id serial primary key,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    firstname VARCHAR(255) NOT NULL
    );



CReate table if not exists public.share(
    id serial primary key NOT NULL,
    companyid Integer references company(id),
    sharetype Char NOT NULL,
    price NUMERIC(20,2) NOT NULL
);

Create table if not exists public.companylisting(
    id serial primary key NOT NULL,
    shareid Integer references share(id),
    numshare Integer NOT NULL
);

Create table if not exists public.investlisting(
    id serial primary key NOT NULL,
    shareid Integer references share(id),
    numshare Integer NOT NULL
);

Create table if not exists public.portfolio(
    id serial primary key NOT NULL,
    userid Integer references customer(id),
    listingid Integer references investlisting(id),
    shareid Integer references share(id),
    companyid Integer references company(id)
);


