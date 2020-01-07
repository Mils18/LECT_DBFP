-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 07, 2020 at 03:39 PM
-- Server version: 10.1.39-MariaDB
-- PHP Version: 7.1.29

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gatdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billID` int(20) UNSIGNED NOT NULL,
  `transactionTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cashierID` int(3) UNSIGNED NOT NULL,
  `storeID` int(3) UNSIGNED NOT NULL,
  `paymentTypeID` int(2) UNSIGNED DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`billID`, `transactionTime`, `cashierID`, `storeID`, `paymentTypeID`) VALUES
(1, '2019-12-28 14:09:13', 1, 3, 4),
(3, '2019-12-28 14:50:42', 3, 5, 1),
(4, '2019-12-28 15:05:23', 2, 1, 3),
(6, '2020-01-07 13:19:08', 4, 2, 3);

-- --------------------------------------------------------

--
-- Table structure for table `cashier`
--

CREATE TABLE `cashier` (
  `cashierID` int(3) UNSIGNED NOT NULL,
  `cashierName` varchar(20) NOT NULL,
  `password` varchar(6) DEFAULT NULL,
  `admin` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cashier`
--

INSERT INTO `cashier` (`cashierID`, `cashierName`, `password`, `admin`) VALUES
(1, 'Erick', 'eri123', 0),
(2, 'John', 'joh123', 0),
(3, 'Dan', 'dan123', 0),
(4, 'Fatimah', 'fat123', 1),
(5, 'Suresep', '123', 1),
(10, 'aa', 'aa123', 1),
(12, 'cc', 'cc123', 0),
(13, 'JasonFlyBy', 'jas123', 0);

-- --------------------------------------------------------

--
-- Table structure for table `itemtransaction`
--

