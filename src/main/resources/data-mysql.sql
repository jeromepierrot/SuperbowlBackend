--
-- Database: `nullstein_sbdatabase`
--

-- --------------------------------------------------------

--
-- Dumping data for table `users`
--

INSERT INTO `users` (type, email, password, lastname, firstname, is_enabled, is_pwd_checked, role, is_super_admin, creation_date, modification_date) VALUES
 ('U', 'ose.jose@toto.fr',                'AZERTY'              , 'Osé'             , 'José'             , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000'),
 ('U', 'marie.paul@email.fr',             'QWERTZ'              , 'Marie'           , 'Paul'             , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000'),
 ('A', 'jeffthetek@superbowl.fr',         'admin34'             , 'Techos'          , 'Jean-Francois'    , b'1', b'1', 'ROLE_ADMIN'      , b'0', '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000'),
 ('A', 'josethesupertek@superbowl.fr',    'superadmin34'        , 'F'               , 'José'             , b'1', b'1', 'ROLE_ADMIN'      , b'1', '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000'),
 ('C', 'csantana@santana.fr',             'como_va'             , 'Santana'         , 'Carlos'           , b'1', b'1', 'ROLE_COMMENTATOR', NULL, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000'),
 ('U', 'john.doe@doedoedoe.fr',           '1234'                , 'Doe'             , 'John'             , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 11:59:20.310345', '2023-06-26 11:59:20.310411'),
 ('U', 'pierrefeu@jurassic.fr',           'silexDu56'           , 'feu'             , 'Pierre'           , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:04:50.000000', '2023-06-26 15:04:50.000000'),
 ('U', 'nicolast@fragile.com',            'eclatdeverre22'      , 'Toukacé'         , 'Nicolas'          , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:04:50.000000', '2023-06-26 15:04:50.000000'),
 ('U', 'p.naure@gmail.com',               'silexDu56'           , 'Naure'           , 'Paul'             , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:04:50.000000', '2023-06-26 15:04:50.000000'),
 ('U', 'metauda.gilles@free.fr',          'silexOf34'           , 'Métauda'         , 'Gilles'           , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:12:04.000000', '2023-06-26 15:12:04.000000'),
 ('U', 'pmz@gmail.fr',                    'jAimePasm0nPr3nom'   , 'Z'               , 'Philippe-Mouloud' , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:12:04.000000', '2023-06-26 15:12:04.000000'),
 ('U', 'francois1er@yoohoo.fr',           'JeSuisNiRoiNiM1nitre', 'Premier'         , 'Francois'         , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:12:04.000000', '2023-06-26 15:12:04.000000'),
 ('U', 'louisxiv@verslinfinietaudela.fr', 'LouisXIV'            , 'Quatorze'        , 'Louis'            , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:12:04.000000', '2023-06-26 15:12:04.000000'),
 ('U', 'nicolenicklous@free.fr',          'tournevis62'         , 'Nicklous'        , 'Nicole'           , b'1', b'1', 'ROLE_USER'       , NULL, '2023-06-26 15:12:04.000000', '2023-06-26 15:12:04.000000'),
 ('A', 'tony@free.fr'          ,          '4321'                , 'Admin'           , 'Tony'             , b'1', b'1', 'ROLE_ADMIN'      , NULL, '2023-06-26 15:12:04.000000', '2023-06-26 15:12:04.000000'),
 ('C', 'albert2@sb.com'        ,          '0987'                , 'Deuxieme'        , 'Albert'           , b'1', b'1', 'ROLE_COMMENTATOR', NULL, '2023-06-26 15:12:04.000000', '2023-06-26 15:12:04.000000');

-- --------------------------------------------------------

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`id`, `name`) VALUES
(1, 'Massachusetts'),
(2, 'Colorado'),
(3, 'Caroline du Sud'),
(4, 'Tennessee'),
(5, 'Arizona'),
(6, 'Nebraska'),
(7, 'country_test'),
(8, 'Missouri'),
(9, 'NewYork');

-- --------------------------------------------------------

--
-- Dumping data for table `teams`
--
INSERT INTO `teams` (id, name, country_id, creation_date, modification_date) VALUE
    ( 1, 'Les chaussettes de Boston'                  , 1, '2023-06-24 20:23:22.000000', NOW() ),
    ( 2, 'Les Dino perdus de Denver'                  , 2, '2023-06-24 20:23:22.000000', NOW() ),
    ( 3, 'Les sirènes poilues d\'Atlanta'             , 6, '2023-06-24 20:23:22.000000', NOW() ),
    ( 4, 'Les canniches vaudous endormis de Nashville', 4, '2023-06-24 20:23:22.000000', NOW() ),
    ( 5, 'Les castors farcis de Phoenix'              , 5, '2023-06-24 20:23:22.000000', NOW() ),
    ( 6, 'Les framboises maudites de Omaha'           , 3, '2023-06-24 20:23:22.000000', NOW() ),
    ( 7, 'Nouvelle équipe'                            , 7, '2023-06-25 13:40:32.150292', NOW() ),
    ( 8, 'New Team'                                   , 3, '2023-06-25 13:41:49.295950', NOW() ),
    ( 9, 'Updated team'                               , 7, '2023-06-25 14:24:03.741012', NOW() ),
    (10, 'Nouvelle équipe test'                       , 1, '2023-06-25 17:44:24.145032', NOW() );

--
-- Dumping data for table `players`
--
INSERT INTO `players` (id, lastname, firstname, number, team_id, creation_date, modification_date) VALUES
( 1,  'Jimmy'     , 'Feuille'    ,  1, 1, '2023-06-24 20:23:22.000000',  NOW() ),
( 2,  'Farrokh'   , 'Bulsara'    ,  1, 2, '2023-06-24 20:23:22.000000',  NOW() ),
( 3,  'John'      , 'Bonne Ame'  ,  2, 1, '2023-06-24 20:23:22.000000',  NOW() ),
( 4,  'Robert'    , 'Centrale'   ,  3, 1, '2023-06-24 20:23:22.000000',  NOW() ),
( 5,  'Jean-Paul' , 'Jaune'      ,  4, 1, '2023-06-24 20:23:22.000000',  NOW() ),
( 6,  'Jean'      , 'Seigneur'   ,  1, 6, '2023-06-24 20:23:22.000000',  NOW() ),
( 7,  'Richard'   , 'Pluskenoir' , 21, 6, '2023-06-24 20:23:22.000000',  NOW() ),
( 8,  'Roger'     , 'Legantier'  , 53, 6, '2023-06-24 20:23:22.000000',  NOW() ),
( 9,  'Yann'      , 'Peste'      , 42, 6, '2023-06-26 00:29:19.000000',  NOW() ),
(10,  'Yann'      , 'Gilet'      ,  5, 6, '2023-06-26 00:29:51.000000',  NOW() ),
(11,  'Roger'     , 'Tailleur'   ,  5, 2, '2023-06-24 20:23:22.000000',  NOW() ),
(12,  'Phil'      , 'Avril'      , 15, 2, '2023-06-24 20:23:22.000000',  NOW() ),
(13,  'Jean'      , 'Déconne'    , 23, 2, '2023-06-24 20:23:22.000000',  NOW() ),
(14,  'Philippe'  , 'Merlu'      ,  1, 3, '2023-06-24 20:23:22.000000',  NOW() ),
(15,  'Pierre'    , 'Mathy'      ,  1, 3, '2023-06-24 20:23:22.000000',  NOW() ),
(16,  'Nicolas'   , 'Maison'     ,  1, 4, '2023-06-24 20:23:22.000000',  NOW() ),
(17,  'David'     , 'Gilmour'    , 21, 4, '2023-06-24 20:23:22.000000',  NOW() ),
(18,  'Roger'     , 'Eaux'       , 53, 4, '2023-06-24 20:23:22.000000',  NOW() ),
(19,  'Richard'   , 'Mauvais'    , 42, 4, '2023-06-26 00:29:19.000000',  NOW() ),
(20,  'Cyprien'   , 'Barrette'   ,  5, 4, '2023-06-26 00:29:51.000000',  NOW() ),
(21,  'Jean'      , 'McLife'     ,  5, 5, '2023-06-24 20:23:22.000000',  NOW() ),
(22,  'Steve'     , 'Nicolas'    , 15, 5, '2023-06-24 20:23:22.000000',  NOW() ),
(23,  'Sandy'     , 'Elysée'     , 23, 5, '2023-06-24 20:23:22.000000',  NOW() ),
(24,  'Michael'   , 'Bois-Flottant',1, 5, '2023-06-24 20:23:22.000000',  NOW() ),
(25,  'Pierre'    , 'Vert'       ,  1, 5, '2023-06-24 20:23:22.000000',  NOW() ),
(26,  'Christ'    , 'McLife'     ,  1, 5, '2023-06-24 20:23:22.000000',  NOW() ),
(27,  ''          , 'Baroblic'   , 21, 7, '2023-06-24 20:23:22.000000',  NOW() ),
(28,  'Axel'      , 'Begonia'    , 53, 7, '2023-06-24 20:23:22.000000',  NOW() ),
(29,  'Dédé'      , 'Lit'        , 42, 7, '2023-06-26 00:29:19.000000',  NOW() ),
(30,  'EZ'        , 'La béquille',  5, 7, '2023-06-26 00:29:51.000000',  NOW() );




-- --------------------------------------------------------



-- --------------------------------------------------------
--
-- Dumping data for table `matches`
--
INSERT INTO `matches` (id, team_a_id, team_b_id, start_date, end_date, odds_a, odds_b, score_a, score_b, status, weather, is_enabled, creation_date, modification_date) VALUES
( 1, 1, 2, '2023-06-28 13:00:00.000000', '2023-06-28 14:00:00.000000',  1.2,  2.5, 22, 10, 'GAME_OVER'         , 'WEATHER_SNOW' , b'1', '2023-06-26 01:14:38.000000', '2023-06-26 01:14:38.000000' ),
( 2, 3, 4, '2023-06-28 15:00:00.000000', '2023-06-28 16:00:00.000000',  2.8, 1.15, 11, 26, 'GAME_OVER'         , 'WEATHER_SNOW' , b'1', '2023-06-26 01:30:27.000000', '2023-06-26 01:30:27.000000' ),
( 3, 5, 6, '2023-06-28 17:00:00.000000', '2023-06-28 18:00:00.000000',  1.4,  1.3, 17, 17, 'GAME_OVER'         , 'WEATHER_SNOW' , b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
( 4, 7, 8, '2023-06-26 19:00:00.000000', '2023-06-26 20:00:00.000000',  1.2,  2.4, 24, 21, 'GAME_OVER'         , 'WEATHER_RAIN' , b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
( 5, 9,10, '2023-06-28 13:00:00.000000', '2023-06-28 14:00:00.000000',  1.2,  2.5, 26, 24, 'GAME_OVER'         , 'WEATHER_RAIN' , b'1', '2023-06-26 01:14:38.000000', '2023-06-26 01:14:38.000000' ),
( 6, 4, 3, '2023-06-28 15:00:00.000000', '2023-06-28 16:00:00.000000',  2.8, 1.15, NULL, NULL, 'GAME_CANCELLED'    , 'WEATHER_CLOUD', b'1', '2023-06-26 01:30:27.000000', '2023-06-26 01:30:27.000000' ),
( 7, 4, 6, DATE_SUB(NOW(), INTERVAL 1 DAY), DATE_SUB( DATE_ADD(NOW(),INTERVAL 1 HOUR), INTERVAL 1 DAY),  1.4,  1.3, 21, 20, 'GAME_OVER'         , 'WEATHER_RAIN' , b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
( 8, 2, 6, DATE_SUB(NOW(), INTERVAL 12 HOUR), DATE_SUB(NOW(), INTERVAL 11 HOUR),  1.2,  2.4, 22, 12, 'GAME_OVER'         , 'WEATHER_CLOUD', b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
( 9, 4, 8, DATE_SUB(NOW(), INTERVAL 10 HOUR), DATE_SUB(NOW(), INTERVAL 9 HOUR),  1.2,  2.5, 25, 13, 'GAME_OVER'         , 'WEATHER_RAIN' , b'1', '2023-06-26 01:14:38.000000', '2023-06-26 01:14:38.000000' ),
(10, 5, 3, DATE_SUB(NOW(), INTERVAL 8 HOUR), DATE_SUB(NOW(), INTERVAL 7 HOUR),  2.8, 1.15, 3, 72, 'GAME_OVER', 'WEATHER_SUN'  , b'1', '2023-06-26 01:30:27.000000', '2023-06-26 01:30:27.000000' ),
(11, 6, 8, DATE_SUB(NOW(), INTERVAL 6 HOUR), DATE_SUB(NOW(), INTERVAL 5 HOUR),  1.4,  1.3, 31, 7, 'GAME_OVER', 'WEATHER_CLOUD', b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
(12, 5, 7, DATE_SUB(NOW(), INTERVAL 4 HOUR), DATE_SUB(NOW(), INTERVAL 3 HOUR),  1.2,  2.4, 14, 18, 'GAME_OVER', 'WEATHER_CLOUD', b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
(13, 4, 2, DATE_SUB(NOW(), INTERVAL 2 HOUR), DATE_SUB(NOW(), INTERVAL 1 HOUR),  1.2,  2.5, 17, 22, 'GAME_OVER', 'WEATHER_SUN'  , b'1', '2023-06-26 01:14:38.000000', '2023-06-26 01:14:38.000000' ),
(14, 1, 3, DATE_SUB(NOW(), INTERVAL 1 MINUTE), DATE_ADD(NOW(),INTERVAL 1 HOUR),  2.8, 1.15, NULL, NULL, 'GAME_STARTED'      , 'WEATHER_CLOUD', b'1', '2023-06-26 01:30:27.000000', '2023-06-26 01:30:27.000000' ),
(15, 4, 6, DATE_ADD(NOW(),INTERVAL 1 HOUR), DATE_ADD(NOW(),INTERVAL 2 HOUR),  1.4,  1.3, NULL, NULL, 'GAME_NOT_STARTED'  , 'WEATHER_SUN'  , b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
(16, 2, 6, DATE_ADD(NOW(),INTERVAL 3 HOUR), DATE_ADD(NOW(),INTERVAL 4 HOUR),  1.2,  2.4, NULL, NULL, 'GAME_NOT_STARTED'  , 'WEATHER_CLOUD', b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
(17, 4, 2, DATE_ADD(NOW(),INTERVAL 5 HOUR), DATE_ADD(NOW(),INTERVAL 6 HOUR),  1.2,  2.5, NULL, NULL, 'GAME_NOT_STARTED'  , 'WEATHER_SUN'  , b'1', '2023-06-26 01:14:38.000000', '2023-06-26 01:14:38.000000' ),
(18, 4, 3, DATE_ADD(NOW(),INTERVAL 7 HOUR), DATE_ADD(NOW(),INTERVAL 8 HOUR),  2.8, 1.15, NULL, NULL, 'GAME_NOT_STARTED'  , 'WEATHER_CLOUD', b'1', '2023-06-26 01:30:27.000000', '2023-06-26 01:30:27.000000' ),
(19, 4, 6, DATE_ADD(NOW(),INTERVAL 9 HOUR), DATE_ADD(NOW(),INTERVAL 10 HOUR), 1.8, 1.4,NULL, NULL, 'GAME_NOT_STARTED'  , 'WEATHER_SUN'  , b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' ),
(20, 2, 6, DATE_ADD(NOW(),INTERVAL 11 HOUR), DATE_ADD(NOW(),INTERVAL 12 HOUR),  1.2,  2.4, NULL, NULL, 'GAME_NOT_SCHEDULED'  , 'WEATHER_SUN'  , b'1', '2023-06-26 01:31:57.000000', '2023-06-26 01:31:57.000000' );

-- --------------------------------------------------------

--
-- Dumping data for table `bets`
--
INSERT INTO `bets` (id, match_id, wager, final_odds, bet_status, creation_date, modification_date) VALUES
(1,  1,  10, 1.45, 'BET_LOSER', '2023-06-24 20:30:00.000000', '2023-06-24 21:30:00.000000'),
(2,  1, 100,  2.1, 'BET_WINNER', '2023-06-26 13:00:00.000000', '2023-06-26 14:00:00.000000'),
(3,  1,  50,  2.3, 'BET_LOSER', '2023-06-26 15:00:00.000000', '2023-06-26 16:00:00.000000'),
(4,  2,  10,  1.3, 'BET_LOSER', '2023-06-26 17:00:00.000000', '2023-06-26 18:00:00.000000'),
(5,  2,  40,  2.5, 'BET_WINNER', '2023-06-26 19:00:00.000000', '2023-06-26 20:00:00.000000'),
(6,  2, 100,  2.3, 'BET_LOSER', '2023-06-27 14:00:00.000000', '2023-06-26 15:00:00.000000'),
(7,  3,  50,  2.1, 'BET_WINNER', '2023-06-27 15:00:00.000000', '2023-06-26 16:00:00.000000'),
(8,  3,  50,  2.1, 'BET_OPENED', '2023-06-27 17:00:00.000000', '2023-06-26 18:00:00.000000'),
(9,  3,  75,  2.3, 'BET_WINNER', '2023-06-27 19:00:00.000000', '2023-06-26 20:00:00.000000'),
(10, 3, 100,  2.1, 'BET_OPENED', '2023-06-28 13:00:00.000000', '2023-06-28 13:00:00.000000'),
(11, 3,  50,  2.2, 'BET_OPENED', '2023-06-28 15:00:00.000000', '2023-06-28 15:00:00.000000'),
(12,  4, 100,  2.1, 'BET_CANCELLED', '2023-06-28 19:00:00.000000', NOW()),
(13,  4,  50,  2.3, 'BET_CANCELLED', '2023-06-28 19:22:00.000000', NOW()),
(14,  4,  10,  1.3, 'BET_CANCELLED', '2023-06-28 19:23:00.000000', NOW()),
(15,  2,  40,  2.5, 'BET_OPENED', NOW(), NOW()),
(16,  2, 100,  2.3, 'BET_OPENED', NOW(), NOW()),
(17,  3,  50,  2.1, 'BET_OPENED', NOW(), NOW()),
(18,  3,  50,  2.1, 'BET_OPENED', NOW(), NOW()),
(19,  3,  75,  2.3, 'BET_OPENED', NOW(), NOW()),
(20, 3, 100,  2.1, 'BET_OPENED', NOW(), NOW()),
(21, 3,  50,  2.2, 'BET_OPENED', NOW(), NOW());


-- --------------------------------------------------------

--
-- Dumping data for table `users_bets`
--

INSERT INTO `users_bets` (`user_id`, `bet_id`) VALUES
(  1,  1),
(  2,  2),
(  2,  3),
( 10,  4),
( 10,  5),
( 10,  6),
(  6,  7),
( 14,  8),
( 14,  9),
(  1, 10),
(  6, 11),
(  7, 12),
(  7, 13),
(  7, 14),
(  8, 15),
(  9, 16),
(  9, 18),
( 10, 19),
( 10, 20);