-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1:3306
-- Généré le :  Mer 03 Mai 2017 à 15:44
-- Version du serveur :  5.6.34-log
-- Version de PHP :  7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `banquedatabase`
--
CREATE DATABASE IF NOT EXISTS `banquedatabase` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `banquedatabase`;

-- --------------------------------------------------------

--
-- Structure de la table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `number` varchar(250) NOT NULL,
  `creationDate` date NOT NULL,
  `initialBalance` double(16,2) NOT NULL,
  `interestRate` double(4,2) NOT NULL,
  `overdraft` double(16,2) NOT NULL,
  `alertThreshold` double(16,2) DEFAULT NULL,
  `agioRate` double(4,2) NOT NULL,
  `idCountryCode` int(11) NOT NULL,
  `idAgency` int(11) NOT NULL,
  `idAccountType` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `account`
--

INSERT INTO `account` (`id`, `description`, `number`, `creationDate`, `initialBalance`, `interestRate`, `overdraft`, `alertThreshold`, `agioRate`, `idCountryCode`, `idAgency`, `idAccountType`) VALUES
(1, 'c''est mon compte à moi', '1234 1234 1234', '2017-04-01', 100.00, 2.25, 0.00, 10.00, 0.00, 1, 1, 1),
(2, 'un compte epargne', '1111 2222 3333', '2017-03-20', 2000.00, 10.00, -100.00, 100.00, 0.00, 2, 1, 2),
(3, 'un compte courant', '4321 4321 4321', '2017-04-01', 152.45, 0.00, 0.00, NULL, 0.00, 3, 1, 3),
(4, 'qwe', '058516851', '2017-04-05', 0.00, 2.25, 0.00, NULL, 0.00, 3, 1, 3),
(5, 'qawe', '0585146851', '2017-04-05', 0.00, 2.25, 0.00, NULL, 0.00, 3, 1, 3),
(6, 'c''est un compte', '0585146851', '2017-04-05', 0.00, 0.00, 0.00, NULL, 0.00, 3, 2, 3),
(7, 'rryuk', '45645645', '2017-04-04', 44.00, 0.00, 0.00, NULL, 0.00, 2, 3, 2),
(8, 'rtgrtyu', '6496', '2017-04-19', 1.00, 0.00, 0.00, NULL, 0.00, 1, 2, 3),
(9, 'zrtyzrty', '9879984987', '2017-04-05', 0.00, 0.00, 0.00, NULL, 0.00, 2, 4, 2),
(10, 'try', '45624(', '2017-01-01', 100.00, 2.25, -500.00, 10.00, 0.00, 1, 2, 2),
(11, 'goooo', '345678', '2017-01-01', 120.00, 2.00, -500.00, 20.00, 14.50, 1, 3, 2),
(12, 'try2', '34567', '2017-01-01', 100.00, 2.00, -100.00, 12.00, 15.00, 1, 3, 2);

-- --------------------------------------------------------

--
-- Structure de la table `accounttype`
--

