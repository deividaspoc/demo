-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 27, 2022 at 10:21 PM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `atsiskaitymas`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `id` int(11) NOT NULL,
  `name` varchar(32) NOT NULL,
  `summary` varchar(32) NOT NULL,
  `isbn` varchar(32) NOT NULL,
  `photo` varchar(32) NOT NULL,
  `pages` int(32) NOT NULL,
  `category` varchar(32) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`id`, `name`, `summary`, `isbn`, `photo`, `pages`, `category`, `user_id`) VALUES
(1, '', 'xxxxx', 'xxxxxxxx', 'xxxxxxxx', 22, 'xxxx', 3),
(3, 'xdxdxd', 'llllllll', 'llllllll', 'llllllllll', 99, 'lllllll', 1),
(4, 'aaaaaaaaaaaaaa', 'aaaaaaaaaaa', 'aaaaaaaa', 'aaaaaaa', 22, 'aaaaaa', 3),
(5, 'asdasdasd', 'gdfsgdfsdf', 'vxcvxdfw3e4', 'www.adasdasdasdas.com/12312/123', 532, 'asadasdx', 3);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
