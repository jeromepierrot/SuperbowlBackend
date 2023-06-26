-- phpMyAdmin SQL Dump
-- version 5.1.4
-- https://www.phpmyadmin.net/
--
-- Host: mysql-nullstein.alwaysdata.net
-- Generation Time: Jun 26, 2023 at 03:50 PM
-- Server version: 10.6.11-MariaDB
-- PHP Version: 7.4.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `nullstein_sbdatabase`
--

-- --------------------------------------------------------

--
-- Table structure for table `bets`
--

CREATE TABLE `bets` (
  `id` bigint(20) NOT NULL,
  `bet_status` varchar(255) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `final_odds` float NOT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `wager` float NOT NULL,
  `match_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Table structure for table `comments`
--

CREATE TABLE `comments` (
  `id` bigint(20) NOT NULL,
  `edit_date` datetime(6) DEFAULT NULL,
  `post_content` varchar(255) DEFAULT NULL,
  `post_date` datetime(6) DEFAULT NULL,
  `commentator_user_id` bigint(20) DEFAULT NULL,
  `match_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `countries`
--

CREATE TABLE `countries` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Table structure for table `matches`
--

CREATE TABLE `matches` (
  `id` bigint(20) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `is_enabled` bit(1) NOT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `odds_a` float NOT NULL,
  `odds_b` float NOT NULL,
  `score_a` int(11) DEFAULT NULL,
  `score_b` int(11) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `weather` varchar(255) DEFAULT NULL,
  `team_a_id` bigint(20) DEFAULT NULL,
  `team_b_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Table structure for table `players`
--

CREATE TABLE `players` (
  `id` bigint(20) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `firstname` varchar(255) NOT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `number` int(11) NOT NULL,
  `team_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `players`
--

INSERT INTO `players` (`id`, `creation_date`, `firstname`, `modification_date`, `name`, `number`, `team_id`) VALUES
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
-- Table structure for table `teams`
--

CREATE TABLE `teams` (
  `id` bigint(20) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `country_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `teams`
--

INSERT INTO `teams` (`id`, `creation_date`, `modification_date`, `name`, `country_id`) VALUES
(1, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000', 'Les chaussettes de Boston', 1),
(2, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000', 'Les Dino perdus de Denver', 2),
(3, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000', 'Les sirènes poilues d\'Atlanta', 6),
(4, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000', 'Les canniches vaudous endormis de Nashville', 4),
(5, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000', 'Les castors farcis de Phoenix', 5),
(6, '2023-06-24 20:23:22.000000', '2023-06-24 20:23:22.000000', 'Les framboises maudites de Omaha', 3),
(7, '2023-06-25 13:40:32.150292', '2023-06-25 13:40:32.150318', 'Nouvelle équipe', NULL),
(8, '2023-06-25 13:41:49.295950', '2023-06-25 13:41:49.295974', 'New Team', 3),
(10, '2023-06-25 14:24:03.741012', '2023-06-25 14:24:03.741031', 'Updated team', 7),
(13, '2023-06-25 17:44:24.145032', '2023-06-25 17:44:24.145055', 'Nouvelle équipe test', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `type` varchar(31) NOT NULL,
  `id` bigint(20) NOT NULL,
  `creation_date` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `firstname` varchar(255) NOT NULL,
  `is_enabled` bit(1) NOT NULL,
  `is_pwd_checked` bit(1) NOT NULL,
  `modification_date` datetime(6) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) DEFAULT NULL,
  `is_super_admin` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`type`, `id`, `creation_date`, `email`, `firstname`, `is_enabled`, `is_pwd_checked`, `modification_date`, `name`, `password`, `role`, `is_super_admin`) VALUES
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
-- Table structure for table `users_bets`
--

CREATE TABLE `users_bets` (
  `user_id` bigint(20) NOT NULL,
  `bet_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users_bets`
--

INSERT INTO `users_bets` (`user_id`, `bet_id`) VALUES
(1, 1),
(2, 1),
(2, 1),
(3, 7),
(1, 10),
(2, 10);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bets`
--
ALTER TABLE `bets`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKli0mtw9rbf5buh7or9x11hfea` (`match_id`);

--
-- Indexes for table `comments`
--
ALTER TABLE `comments`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKcxy41eonkm9yb9upp2drc6a4f` (`commentator_user_id`),
  ADD KEY `FKfbv1dhb45w9v9k2wevfdy15h3` (`match_id`);

--
-- Indexes for table `countries`
--
ALTER TABLE `countries`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_countries_name` (`name`);

--
-- Indexes for table `matches`
--
ALTER TABLE `matches`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiuw7d7dus1a1d8v0ntqsk3avx` (`team_a_id`),
  ADD KEY `FK6ufkvysjla700dal951w3jhjt` (`team_b_id`);

--
-- Indexes for table `players`
--
ALTER TABLE `players`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK5nglidr00c4dyybl171v6kask` (`team_id`);

--
-- Indexes for table `teams`
--
ALTER TABLE `teams`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_teams_name` (`name`),
  ADD KEY `FK7oxg99kw26u7w7swuu6tivxmr` (`country_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_users_email` (`email`);

--
-- Indexes for table `users_bets`
--
ALTER TABLE `users_bets`
  ADD KEY `FKql0r9g0iew9vb1fevmdq9xyo3` (`bet_id`),
  ADD KEY `FKrg22y54nikqtpc6od6bnvq2u2` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bets`
--
ALTER TABLE `bets`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `comments`
--
ALTER TABLE `comments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `countries`
--
ALTER TABLE `countries`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `matches`
--
ALTER TABLE `matches`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `players`
--
ALTER TABLE `players`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `teams`
--
ALTER TABLE `teams`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bets`
--
ALTER TABLE `bets`
  ADD CONSTRAINT `FKli0mtw9rbf5buh7or9x11hfea` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`);

--
-- Constraints for table `comments`
--
ALTER TABLE `comments`
  ADD CONSTRAINT `FKcxy41eonkm9yb9upp2drc6a4f` FOREIGN KEY (`commentator_user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKfbv1dhb45w9v9k2wevfdy15h3` FOREIGN KEY (`match_id`) REFERENCES `matches` (`id`);

--
-- Constraints for table `matches`
--
ALTER TABLE `matches`
  ADD CONSTRAINT `FK6ufkvysjla700dal951w3jhjt` FOREIGN KEY (`team_b_id`) REFERENCES `teams` (`id`),
  ADD CONSTRAINT `FKiuw7d7dus1a1d8v0ntqsk3avx` FOREIGN KEY (`team_a_id`) REFERENCES `teams` (`id`);

--
-- Constraints for table `players`
--
ALTER TABLE `players`
  ADD CONSTRAINT `FK5nglidr00c4dyybl171v6kask` FOREIGN KEY (`team_id`) REFERENCES `teams` (`id`);

--
-- Constraints for table `teams`
--
ALTER TABLE `teams`
  ADD CONSTRAINT `FK7oxg99kw26u7w7swuu6tivxmr` FOREIGN KEY (`country_id`) REFERENCES `countries` (`id`);

--
-- Constraints for table `users_bets`
--
ALTER TABLE `users_bets`
--  ADD CONSTRAINT `FKdgdpf98q802gs22x9knsf6557` FOREIGN KEY (`user_id`) REFERENCES `bets` (`id`),
--  ADD CONSTRAINT `FKq9ufscl5qlka7h34i44i2ftro` FOREIGN KEY (`bet_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKql0r9g0iew9vb1fevmdq9xyo3` FOREIGN KEY (`bet_id`) REFERENCES `bets` (`id`),
  ADD CONSTRAINT `FKrg22y54nikqtpc6od6bnvq2u2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
