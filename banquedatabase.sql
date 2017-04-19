-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Apr 19, 2017 at 02:24 PM
-- Server version: 5.6.34-log
-- PHP Version: 7.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banquedatabase`
--
CREATE DATABASE IF NOT EXISTS `banquedatabase` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `banquedatabase`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
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
  `idCountryCode` int(11) NOT NULL,
  `idAgency` int(11) NOT NULL,
  `idAccountType` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `description`, `number`, `creationDate`, `initialBalance`, `interestRate`, `overdraft`, `alertThreshold`, `idCountryCode`, `idAgency`, `idAccountType`) VALUES
(1, 'c''est mon compte à moi', '1234 1234 1234', '2017-04-01', 100.00, 1.00, 0.00, 10.00, 1, 1, 1),
(2, 'un compte epargne', '1111 2222 3333', '2017-03-20', 2000.00, 10.00, -100.00, 100.00, 2, 1, 2),
(3, 'un compte courant', '4321 4321 4321', '2017-04-01', 152.45, 0.00, 0.00, NULL, 3, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `accounttype`
--

CREATE TABLE IF NOT EXISTS `accounttype` (
  `id` int(11) NOT NULL,
  `type` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounttype`
--

INSERT INTO `accounttype` (`id`, `type`) VALUES
(1, 'epargne'),
(2, 'livret A'),
(3, 'courant');

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` int(11) NOT NULL,
  `idCpVille` int(11) NOT NULL,
  `line1` varchar(250) NOT NULL,
  `line2` varchar(250) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `idCpVille`, `line1`, `line2`) VALUES
(1, 1, '114 rue Lucien Faure', NULL),
(2, 2, '1 rue truc', NULL),
(3, 1, '12 rue machin', 'batiment B\r\n'),
(5, 5, 'lu', '');

-- --------------------------------------------------------

--
-- Table structure for table `advisor`
--

CREATE TABLE IF NOT EXISTS `advisor` (
  `id` int(11) NOT NULL,
  `idAgency` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstName` varchar(250) NOT NULL,
  `assignmentDate` date NOT NULL,
  `phoneNumber` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `advisor`
--

