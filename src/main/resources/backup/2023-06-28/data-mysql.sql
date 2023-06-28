--
-- Database: `nullstein_sbdatabase`
--

-- --------------------------------------------------------

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`type`, `id`, `creation_date`, `email`, `firstname`, `is_enabled`, `is_pwd_checked`, `modification_date`, `lastname`, `password`, `role`, `is_super_admin`) VALUES
 ('U', 1, '2023-06-24 20:23:22.000000', 'ose.jose@toto.fr', 'José', b'1', b'1', '2023-06-24 20:23:22.000000', 'Osé', 'AZERTY', 'ROLE_USER', b'0'),
 ('U', 2, '2023-06-24 20:23:22.000000', 'marie.paul@email.fr', 'Paul', b'1', b'1', '2023-06-24 20:23:22.000000', 'Marie', 'QWERTZ', 'ROLE_USER', b'0'),
 ('A', 3, '2023-06-24 20:23:22.000000', 'jeffthetek@superbowl.fr', 'Jean-Francois', b'1', b'1', '2023-06-24 20:23:22.000000', 'Techos', 'admin34', 'ROLE_ADMIN', b'0'),
 ('A', 4, '2023-06-24 20:23:22.000000', 'josethesupertek@superbowl.fr', 'José', b'1', b'1', '2023-06-24 20:23:22.000000', 'F', 'superadmin34', 'ROLE_ADMIN', b'1'),
 ('C', 5, '2023-06-24 20:23:22.000000', 'csantana@santana.fr', 'Carlos', b'1', b'1', '2023-06-24 20:23:22.000000', 'Santana', 'como_va', 'ROLE_COMMENTATOR', b'0'),
 ('U', 6, '2023-06-26 11:59:20.310345', 'john.doe@doedoedoe.fr', 'John', b'1', b'1', '2023-06-26 11:59:20.310411', 'Doe', '1234', 'ROLE_USER', NULL),
 ('U', 7, '2023-06-26 15:04:50.000000', 'pierrefeu@jurassic.fr', 'Pierre', b'1', b'1', '2023-06-26 15:04:50.000000', 'feu', 'silexDu56', 'ROLE_USER', NULL),
 ('U', 10, '2023-06-26 15:12:04.000000', 'peter.fire@jurassic.co.uk', 'Peter', b'1', b'1', '2023-06-26 15:12:04.000000', 'fire', 'silexOf34', 'ROLE_USER', NULL),
 ('U', 11, '2023-06-26 15:12:04.000000', 'pmz@gmail.fr', 'Philippe-Mouloud', b'1', b'1', '2023-06-26 15:12:04.000000', 'Z', 'jAimePasm0nPr3nom', 'ROLE_USER', NULL),
 ('U', 12, '2023-06-26 15:12:04.000000', 'francois1er@yoohoo.fr', 'Francois', b'1', b'1', '2023-06-26 15:12:04.000000', 'Premier', 'JeSuisNiRoiNiM1nitre', 'ROLE_USER', NULL),
 ('U', 13, '2023-06-26 15:12:04.000000', 'louisxiv@verslinfinietaudela.fr', 'Louis', b'1', b'1', '2023-06-26 15:12:04.000000', 'Quatorze', 'LouisXIV', 'ROLE_USER', NULL),
 ('U', 14, '2023-06-26 15:12:04.000000', 'nicolenicklous@free.fr', 'Nicole', b'1', b'1', '2023-06-26 15:12:04.000000', 'Nicklous', 'tournevis62', 'ROLE_USER', NULL);


-- --------------------------------------------------------

--
-- Dumping data for table `countries`
--

INSERT INTO `countries` (`id`, `name`) VALUES
(5, 'Arizona'),
(3, 'Caroline du Sud'),
(2, 'Colorado'),
(7, 'country test'),
(1, 'Massachusetts'),
(6, 'Nebraska'),
(4, 'Tennessee');

-- --------------------------------------------------------

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`id`, `creation_date`, `modification_date`, `name`, `country_id`) VALUES
(1, '2023-06-24 20:23:22.000000', NOW(), 'Les chaussettes de Boston', 1),
(2, '2023-06-24 20:23:22.000000', NOW(), 'Les Dino perdus de Denver', 2),
(3, '2023-06-24 20:23:22.000000', NOW(), 'Les sirènes poilues d\'Atlanta', 6),
(4, '2023-06-24 20:23:22.000000', NOW(), 'Les canniches vaudous endormis de Nashville', 4),
(5, '2023-06-24 20:23:22.000000', NOW(), 'Les castors farcis de Phoenix', 5),
(6, '2023-06-24 20:23:22.000000', NOW(), 'Les framboises maudites de Omaha', 3),
(7, '2023-06-25 13:40:32.150292', NOW(), 'Nouvelle équipe', NULL),
(8, '2023-06-25 13:41:49.295950', NOW(), 'New Team', 3),
(10, '2023-06-25 14:24:03.741012', 'NOW()', 'Updated team', 7),
(13, '2023-06-25 17:44:24.145032', 'NOW()', 'Nouvelle équipe test', NULL);