CREATE TABLE `itemtransaction` (
  `itemID` int(20) NOT NULL,
  `billID` int(20) UNSIGNED NOT NULL,
  `productID` int(10) UNSIGNED NOT NULL,
  `productName` varchar(30) NOT NULL,
  `productPrice` int(11) NOT NULL,
  `qty` int(3) NOT NULL,
  `subtotal` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `itemtransaction`
--

INSERT INTO `itemtransaction` (`itemID`, `billID`, `productID`, `productName`, `productPrice`, `qty`, `subtotal`) VALUES
(1, 1, 8, '', 0, 1, 0),
(2, 1, 10, '', 0, 1, 0),
(3, 1, 10, '', 0, 4, 0),
(4, 1, 9, '', 0, 3, 0),
(5, 1, 9, 'aaas', 15000, 1, 15000),
(6, 1, 9, 'aaas', 15000, 3, 45000),
(7, 1, 10, 'abc', 50000, 3, 150000),
(8, 1, 8, 'a', 20000, 1, 20000),
(10, 3, 10, 'abc', 50000, 1, 50000),
(11, 3, 8, 'a', 20000, 1, 20000),
(12, 4, 10, 'abc', 50000, 1, 50000),
(13, 4, 9, 'aaas', 15000, 1, 15000),
(15, 4, 10, 'abc', 50000, 1, 50000),
(23, 4, 8, 'a', 20000, 1, 20000);

-- --------------------------------------------------------

--
-- Table structure for table `paymenttype`
--

CREATE TABLE `paymenttype` (
  `paymentTypeID` int(2) UNSIGNED NOT NULL,
  `paymentName` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paymenttype`
--

INSERT INTO `paymenttype` (`paymentTypeID`, `paymentName`) VALUES
(1, 'Cash'),
(2, 'Debit'),
(3, 'Credit'),
(4, 'Go-pay'),
(5, 'Ovo');

-- --------------------------------------------------------

--
-- Table structure for table `products`
--

CREATE TABLE `products` (
  `productID` int(11) UNSIGNED NOT NULL,
  `productLocation` varchar(30) NOT NULL,
  `productName` varchar(30) NOT NULL,
  `barcodeNumber` varchar(30) NOT NULL,
  `productPrice` int(10) NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `products`
--

INSERT INTO `products` (`productID`, `productLocation`, `productName`, `barcodeNumber`, `productPrice`, `stock`) VALUES
(8, '2 Food Hall Grand Indonesia', 'a', 'a', 20000, 10),
(9, '2 Food Hall Grand Indonesia', 'aaas', '1231', 15000, 100),
(10, '4 Food Hall FX', 'abc', '1234', 50000, 10);

-- --------------------------------------------------------

--
-- Table structure for table `store`
--

CREATE TABLE `store` (
  `StoreID` int(3) UNSIGNED NOT NULL,
  `StoreName` varchar(255) NOT NULL,
  `StoreLocation` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `store`
--

INSERT INTO `store` (`StoreID`, `StoreName`, `StoreLocation`) VALUES
(1, 'Food Hall Senayan City', 'Jl. Asia Afrika No.19, RT.1/RW.3, Gelora, Kecamatan Tanah Abang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270'),
(2, 'Food Hall Grand Indonesia', 'Jl. M.H. Thamrin No.1, Kb. Melati, Tanahabang, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10250'),
(3, 'Food Hall Plaza Indonesia', 'Jl. M.H. Thamrin No.28-30, RT.9/RW.5, Gondangdia, Kec. Menteng, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10350'),
(4, 'Food Hall FX', 'Jalan Jenderal Sudirman, Gelora, RT.1/RW.3, Gelora, Kota Jakarta Pusat, Daerah Khusus Ibukota Jakarta 10270'),
(5, 'Food Hall PIM 2', 'Jalan Metro Pondok Indah, RT.1/RW.16, Pd. Pinang, Kec. Kby. Lama, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12310');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billID`),
  ADD KEY `bill_ibfk_1` (`cashierID`),
  ADD KEY `bill_ibfk_2` (`storeID`),
  ADD KEY `paymentTypeID` (`paymentTypeID`);

--
-- Indexes for table `cashier`
--
ALTER TABLE `cashier`
  ADD PRIMARY KEY (`cashierID`);

--
-- Indexes for table `itemtransaction`
--
ALTER TABLE `itemtransaction`
  ADD PRIMARY KEY (`itemID`),
  ADD KEY `billID` (`billID`),
  ADD KEY `productID` (`productID`);

--
-- Indexes for table `paymenttype`
--
ALTER TABLE `paymenttype`
  ADD PRIMARY KEY (`paymentTypeID`);

--
-- Indexes for table `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`productID`);

--
-- Indexes for table `store`
--
ALTER TABLE `store`
  ADD PRIMARY KEY (`StoreID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bill`
--
ALTER TABLE `bill`
  MODIFY `billID` int(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `cashier`
--
ALTER TABLE `cashier`
  MODIFY `cashierID` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `itemtransaction`
--
ALTER TABLE `itemtransaction`
  MODIFY `itemID` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `paymenttype`
--
ALTER TABLE `paymenttype`
  MODIFY `paymentTypeID` int(2) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `products`
--
ALTER TABLE `products`
  MODIFY `productID` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `store`
--
ALTER TABLE `store`
  MODIFY `StoreID` int(3) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`cashierID`) REFERENCES `cashier` (`cashierID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bill_ibfk_2` FOREIGN KEY (`storeID`) REFERENCES `store` (`StoreID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `bill_ibfk_3` FOREIGN KEY (`paymentTypeID`) REFERENCES `paymenttype` (`paymentTypeID`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `itemtransaction`
--
ALTER TABLE `itemtransaction`
  ADD CONSTRAINT `itemtransaction_ibfk_1` FOREIGN KEY (`billID`) REFERENCES `bill` (`billID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `itemtransaction_ibfk_2` FOREIGN KEY (`productID`) REFERENCES `products` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