INSERT INTO `advisor` (`id`, `idAgency`, `name`, `firstName`, `assignmentDate`, `phoneNumber`, `email`) VALUES
(1, 1, 'M', 'Mme', '2017-03-01', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `agency`
--

CREATE TABLE IF NOT EXISTS `agency` (
  `id` int(11) NOT NULL,
  `idAddress` int(11) NOT NULL,
  `idBank` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `counterCode` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agency`
--

INSERT INTO `agency` (`id`, `idAddress`, `idBank`, `name`, `counterCode`) VALUES
(1, 2, 1, 'hshdjne', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE IF NOT EXISTS `assign` (
  `main` varchar(250) NOT NULL,
  `idOwner` int(11) NOT NULL,
  `idAccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE IF NOT EXISTS `bank` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `code` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`id`, `name`, `code`) VALUES
(1, 'credit truc', '12345'),
(2, 'lionaise machin', '54321');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `idParentCategory` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `description`, `idParentCategory`) VALUES
(1, 'ma cate generique', NULL),
(2, 'alimentaire', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `countrycode`
--

CREATE TABLE IF NOT EXISTS `countrycode` (
  `id` int(11) NOT NULL,
  `code` char(2) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `countrycode`
--

INSERT INTO `countrycode` (`id`, `code`) VALUES
(1, 'FR'),
(2, 'BE'),
(3, 'EN'),
(4, 'US'),
(5, 'ES');

-- --------------------------------------------------------

--
-- Table structure for table `cpville`
--

CREATE TABLE IF NOT EXISTS `cpville` (
  `id` int(11) NOT NULL,
  `zip` varchar(50) NOT NULL,
  `city` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cpville`
--

INSERT INTO `cpville` (`id`, `zip`, `city`) VALUES
(1, '75000', 'Paris'),
(2, '33000', 'Bordeaux\r\n'),
(5, 'lu', 'Lu');

-- --------------------------------------------------------

--
-- Table structure for table `frequency`
--

CREATE TABLE IF NOT EXISTS `frequency` (
  `id` int(11) NOT NULL,
  `unit` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `frequency`
--

INSERT INTO `frequency` (`id`, `unit`) VALUES
(1, 'hebdomadaire'),
(2, 'mensuelle'),
(3, 'anuelle');

-- --------------------------------------------------------

--
-- Table structure for table `owner`
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
  `salt` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`id`, `name`, `firstName`, `phoneNumber`, `birthday`, `login`, `pswd`, `email`, `idAddress`, `salt`) VALUES
(1, 'Doe', 'John', '0000000000', '1986-07-28', 'mylogin', '62a5daea27481816ef8959019c78efa84d693dd3', '', 1, ''),
(2, 'lu', 'lu', '0678787878', '2017-03-27', 'lu', 'lu', 'lu@lu.lu', 5, 'dfsgdhf');

-- --------------------------------------------------------

--
-- Table structure for table `periodictransaction`
--

CREATE TABLE IF NOT EXISTS `periodictransaction` (
  `id` int(11) NOT NULL,
  `endDate` date NOT NULL,
  `numberDefiningPeriodicity` int(11) NOT NULL,
  `idFreq` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periodictransaction`
--

INSERT INTO `periodictransaction` (`id`, `endDate`, `numberDefiningPeriodicity`, `idFreq`) VALUES
(1, '2017-04-29', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `targettransaction`
--

CREATE TABLE IF NOT EXISTS `targettransaction` (
  `id` int(11) NOT NULL,
  `summary` varchar(250) NOT NULL,
  `iban` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `targettransaction`
--

INSERT INTO `targettransaction` (`id`, `summary`, `iban`) VALUES
(1, 'un destinataire', 'FR76 1234 1234 1234 '),
(2, 'un autre destinataire', 'BE61 1111 2222 3333\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE IF NOT EXISTS `transaction` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL,
  `value` double(16,2) NOT NULL,
  `dateTransaction` datetime NOT NULL,
  `idAccount` int(11) NOT NULL,
  `idTransactionType` int(11) NOT NULL,
  `idTargetTransaction` int(11) DEFAULT NULL,
  `idCategory` int(11) DEFAULT NULL,
  `idPeriodicTransaction` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `description`, `value`, `dateTransaction`, `idAccount`, `idTransactionType`, `idTargetTransaction`, `idCategory`, `idPeriodicTransaction`) VALUES
(1, 'un retrait', -150.00, '2017-04-03 00:00:00', 1, 1, NULL, 1, NULL),
(2, 'werwef ', 200.00, '2017-04-02 00:00:00', 2, 4, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactiontype`
--

CREATE TABLE IF NOT EXISTS `transactiontype` (
  `id` int(11) NOT NULL,
  `description` varchar(250) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactiontype`
--

INSERT INTO `transactiontype` (`id`, `description`) VALUES
(1, 'je sais plus ce que c''est\r\n'),
(4, 'virement interne'),
(5, 'cheque');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_acc_acc` (`idAccountType`),
  ADD KEY `FK_acc_coun` (`idCountryCode`),
  ADD KEY `FK_acc_agen` (`idAgency`);

--
-- Indexes for table `accounttype`
--
ALTER TABLE `accounttype`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `address`
--
ALTER TABLE `address`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_AD_IDcpvi` (`idCpVille`);

--
-- Indexes for table `advisor`
--
ALTER TABLE `advisor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ADv_idagen` (`idAgency`);

--
-- Indexes for table `agency`
--
ALTER TABLE `agency`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ag_idad` (`idAddress`),
  ADD KEY `FK_ag_idban` (`idBank`);

--
-- Indexes for table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`idOwner`,`idAccount`),
  ADD KEY `FK_ass_accou` (`idAccount`);

--
-- Indexes for table `bank`
--
ALTER TABLE `bank`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_cat_wor` (`idParentCategory`);

--
-- Indexes for table `countrycode`
--
ALTER TABLE `countrycode`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `cpville`
--
ALTER TABLE `cpville`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `frequency`
--
ALTER TABLE `frequency`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`id`),
  ADD KEY `idadress` (`idAddress`);

--
-- Indexes for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_per_freq` (`idFreq`);

--
-- Indexes for table `targettransaction`
--
ALTER TABLE `targettransaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_pt_acc` (`idAccount`),
  ADD KEY `FK_pt_trtyp` (`idTransactionType`),
  ADD KEY `FK_pt_tartr` (`idTargetTransaction`),
  ADD KEY `FK_pt_cat` (`idCategory`),
  ADD KEY `idPeriodicTransaction` (`idPeriodicTransaction`);

--
-- Indexes for table `transactiontype`
--
ALTER TABLE `transactiontype`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `accounttype`
--
ALTER TABLE `accounttype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `address`
--
ALTER TABLE `address`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `advisor`
--
ALTER TABLE `advisor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `agency`
--
ALTER TABLE `agency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `countrycode`
--
ALTER TABLE `countrycode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `cpville`
--
ALTER TABLE `cpville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `frequency`
--
ALTER TABLE `frequency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `targettransaction`
--
ALTER TABLE `targettransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `transactiontype`
--
ALTER TABLE `transactiontype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_acc_acc` FOREIGN KEY (`idAccountType`) REFERENCES `accounttype` (`id`),
  ADD CONSTRAINT `FK_acc_agen` FOREIGN KEY (`idAgency`) REFERENCES `agency` (`id`),
  ADD CONSTRAINT `FK_acc_coun` FOREIGN KEY (`idCountryCode`) REFERENCES `countrycode` (`id`);

--
-- Constraints for table `address`
--
ALTER TABLE `address`
  ADD CONSTRAINT `FK_AD_IDcpvi` FOREIGN KEY (`idCpVille`) REFERENCES `cpville` (`id`);

--
-- Constraints for table `advisor`
--
ALTER TABLE `advisor`
  ADD CONSTRAINT `FK_ADv_idagen` FOREIGN KEY (`idAgency`) REFERENCES `agency` (`id`);

--
-- Constraints for table `agency`
--
ALTER TABLE `agency`
  ADD CONSTRAINT `FK_ag_idad` FOREIGN KEY (`idAddress`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `FK_ag_idban` FOREIGN KEY (`idBank`) REFERENCES `bank` (`id`);

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `FK_ass_accou` FOREIGN KEY (`idAccount`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_ass_own` FOREIGN KEY (`idOwner`) REFERENCES `owner` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK_cat_wor` FOREIGN KEY (`idParentCategory`) REFERENCES `category` (`id`);

--
-- Constraints for table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `FK_own_add` FOREIGN KEY (`idAddress`) REFERENCES `address` (`id`);

--
-- Constraints for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  ADD CONSTRAINT `FK_per_freq` FOREIGN KEY (`idFreq`) REFERENCES `frequency` (`id`);

--
-- Constraints for table `transaction`
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
