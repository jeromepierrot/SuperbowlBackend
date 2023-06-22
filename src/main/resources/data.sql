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
    ),
    (
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
    ),
    (
        'A',
        'marie.jose@toto.fr',
        'admin34',
        'Techos',
        'Jean-Francois',
        true,
        true,
        'ROLE_ADMIN',
        false,
        now(),
        now()
    )
;

# bets table
INSERT INTO `bets`(`bet_status`, `creation_date`, `final_odds`, `modification_date`, `wager`, `match_id`)
    VALUES ('BET_ALLOWED',NOW(),1.45,NOW(),10.0,null);

# users_bets table
INSERT INTO `users_bets` (`user_id`, `bet_id`) VALUES ('1', '1');