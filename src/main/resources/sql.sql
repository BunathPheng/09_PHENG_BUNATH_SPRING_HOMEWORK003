create table venues
(
    venue_id   serial
        primary key,
    venue_name varchar(100) not null,
    location   text
);


create table events
(
    event_id   serial
        primary key,
    event_name varchar(50) not null,
    event_date timestamp,
    venues_id  integer
        references venues
            on delete cascade
);



create table attendees
(
    attendee_id   serial
        primary key,
    attendee_name varchar(255) not null,
    email         varchar(255)
        unique
);



create table event_attendee
(
    id          serial
        primary key,
    event_id    integer
        references events
            on delete cascade,
    attendee_id integer
        references attendees
            on delete cascade
);