CREATE TABLE IF NOT EXISTS `accounttype` (
  `id` int(11) NOT NULL,
  `type` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `accounttype`
--

INSERT INTO `accounttype` (`id`, `type`) VALUES
(1, 'epargne'),
(2, 'livret A'),
(3, 'courant');

-- --------------------------------------------------------

--
-- Structure de la table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL,
  `idCpVille` int(11) NOT NULL,
  `line1` varchar(250) NOT NULL,
  `line2` varchar(250) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `address`
--

INSERT INTO `address` (`id`, `idCpVille`, `line1`, `line2`) VALUES
(1, 1, '114 rue Lucien Faure', NULL),
(2, 2, '1 rue truc', NULL),
(3, 1, '12 rue machin', 'batiment B\r\n'),
(5, 5, 'lu', ''),
(6, 6, 'qwe', ''),
(11, 11, 'somewhere', ''),
(12, 12, 'sdfgsdfg', ''),
(13, 13, 'd', ''),
(14, 14, 'rtuyityui', ''),
(15, 15, 'zrtyzrty', ''),
(16, 16, 'dd', '');

-- --------------------------------------------------------

--
-- Structure de la table `advisor`
--

CREATE TABLE IF NOT EXISTS `advisor` (
  `id` int(11) NOT NULL,
  `idAgency` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstName` varchar(250) NOT NULL,
  `assignmentDate` date NOT NULL,
  `phoneNumber` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `advisor`
--

INSERT INTO `advisor` (`id`, `idAgency`, `name`, `firstName`, `assignmentDate`, `phoneNumber`, `email`) VALUES
(1, 1, 'Mddb', 'Mme', '2017-03-09', '0909090909', 'dfdfgh@hj.ghj'),
(3, 2, 'Sdffg', 'Sdffgdfg', '2017-04-03', '0765656565', 'sdffgsdf@dssdfg.om');

-- --------------------------------------------------------

--
-- Structure de la table `agency`
--

CREATE TABLE IF NOT EXISTS `agency` (
  `id` int(11) NOT NULL,
  `idAddress` int(11) NOT NULL,
  `idBank` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `counterCode` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `agency`
--

INSERT INTO `agency` (`id`, `idAddress`, `idBank`, `name`, `counterCode`) VALUES
(1, 2, 1, 'hshdjne', '12345'),
(2, 3, 2, 'agence locale', '12345'),
(3, 14, 5, 'lo', 'lo'),
(4, 15, 8, 'zrtyzrtyzrt', 'zrtyzrt');

-- --------------------------------------------------------

--
-- Structure de la table `assign`
--

CREATE TABLE IF NOT EXISTS `assign` (
  `main` varchar(250) DEFAULT NULL,
  `idOwner` int(11) NOT NULL,
  `idAccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `assign`
--

INSERT INTO `assign` (`main`, `idOwner`, `idAccount`) VALUES
('askdfj', 3, 2),
('', 4, 1),
('askdfsj', 4, 4),
('', 4, 5),
('', 4, 6),
(NULL, 4, 7),
(NULL, 4, 8),
(NULL, 4, 9),
(NULL, 4, 10),
(NULL, 4, 11),
(NULL, 4, 12);

-- --------------------------------------------------------

--
-- Structure de la table `bank`
--

CREATE TABLE IF NOT EXISTS `bank` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `code` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `bank`
--

INSERT INTO `bank` (`id`, `name`, `code`) VALUES
(1, 'credit truc', '12345'),
(2, 'lionaise machin', '54321'),
(3, 'ryerty', '56756'),
(4, 'qsdfqsdf', '46464'),
(5, 'azer', '456'),
(6, 'qsdf', 'qsdf'),
(7, 'qsdf', 'qsdf'),
(8, 'fghdfgh', '654645'),
(9, 'tzeer', '34566');

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `idParentCategory` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `category`
--

INSERT INTO `category` (`id`, `description`, `idParentCategory`) VALUES
(1, 'ma cate generique', NULL),
(2, 'alimentaire', NULL),
(15, 'Sport', 1),
(18, 'Velo', 15),
(19, 'Cinema', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `countrycode`
--

CREATE TABLE IF NOT EXISTS `countrycode` (
  `id` int(11) NOT NULL,
  `code` char(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `countrycode`
--

INSERT INTO `countrycode` (`id`, `code`) VALUES
(1, 'FR'),
(2, 'BE'),
(3, 'EN'),
(4, 'US'),
(5, 'ES');

-- --------------------------------------------------------

--
-- Structure de la table `cpville`
--

CREATE TABLE IF NOT EXISTS `cpville` (
  `id` int(11) NOT NULL,
  `zip` varchar(50) NOT NULL,
  `city` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `cpville`
--

INSERT INTO `cpville` (`id`, `zip`, `city`) VALUES
(1, '75000', 'Paris'),
(2, '33000', 'Bordeaux\r\n'),
(5, 'lu', 'Lu'),
(6, '001', 'Bordeaux'),
(11, '33000', 'Bordeaux'),
(12, 'sdfgsdfg', 'Sdffgsdffg'),
(13, 'd', 'D'),
(14, '9874987', 'Tyuityuity'),
(15, 'zrtyzry', 'Zrtyzrty'),
(16, '23455', 'Dd');

-- --------------------------------------------------------

--
-- Structure de la table `frequency`
--

CREATE TABLE IF NOT EXISTS `frequency` (
  `id` int(11) NOT NULL,
  `unit` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `frequency`
--

INSERT INTO `frequency` (`id`, `unit`) VALUES
(1, 'hebdomadaire'),
(2, 'mensuelle'),
(3, 'anuelle');

-- --------------------------------------------------------

--
-- Structure de la table `owner`
--

CREATE TABLE IF NOT EXISTS `owner` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstName` varchar(250) NOT NULL,
  `phoneNumber` varchar(250) NOT NULL,
  `birthday` date NOT NULL,
  `login` varchar(250) NOT NULL,
  `pswd` varchar(250) NOT NULL,
  `email` varchar(250) NOT NULL,
  `idAddress` int(11) NOT NULL,
  `salt` varchar(250) NOT NULL,
  `newUser` int(1) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `owner`
--

INSERT INTO `owner` (`id`, `name`, `firstName`, `phoneNumber`, `birthday`, `login`, `pswd`, `email`, `idAddress`, `salt`, `newUser`) VALUES
(1, 'Doe', 'John', '0000000000', '1986-07-28', 'mylogin', '62a5daea27481816ef8959019c78efa84d693dd3', '', 1, '', 0),
(2, 'lu', 'lu', '0678787878', '2017-03-27', 'lu', 'lu', 'lu@lu.lu', 5, 'dfsgdhf', 0),
(3, 'qwe', 'qwe', '0000000000', '2017-04-04', 'qwe', 'pvfNpk3t1zospwfY1yhVRZhe2qAPdmekQJ326Gla+hc=', 'q@w.fr', 6, 'aua+Ix9Lp1I=', 0),
(4, 'myname', 'myFirstName', '+33612345678', '2017-04-01', 'defaultUser', 'AOV1u5aSyu7dXvpDCJQdCyMp4Fo6Ut8V6LEqoz2nRYo=', 'default@truc.com', 11, '1HoGuIfDKgA=', 0),
(5, 'Dsfgsdffg', 'Sdffgsdffg', '0988888888', '2017-04-04', 'qgshdfj', 'cVUmnoGgh1ok0UyVhoLm9k2juaCbsdMnZKcNRn0U/L4=', 'd@d.dd', 12, 'XZIBN8WNdgY=', 0),
(6, 'D', 'D', '0945454545', '2017-04-05', 'd', '8dHSNNU5bgbprknvAST8iRAt4MDwR8GQjnxXa8DTT/U=', 'd@d.dd', 13, 'vRlyNbOyT7c=', 0);

-- --------------------------------------------------------

--
-- Structure de la table `periodictransaction`
--

CREATE TABLE IF NOT EXISTS `periodictransaction` (
  `id` int(11) NOT NULL,
  `endDate` date NOT NULL,
  `numberDefiningPeriodicity` int(11) NOT NULL,
  `idFreq` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `periodictransaction`
--

INSERT INTO `periodictransaction` (`id`, `endDate`, `numberDefiningPeriodicity`, `idFreq`) VALUES
(1, '2017-04-29', 2, 1);

-- --------------------------------------------------------

--
-- Structure de la table `targettransaction`
--

CREATE TABLE IF NOT EXISTS `targettransaction` (
  `id` int(11) NOT NULL,
  `summary` varchar(250) NOT NULL,
  `iban` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `targettransaction`
--

INSERT INTO `targettransaction` (`id`, `summary`, `iban`) VALUES
(1, 'un destinataire', 'FR7630004000031234567890143'),
(2, 'un autre destinataire', 'FR7630001007941234567890185');

-- --------------------------------------------------------

--
-- Structure de la table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `value` double(16,2) NOT NULL,
  `dateTransaction` date NOT NULL,
  `idAccount` int(11) NOT NULL,
  `idTransactionType` int(11) NOT NULL,
  `idTargetTransaction` int(11) DEFAULT NULL,
  `idCategory` int(11) DEFAULT NULL,
  `idPeriodicTransaction` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `transaction`
--

INSERT INTO `transaction` (`id`, `description`, `value`, `dateTransaction`, `idAccount`, `idTransactionType`, `idTargetTransaction`, `idCategory`, `idPeriodicTransaction`) VALUES
(2, 'un retrait', -150.00, '2017-04-03', 2, 1, 1, 1, NULL),
(12, 'un retrait', -50.00, '2017-04-03', 5, 1, NULL, NULL, NULL),
(20, 'j', 100.00, '2017-04-04', 1, 1, 1, NULL, NULL),
(21, 'TR1', 344.00, '2017-03-28', 6, 4, NULL, NULL, NULL),
(22, 'TR2', 6554.00, '2017-05-06', 6, 5, NULL, NULL, NULL),
(23, 'TR2', -3444.00, '2017-03-28', 6, 1, NULL, NULL, NULL),
(24, 'TR4', -5.00, '2017-04-01', 6, 4, NULL, NULL, NULL),
(25, 'TR5', 333.00, '2017-03-29', 6, 1, NULL, NULL, NULL),
(33, 'df', 99.00, '2017-04-11', 6, 4, NULL, 15, NULL),
(36, 'zdfg', 88.00, '2017-04-15', 1, 4, NULL, 18, NULL),
(37, 'df', 3.00, '2017-04-16', 1, 4, NULL, 19, NULL),
(38, 'balancetest', -7.00, '2017-05-01', 1, 1, NULL, 15, NULL),
(39, 'qsdsdf', -800.00, '2017-05-01', 1, 1, NULL, NULL, NULL),
(40, 'sdfsdf', 9.00, '2017-05-03', 1, 1, NULL, NULL, NULL),
(42, 'RR', 100.00, '2017-01-16', 4, 1, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `transactiontype`
--

CREATE TABLE IF NOT EXISTS `transactiontype` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Contenu de la table `transactiontype`
--

INSERT INTO `transactiontype` (`id`, `description`) VALUES
(1, 'je sais plus ce que c''est\r\n'),
(4, 'virement interne'),
(5, 'cheque');

--
-- Index pour les tables exportées
--

--
-- Index pour la table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_acc_acc` (`idAccountType`),
  ADD KEY `FK_acc_coun` (`idCountryCode`),
  ADD KEY `FK_acc_agen` (`idAgency`);

--
-- Index pour la table `accounttype`
--
ALTER TABLE `accounttype`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_AD_IDcpvi` (`idCpVille`);

--
-- Index pour la table `advisor`
--
ALTER TABLE `advisor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ADv_idagen` (`idAgency`);

--
-- Index pour la table `agency`
--
ALTER TABLE `agency`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ag_idad` (`idAddress`),
  ADD KEY `FK_ag_idban` (`idBank`);

--
-- Index pour la table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`idOwner`,`idAccount`),
  ADD KEY `FK_ass_accou` (`idAccount`);

--
-- Index pour la table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cat_wor` (`idParentCategory`);

--
-- Index pour la table `countrycode`
--
ALTER TABLE `countrycode`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `cpville`
--
ALTER TABLE `cpville`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `frequency`
--
ALTER TABLE `frequency`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idadress` (`idAddress`);

--
-- Index pour la table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_per_freq` (`idFreq`);

--
-- Index pour la table `targettransaction`
--
ALTER TABLE `targettransaction`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_pt_acc` (`idAccount`),
  ADD KEY `FK_pt_trtyp` (`idTransactionType`),
  ADD KEY `FK_pt_tartr` (`idTargetTransaction`),
  ADD KEY `FK_pt_cat` (`idCategory`),
  ADD KEY `idPeriodicTransaction` (`idPeriodicTransaction`);

--
-- Index pour la table `transactiontype`
--
ALTER TABLE `transactiontype`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT pour la table `accounttype`
--
ALTER TABLE `accounttype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT pour la table `advisor`
--
ALTER TABLE `advisor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `agency`
--
ALTER TABLE `agency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT pour la table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=20;
--
-- AUTO_INCREMENT pour la table `countrycode`
--
ALTER TABLE `countrycode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT pour la table `cpville`
--
ALTER TABLE `cpville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=17;
--
-- AUTO_INCREMENT pour la table `frequency`
--
ALTER TABLE `frequency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT pour la table `owner`
--
ALTER TABLE `owner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT pour la table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT pour la table `targettransaction`
--
ALTER TABLE `targettransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT pour la table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=43;
--
-- AUTO_INCREMENT pour la table `transactiontype`
--
ALTER TABLE `transactiontype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_acc_acc` FOREIGN KEY (`idAccountType`) REFERENCES `accounttype` (`id`),
  ADD CONSTRAINT `FK_acc_agen` FOREIGN KEY (`idAgency`) REFERENCES `agency` (`id`),
  ADD CONSTRAINT `FK_acc_coun` FOREIGN KEY (`idCountryCode`) REFERENCES `countrycode` (`id`);

--
-- Contraintes pour la table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK_AD_IDcpvi` FOREIGN KEY (`idCpVille`) REFERENCES `cpville` (`id`);

--
-- Contraintes pour la table `advisor`
--
ALTER TABLE `advisor`
  ADD CONSTRAINT `FK_ADv_idagen` FOREIGN KEY (`idAgency`) REFERENCES `agency` (`id`);

--
-- Contraintes pour la table `agency`
--
ALTER TABLE `agency`
  ADD CONSTRAINT `FK_ag_idad` FOREIGN KEY (`idAddress`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FK_ag_idban` FOREIGN KEY (`idBank`) REFERENCES `bank` (`id`);

--
-- Contraintes pour la table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `FK_ass_accou` FOREIGN KEY (`idAccount`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_ass_own` FOREIGN KEY (`idOwner`) REFERENCES `owner` (`id`);

--
-- Contraintes pour la table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK_cat_wor` FOREIGN KEY (`idParentCategory`) REFERENCES `category` (`id`);

--
-- Contraintes pour la table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `FK_own_add` FOREIGN KEY (`idAddress`) REFERENCES `address` (`id`);

--
-- Contraintes pour la table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  ADD CONSTRAINT `FK_per_freq` FOREIGN KEY (`idFreq`) REFERENCES `frequency` (`id`);

--
-- Contraintes pour la table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK_pt_acc` FOREIGN KEY (`idAccount`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_pt_cat` FOREIGN KEY (`idCategory`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK_pt_tartr` FOREIGN KEY (`idTargetTransaction`) REFERENCES `targettransaction` (`id`),
  ADD CONSTRAINT `FK_pt_trtyp` FOREIGN KEY (`idTransactionType`) REFERENCES `transactiontype` (`id`),
  ADD CONSTRAINT `FK_tr_pt` FOREIGN KEY (`idPeriodicTransaction`) REFERENCES `periodictransaction` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
