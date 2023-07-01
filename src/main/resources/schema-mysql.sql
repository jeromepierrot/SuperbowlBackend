ALTER TABLE IF EXISTS bets
DROP FOREIGN KEY IF EXISTS FK_bets_match;

ALTER TABLE IF EXISTS comments
DROP FOREIGN KEY IF EXISTS FK_comments_commentator;

ALTER TABLE IF EXISTS comments
DROP FOREIGN KEY IF EXISTS FK_comments_match;

ALTER TABLE IF EXISTS matches
DROP FOREIGN KEY IF EXISTS FK_matches_team_a;

ALTER TABLE IF EXISTS matches
DROP FOREIGN KEY IF EXISTS FK_matches_team_b;

ALTER TABLE IF EXISTS players
DROP FOREIGN KEY IF EXISTS FK_players_team;

ALTER TABLE IF EXISTS teams
DROP FOREIGN KEY IF EXISTS FK_teams_country;

ALTER TABLE IF EXISTS users_bets
DROP FOREIGN KEY IF EXISTS FK_usersbets_bet;

ALTER TABLE IF EXISTS users_bets
DROP FOREIGN KEY IF EXISTS FK_usersbets_user;

DROP TABLE IF EXISTS bets;

DROP TABLE IF EXISTS comments;

DROP TABLE IF EXISTS countries;

DROP TABLE IF EXISTS matches;

DROP TABLE IF EXISTS players;

DROP TABLE IF EXISTS teams;

DROP TABLE IF EXISTS users;

DROP TABLE IF EXISTS users_bets;

CREATE TABLE bets (
                      id bigint not null auto_increment,
                      match_id bigint,
                      wager float(23) not null,
                      final_odds float(23) not null,
                      bet_status varchar(255) not null,
                      creation_date datetime(6),
                      modification_date datetime(6),

                      primary key (id)
);

CREATE TABLE comments (
                          id bigint not null auto_increment,
                          match_id bigint,
                          commentator_user_id bigint,
                          post_content varchar(255),
                          post_date datetime(6),
                          edit_date datetime(6),
                          primary key (id)
);

CREATE TABLE countries (
                           id bigint not null auto_increment,
                           name varchar(255) not null,
                           primary key (id)
);

CREATE TABLE matches (
                         id bigint not null auto_increment,
                         team_a_id bigint,
                         team_b_id bigint,
                         start_date datetime(6),
                         end_date datetime(6),
                         odds_a float(23) not null,
                         odds_b float(23) not null,
                         score_a integer,
                         score_b integer,
                         status varchar(255),
                         weather varchar(255),
                         is_enabled bit not null,
                         creation_date datetime(6),
                         modification_date datetime(6),
                         primary key (id)
);

CREATE TABLE players (
                         id bigint not null auto_increment,
                         lastname varchar(255) not null,
                         firstname varchar(255) not null,
                         number integer not null,
                         team_id bigint,
                         creation_date datetime(6),
                         modification_date datetime(6),
                         primary key (id)
);

CREATE TABLE teams (
                       id bigint not null auto_increment,
                       name varchar(255) not null,
                       country_id bigint,
                       creation_date datetime(6),
                       modification_date datetime(6),
                       primary key (id)
);

CREATE TABLE users (
                       type varchar(31) not null,
                       id bigint not null auto_increment,
                       email varchar(255) not null,
                       password varchar(255) not null,
                       lastname varchar(255) not null,
                       firstname varchar(255) not null,
                       is_enabled bit not null,
                       is_pwd_checked bit not null,
                       role varchar(255),
                       is_super_admin bit,
                       creation_date datetime(6),
                       modification_date datetime(6),
                       primary key (id)
);

CREATE TABLE users_bets (
                            bet_id bigint not null,
                            user_id bigint not null,
                            primary key (bet_id, user_id)
);

ALTER TABLE IF EXISTS countries
    ADD CONSTRAINT UK_countries_name unique (name);

ALTER TABLE IF EXISTS teams
    ADD CONSTRAINT UK_teams_name unique (name);

ALTER TABLE IF EXISTS users
    ADD CONSTRAINT UK_users_email unique (email);

ALTER TABLE IF EXISTS bets
    ADD CONSTRAINT FK_bets_match
    FOREIGN KEY (match_id)
    REFERENCES matches (id);

ALTER TABLE IF EXISTS comments
    ADD CONSTRAINT FK_comments_commentator
    FOREIGN KEY (commentator_user_id)
    REFERENCES users (id);

ALTER TABLE IF EXISTS comments
    ADD CONSTRAINT FK_comments_match
    FOREIGN KEY (match_id)
    REFERENCES matches (id);

ALTER TABLE IF EXISTS matches
    ADD CONSTRAINT FK_matches_team_a
    FOREIGN KEY (team_a_id)
    REFERENCES teams (id);

ALTER TABLE IF EXISTS matches
    ADD CONSTRAINT FK_matches_team_b
    FOREIGN KEY (team_b_id)
    REFERENCES teams (id);

ALTER TABLE IF EXISTS players
    ADD CONSTRAINT FK_players_team
    FOREIGN KEY (team_id)
    REFERENCES teams (id);

ALTER TABLE IF EXISTS teams
    ADD CONSTRAINT FK_teams_country
    FOREIGN KEY (country_id)
    REFERENCES countries (id);

ALTER TABLE IF EXISTS users_bets
    ADD CONSTRAINT FK_usersbets_bet
    FOREIGN KEY (bet_id)
    REFERENCES bets (id);

ALTER TABLE IF EXISTS users_bets
    ADD CONSTRAINT FK_usersbets_user
    FOREIGN KEY (user_id)
    REFERENCES users (id);

ALTER TABLE IF EXISTS bets
    add column team_id bigint;

ALTER TABLE IF EXISTS bets
    ADD CONSTRAINT FK_bets_team
    FOREIGN KEY (team_id)
    REFERENCES teams (id);