-- --------------------------------------------------------

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`id`, `creation_date`, `firstname`, `modification_date`, `lastname`, `number`, `team_id`) VALUES
 (1, '2023-06-24 20:23:22.000000', 'Jimmy', '2023-06-24 20:23:22.000000', 'Feuille', 1, 1),
 (2, '2023-06-24 20:23:22.000000', 'Farrokh', '2023-06-24 20:23:22.000000', 'Bulsara', 1, 2),
 (3, '2023-06-24 20:23:22.000000', 'John', '2023-06-24 20:23:22.000000', 'Bonhomme', 1, 3),
 (4, '2023-06-24 20:23:22.000000', 'Robert', '2023-06-24 20:23:22.000000', 'Centrale', 1, 4),
 (5, '2023-06-24 20:23:22.000000', 'Jean-Paul', '2023-06-24 20:23:22.000000', 'Jaune', 1, 5),
 (6, '2023-06-24 20:23:22.000000', 'Jean', '2023-06-24 20:23:22.000000', 'Seigneur', 1, 6),
 (7, '2023-06-24 20:23:22.000000', 'Richard', '2023-06-24 20:23:22.000000', 'Pluskenoir', 21, 6),
 (8, '2023-06-24 20:23:22.000000', 'Roger', '2023-06-24 20:23:22.000000', 'Legantier', 53, 6),
 (9, '2023-06-26 00:29:19.000000', 'Yann', '2023-06-26 00:29:19.000000', 'Peste', 42, 6),
 (10, '2023-06-26 00:29:51.000000', 'Yann', '2023-06-26 00:29:51.000000', 'Gilet', 5, 6);

-- --------------------------------------------------------
--
-- Dumping data for table `matches`
--

INSERT INTO `matches` (`id`, `creation_date`, `end_date`, `is_enabled`, `modification_date`, `odds_a`, `odds_b`, `score_a`, `score_b`, `start_date`, `status`, `weather`, `team_a_id`, `team_b_id`) VALUES
(1, '2023-06-26 01:14:38.000000', '2023-06-28 14:12:35.287000', b'1', '2023-06-26 01:14:38.000000', 1.2, 2.5, NULL, NULL, '2023-06-28 13:12:35.287000', NULL, NULL, 4, 2),
(2, '2023-06-26 01:30:27.000000', '2023-06-28 17:28:52.184000', b'0', '2023-06-26 01:30:27.000000', 2.8, 1.15, NULL, NULL, '2023-06-28 16:28:52.184000', NULL, NULL, 4, 3),
(3, '2023-06-26 01:31:57.000000', '2023-06-28 19:28:52.184000', b'0', '2023-06-26 01:31:57.000000', 1.4, 1.3, NULL, NULL, '2023-06-28 18:28:52.184000', NULL, NULL, 4, 6),
(4, '2023-06-26 01:31:57.000000', '2023-06-26 13:30:52.184000', b'0', '2023-06-26 01:31:57.000000', 1.2, 2.4, NULL, NULL, '2023-06-26 14:30:52.184000', NULL, NULL, 2, 6);

-- --------------------------------------------------------

--
-- Dumping data for table `bets`
--

INSERT INTO `bets` (`id`, `bet_status`, `creation_date`, `final_odds`, `modification_date`, `wager`, `match_id`) VALUES
(1, 'BET_ALLOWED', '2023-06-24 20:23:22.000000', 1.45, '2023-06-24 20:23:22.000000', 10, 1),
(2, 'BET_ALLOWED', '2023-06-26 14:59:56.000000', 2.1, '2023-06-26 14:59:56.000000', 100, 1),
(3, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.3, '2023-06-26 15:00:00.000000', 50, 1),
(4, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 1.3, '2023-06-26 15:00:00.000000', 10, 2),
(5, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.5, '2023-06-26 15:00:00.000000', 40, 2),
(6, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.3, '2023-06-26 15:00:00.000000', 100, 2),
(7, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.1, '2023-06-26 15:00:00.000000', 50, 3),
(8, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.1, '2023-06-26 15:00:00.000000', 50, 3),
(9, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.3, '2023-06-26 15:00:00.000000', 75, 3),
(10, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.1, '2023-06-26 15:00:00.000000', 100, 3),
(11, 'BET_ALLOWED', '2023-06-26 15:00:00.000000', 2.2, '2023-06-26 15:00:00.000000', 50, 3);



-- --------------------------------------------------------

--
-- Dumping data for table `users_bets`
--

INSERT INTO `users_bets` (`user_id`, `bet_id`) VALUES
(1, 1),
(2, 2),
(2, 3),
(10, 4),
(10, 5),
(10, 6),
(3, 7),
(14, 8),
(14, 9),
(1, 10),
(3, 11);
