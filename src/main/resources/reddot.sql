create table usrs (
    id serial primary key,
    username varchar(40) not null,
    first_name varchar(40) not null,
    last_name varchar(40) not null,
    status varchar(20) not null,
    activated boolean not null,
);

create table articles (
    id serial primary key,
    title varchar(100) not null,
    author integer not null,
    text text not null,
    upload_date timestamp default current_timestamp,
    update_date timestamp default current_timestamp,
    hidden boolean default false,
    foreign key (author) references usrs(id)
);

create or replace function update_date() returns trigger as $$
declare
    i integer;
begin
    i = old.id;
    update articles set update_date = now() where id = i;
return new;
end;
$$ language 'plpgsql';

create trigger update_date_trigger after update on articles for each row when (old.* is distinct from new.*) execute procedure update_date();

insert into articles(title, author, text ,hidden)
values ('WAR', 8, 'war is a war', false ), ('REMBRAND', 5, 'masterpis ', false ),
       ('PICE', 9, 'war is a pice', false ), ('lemon', 8, 'i love lemons', true);