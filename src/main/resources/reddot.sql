create table users (
    id serial primary key,
    username varchar(50) not null,
    email varchar(100),
    password varchar(100) not null,
    status varchar(20) not null,
    activated boolean not null,
    avatar varchar(100)
);

create table articles (
    id serial primary key,
    title varchar(100) not null,
    user_id integer not null,
    content text not null,
    upload date default now(),
    edit date default now(),
    hidden boolean default false,
    rate integer,
    foreign key (user_id) references users(id)
);

create or replace function update_date() returns trigger as $$
declare
    i integer;
begin
    i = old.id;
    update articles set edit = now() where id = i;
return new;
end;
$$ language 'plpgsql';

create trigger update_date_trigger after update on articles for each row when (old.* is distinct from new.*) execute procedure update_date();
