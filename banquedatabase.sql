-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 06, 2017 at 07:13 AM
-- Server version: 5.7.14
-- PHP Version: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `banquedatabase_v1`
--
CREATE DATABASE IF NOT EXISTS `banquedatabase_v1` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `banquedatabase_v1`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` int(11) NOT NULL,
  `descrip_type` varchar(250) NOT NULL,
  `account_number` varchar(250) NOT NULL,
  `creation_date` date NOT NULL,
  `first_total` double(16,2) NOT NULL,
  `interest_rate` double(4,2) NOT NULL,
  `overdraft` double(16,2) NOT NULL,
  `aler_thresh` double(16,2) DEFAULT NULL,
  `idcountrycode` int(11) NOT NULL,
  `idagency` int(11) NOT NULL,
  `idaccounttype` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `descrip_type`, `account_number`, `creation_date`, `first_total`, `interest_rate`, `overdraft`, `aler_thresh`, `idcountrycode`, `idagency`, `idaccounttype`) VALUES
(1, 'c\'est mon compte à moi', '1234 1234 1234', '2017-04-01', 100.00, 1.00, 0.00, 10.00, 1, 1, 1),
(2, 'un compte epargne', '1111 2222 3333', '2017-03-20', 2000.00, 10.00, -100.00, 100.00, 2, 1, 2),
(3, 'un compte courant', '4321 4321 4321', '2017-04-01', 152.45, 0.00, 0.00, NULL, 3, 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `accounttype`
--

CREATE TABLE `accounttype` (
  `id` int(11) NOT NULL,
  `type` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `accounttype`
--

INSERT INTO `accounttype` (`id`, `type`) VALUES
(1, 'epargne'),
(2, 'livret A'),
(3, 'courant');

-- --------------------------------------------------------

--
-- Table structure for table `adresse`
--

CREATE TABLE `adresse` (
  `id` int(11) NOT NULL,
  `idcpville` int(11) NOT NULL,
  `lign1` varchar(250) NOT NULL,
  `lign2` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `adresse`
--

INSERT INTO `adresse` (`id`, `idcpville`, `lign1`, `lign2`) VALUES
(1, 1, '114 rue Lucien Faure', NULL),
(2, 2, '1 rue truc', NULL),
(3, 1, '12 rue machin', 'batiment B\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `advisor`
--

CREATE TABLE `advisor` (
  `id` int(11) NOT NULL,
  `idagency` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstname` varchar(250) NOT NULL,
  `date_assignment` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `advisor`
--

INSERT INTO `advisor` (`id`, `idagency`, `name`, `firstname`, `date_assignment`) VALUES
(1, 1, 'M', 'Mme', '2017-03-01');

-- --------------------------------------------------------

--
-- Table structure for table `agency`
--

