# users table
INSERT INTO users (type,
                   email,
                   password,
                   name,
                   firstname,
                   is_enabled,
                   is_pwd_checked,
                   role,
                   is_super_admin,
                   creation_date,
                   modification_date)
    VALUES (
        'U',
        'ose.jose@toto.fr',
        'AZERTY',
        'Osé',
        'José',
        true,
        true,
        'ROLE_USER',
        false,
        now(),
        now()
    ),(
        'U',
        'marie.paul@email.fr',
        'QWERTZ',
        'Marie',
        'Paul',
        true,
        true,
        'ROLE_USER',
        false,
        now(),
        now()
    ),(
        'A',
        'jeffthetek@superbowl.fr',
        'admin34',
        'Techos',
        'Jean-Francois',
        true,
        true,
        'ROLE_ADMIN',
        false,
        now(),
        now()
    ),(
       'A',
       'josethesupertek@superbowl.fr',
       'superadmin34',
       'F',
       'José',
       true,
       true,
       'ROLE_ADMIN',
       true,
       now(),
       now()
   ),(
       'C',
       'csantana@santana.fr',
       'como_va',
       'Santana',
       'Carlos',
       true,
       true,
       'ROLE_COMMENTATOR',
       false,
       now(),
       now()
   )
;

# Teams table
INSERT INTO teams (name, creation_date, modification_date) VALUES (
        'Les chaussettes de Boston', now(), now()),(
        'Les Dino perdus de Denver', now(), now()),(
        'Les sirènes poilues d\'Atlanta', now(), now()),(
        'Les canniches vaudous endormis de Nashville', now(), now()),(
        'Les castors farcis de Phoenix', now(), now()),(
        'Les framboises maudites de Omaha', now(), now()
    );

# Countries table
INSERT INTO countries (name)
VALUES (
           'Massachusetts'),(
           'Colorado'),(
           'Caroline du Sud'),(
           'Tennessee'),(
           'Arizona'),(
           'Nebraska'
       );

# Players table
INSERT INTO players (name, firstname,  number, creation_date, modification_date)
VALUES
    ('Feuille', 'Jimmy', 1, now(), now()),
        ('Bulsara', 'Farrokh', 1, now(), now()),
        ('Bonhomme', 'John', 1, now(), now()),
        ('Centrale', 'Robert', 1, now(), now()),
        ('Jaune', 'Jean-Paul', 1, now(), now()),
        ('Seigneur', 'Jean', 1, now(), now()),
        ('Pluskenoir', 'Richard', 1, now(), now()),
        ('Legantier', 'Roger', 1, now(), now()
    );

# Matchs table

# bets table
INSERT INTO `bets`(`bet_status`, `creation_date`, `final_odds`, `modification_date`, `wager`, `match_id`)
    VALUES ('BET_ALLOWED',NOW(),1.45,NOW(),10.0,null);

# users_bets table
INSERT INTO `users_bets` (`user_id`, `bet_id`) VALUES ('1', '1');