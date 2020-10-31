create schema if not exists skier_lift;
use skier_lift;
create table if not exists lifts_vertical
(
    lift_id          int not null AUTO_INCREMENT primary key,
    vertical         int  not null
);

create table if not exists skier_records
(
    id             int                           not null AUTO_INCREMENT primary key,
    resort_id      int                         not null,
    lift_id        int                     not null,
    season_id      int,
    day_id         int                             comment 'day number in the season',
    day_time       int    default 0 not null,
    CONSTRAINT fk_skier_records_lift_id
        FOREIGN KEY (lift_id)
            REFERENCES lifts_vertical(lift_id)
) charset utf8mb4;