CREATE TABLE `agency` (
  `id` int(11) NOT NULL,
  `idadress` int(11) NOT NULL,
  `idbank` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `countercode` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `agency`
--

INSERT INTO `agency` (`id`, `idadress`, `idbank`, `name`, `countercode`) VALUES
(1, 2, 1, 'hshdjne', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE `assign` (
  `main` varchar(250) NOT NULL,
  `idowner` int(11) NOT NULL,
  `idaccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE `bank` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `bankcode` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank`
--

INSERT INTO `bank` (`id`, `name`, `bankcode`) VALUES
(1, 'credit truc', '12345'),
(2, 'lionaise machin', '54321');

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `wording` varchar(250) NOT NULL,
  `idcategory` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `wording`, `idcategory`) VALUES
(1, 'ma cate generique', NULL),
(2, 'alimentaire', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `countrycode`
--

CREATE TABLE `countrycode` (
  `id` int(11) NOT NULL,
  `code` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `cpville` (
  `id` int(11) NOT NULL,
  `cp` varchar(50) NOT NULL,
  `ville` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cpville`
--

INSERT INTO `cpville` (`id`, `cp`, `ville`) VALUES
(1, '75000', 'Paris'),
(2, '33000', 'Bordeaux\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `frequency`
--

CREATE TABLE `frequency` (
  `id` int(11) NOT NULL,
  `unit` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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

CREATE TABLE `owner` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstname` varchar(250) NOT NULL,
  `telnumber` varchar(250) NOT NULL,
  `birthday` date NOT NULL,
  `login` varchar(250) NOT NULL,
  `psd` varchar(250) NOT NULL,
  `idadress` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `owner`
--

INSERT INTO `owner` (`id`, `name`, `firstname`, `telnumber`, `birthday`, `login`, `psd`, `idadress`) VALUES
(1, 'Doe', 'John', '0000000000', '1986-07-28', 'mylogin', '62a5daea27481816ef8959019c78efa84d693dd3', 1);

-- --------------------------------------------------------

--
-- Table structure for table `periodictransaction`
--

CREATE TABLE `periodictransaction` (
  `id` int(11) NOT NULL,
  `end_date` date NOT NULL,
  `day_number` int(11) NOT NULL,
  `idFreq` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `periodictransaction`
--

INSERT INTO `periodictransaction` (`id`, `end_date`, `day_number`, `idFreq`) VALUES
(1, '2017-04-29', 2, 1);

-- --------------------------------------------------------

--
-- Table structure for table `targettransaction`
--

CREATE TABLE `targettransaction` (
  `id` int(11) NOT NULL,
  `summary` varchar(250) NOT NULL,
  `IBAN` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `targettransaction`
--

INSERT INTO `targettransaction` (`id`, `summary`, `IBAN`) VALUES
(1, 'un destinataire', 'FR76 1234 1234 1234 '),
(2, 'un autre destinataire', 'BE61 1111 2222 3333\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `wording` varchar(250) NOT NULL,
  `transactionvalue` double(16,2) NOT NULL,
  `date_transaction` datetime NOT NULL,
  `idaccount` int(11) NOT NULL,
  `idtransaction_type` int(11) NOT NULL,
  `idtarget_transaction` int(11) DEFAULT NULL,
  `idcategory` int(11) DEFAULT NULL,
  `idPeriodicTransaction` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `wording`, `transactionvalue`, `date_transaction`, `idaccount`, `idtransaction_type`, `idtarget_transaction`, `idcategory`, `idPeriodicTransaction`) VALUES
(1, 'un retrait', -150.00, '2017-04-03 00:00:00', 1, 1, NULL, 1, NULL),
(2, 'werwef ', 200.00, '2017-04-02 00:00:00', 2, 4, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `transactiontype`
--

CREATE TABLE `transactiontype` (
  `id` int(11) NOT NULL,
  `wording` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transactiontype`
--

INSERT INTO `transactiontype` (`id`, `wording`) VALUES
(1, 'je sais plus ce que c\'est\r\n'),
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
  ADD KEY `FK_acc_acc` (`idaccounttype`),
  ADD KEY `FK_acc_coun` (`idcountrycode`),
  ADD KEY `FK_acc_agen` (`idagency`);

--
-- Indexes for table `accounttype`
--
ALTER TABLE `accounttype`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_AD_IDcpvi` (`idcpville`);

--
-- Indexes for table `advisor`
--
ALTER TABLE `advisor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ADv_idagen` (`idagency`);

--
-- Indexes for table `agency`
--
ALTER TABLE `agency`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_ag_idad` (`idadress`),
  ADD KEY `FK_ag_idban` (`idbank`);

--
-- Indexes for table `assign`
--
ALTER TABLE `assign`
  ADD PRIMARY KEY (`idowner`,`idaccount`),
  ADD KEY `FK_ass_accou` (`idaccount`);

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
  ADD KEY `FK_cat_wor` (`idcategory`);

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
  ADD KEY `idadress` (`idadress`);

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
  ADD KEY `FK_pt_acc` (`idaccount`),
  ADD KEY `FK_pt_trtyp` (`idtransaction_type`),
  ADD KEY `FK_pt_tartr` (`idtarget_transaction`),
  ADD KEY `FK_pt_cat` (`idcategory`),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `accounttype`
--
ALTER TABLE `accounttype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `advisor`
--
ALTER TABLE `advisor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `agency`
--
ALTER TABLE `agency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `countrycode`
--
ALTER TABLE `countrycode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `cpville`
--
ALTER TABLE `cpville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `frequency`
--
ALTER TABLE `frequency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `targettransaction`
--
ALTER TABLE `targettransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `transactiontype`
--
ALTER TABLE `transactiontype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_acc_acc` FOREIGN KEY (`idaccounttype`) REFERENCES `accounttype` (`id`),
  ADD CONSTRAINT `FK_acc_agen` FOREIGN KEY (`idagency`) REFERENCES `agency` (`id`),
  ADD CONSTRAINT `FK_acc_coun` FOREIGN KEY (`idcountrycode`) REFERENCES `countrycode` (`id`);

--
-- Constraints for table `adresse`
--
ALTER TABLE `adresse`
  ADD CONSTRAINT `FK_AD_IDcpvi` FOREIGN KEY (`idcpville`) REFERENCES `cpville` (`id`);

--
-- Constraints for table `advisor`
--
ALTER TABLE `advisor`
  ADD CONSTRAINT `FK_ADv_idagen` FOREIGN KEY (`idagency`) REFERENCES `agency` (`id`);

--
-- Constraints for table `agency`
--
ALTER TABLE `agency`
  ADD CONSTRAINT `FK_ag_idad` FOREIGN KEY (`idadress`) REFERENCES `adresse` (`id`),
  ADD CONSTRAINT `FK_ag_idban` FOREIGN KEY (`idbank`) REFERENCES `bank` (`id`);

--
-- Constraints for table `assign`
--
ALTER TABLE `assign`
  ADD CONSTRAINT `FK_ass_accou` FOREIGN KEY (`idaccount`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_ass_own` FOREIGN KEY (`idowner`) REFERENCES `owner` (`id`);

--
-- Constraints for table `category`
--
ALTER TABLE `category`
  ADD CONSTRAINT `FK_cat_wor` FOREIGN KEY (`idcategory`) REFERENCES `category` (`id`);

--
-- Constraints for table `owner`
--
ALTER TABLE `owner`
  ADD CONSTRAINT `FK_own_add` FOREIGN KEY (`idadress`) REFERENCES `adresse` (`id`);

--
-- Constraints for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  ADD CONSTRAINT `FK_per_freq` FOREIGN KEY (`idFreq`) REFERENCES `frequency` (`id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK_pt_acc` FOREIGN KEY (`idaccount`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_pt_cat` FOREIGN KEY (`idcategory`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK_pt_tartr` FOREIGN KEY (`idtarget_transaction`) REFERENCES `targettransaction` (`id`),
  ADD CONSTRAINT `FK_pt_trtyp` FOREIGN KEY (`idtransaction_type`) REFERENCES `transactiontype` (`id`),
  ADD CONSTRAINT `FK_tr_pt` FOREIGN KEY (`idPeriodicTransaction`) REFERENCES `periodictransaction` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
