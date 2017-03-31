-- phpMyAdmin SQL Dump
-- version 4.4.15.5
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1:3306
-- Generation Time: Mar 31, 2017 at 03:31 PM
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

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE IF NOT EXISTS `account` (
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

-- --------------------------------------------------------

--
-- Table structure for table `accounttype`
--

CREATE TABLE IF NOT EXISTS `accounttype` (
  `id` int(11) NOT NULL,
  `type` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `adresse`
--

CREATE TABLE IF NOT EXISTS `adresse` (
  `id` int(11) NOT NULL,
  `idcpville` int(11) NOT NULL,
  `lign1` varchar(250) NOT NULL,
  `lign2` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `advisor`
--

CREATE TABLE IF NOT EXISTS `advisor` (
  `id` int(11) NOT NULL,
  `idagency` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstname` varchar(250) NOT NULL,
  `date_assignment` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `agency`
--

CREATE TABLE IF NOT EXISTS `agency` (
  `id` int(11) NOT NULL,
  `idadress` int(11) NOT NULL,
  `idbank` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `countercode` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `assign`
--

CREATE TABLE IF NOT EXISTS `assign` (
  `main` varchar(250) NOT NULL,
  `idowner` int(11) NOT NULL,
  `idaccount` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `bank`
--

CREATE TABLE IF NOT EXISTS `bank` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `bankcode` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE IF NOT EXISTS `category` (
  `id` int(11) NOT NULL,
  `wording` varchar(250) NOT NULL,
  `idcategory` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `countrycode`
--

CREATE TABLE IF NOT EXISTS `countrycode` (
  `id` int(11) NOT NULL,
  `code` char(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `cpville`
--

CREATE TABLE IF NOT EXISTS `cpville` (
  `id` int(11) NOT NULL,
  `cp` varchar(50) NOT NULL,
  `ville` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `frequency`
--

CREATE TABLE IF NOT EXISTS `frequency` (
  `id` int(11) NOT NULL,
  `unit` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `owner`
--

CREATE TABLE IF NOT EXISTS `owner` (
  `id` int(11) NOT NULL,
  `name` varchar(250) NOT NULL,
  `firstname` varchar(250) NOT NULL,
  `telnumber` varchar(250) NOT NULL,
  `birthday` date NOT NULL,
  `login` varchar(250) NOT NULL,
  `psd` varchar(250) NOT NULL,
  `idadress` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `periodictransaction`
--

CREATE TABLE IF NOT EXISTS `periodictransaction` (
  `id` int(11) NOT NULL,
  `wording` varchar(250) NOT NULL,
  `transactionvalue` double(16,2) NOT NULL,
  `date_transaction` datetime NOT NULL,
  `end_date_transaction` date DEFAULT NULL,
  `day_number` int(11) NOT NULL,
  `idaccount` int(11) NOT NULL,
  `idtransaction_type` int(11) NOT NULL,
  `idtarget_transaction` int(11) DEFAULT NULL,
  `idcategory` int(11) DEFAULT NULL,
  `idfrequency` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `targettransaction`
--

CREATE TABLE IF NOT EXISTS `targettransaction` (
  `id` int(11) NOT NULL,
  `summary` varchar(250) NOT NULL,
  `IBAN` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `transactiontype`
--

CREATE TABLE IF NOT EXISTS `transactiontype` (
  `id` int(11) NOT NULL,
  `wording` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

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
  ADD KEY `FK_pt_acc` (`idaccount`),
  ADD KEY `FK_pt_trtyp` (`idtransaction_type`),
  ADD KEY `FK_pt_tartr` (`idtarget_transaction`),
  ADD KEY `FK_pt_cat` (`idcategory`),
  ADD KEY `FK_pt_freq` (`idfrequency`);

--
-- Indexes for table `targettransaction`
--
ALTER TABLE `targettransaction`
  ADD PRIMARY KEY (`id`);

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `accounttype`
--
ALTER TABLE `accounttype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `adresse`
--
ALTER TABLE `adresse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `advisor`
--
ALTER TABLE `advisor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `agency`
--
ALTER TABLE `agency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `bank`
--
ALTER TABLE `bank`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `countrycode`
--
ALTER TABLE `countrycode`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `cpville`
--
ALTER TABLE `cpville`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `frequency`
--
ALTER TABLE `frequency`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `owner`
--
ALTER TABLE `owner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `periodictransaction`
--
ALTER TABLE `periodictransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `targettransaction`
--
ALTER TABLE `targettransaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transactiontype`
--
ALTER TABLE `transactiontype`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
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
  ADD CONSTRAINT `FK_pt_acc` FOREIGN KEY (`idaccount`) REFERENCES `account` (`id`),
  ADD CONSTRAINT `FK_pt_cat` FOREIGN KEY (`idcategory`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `FK_pt_freq` FOREIGN KEY (`idfrequency`) REFERENCES `frequency` (`id`),
  ADD CONSTRAINT `FK_pt_tartr` FOREIGN KEY (`idtarget_transaction`) REFERENCES `targettransaction` (`id`),
  ADD CONSTRAINT `FK_pt_trtyp` FOREIGN KEY (`idtransaction_type`) REFERENCES `transactiontype` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
