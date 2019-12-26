-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Dec 26, 2019 at 10:56 PM
-- Server version: 5.7.28-0ubuntu0.18.04.4
-- PHP Version: 7.2.24-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `wootlab-trial`
--

-- --------------------------------------------------------

--
-- Table structure for table `cart`
--

CREATE TABLE `cart` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  `product` varchar(255) NOT NULL,
  `product_image_url` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `shipping_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `category` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `category`, `created_at`, `updated_at`) VALUES
(1, 'Electronics', '2019-12-23 00:27:11.000000', '2019-12-23 00:27:12.000000'),
(2, 'Fashion', '2019-12-23 00:27:21.000000', '2019-12-23 00:27:22.000000'),
(3, 'Food', '2019-12-23 00:27:28.000000', '2019-12-23 00:27:29.000000'),
(4, 'Jewelry', '2019-12-23 00:27:36.000000', '2019-12-23 00:27:37.000000'),
(5, 'Toys', '2019-12-23 00:27:47.000000', '2019-12-23 00:27:47.000000');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `image`
--

CREATE TABLE `image` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `image`
--

INSERT INTO `image` (`id`, `created_at`, `updated_at`, `url`, `product_id`) VALUES
(1, '2019-12-23 00:42:09.000000', '2019-12-23 00:42:11.000000', '/data/product-images/1.jpg', 1),
(2, '2019-12-23 00:42:49.000000', '2019-12-23 00:42:50.000000', '/data/product-images/2a.jpg', 1),
(3, '2019-12-23 00:43:04.000000', '2019-12-23 00:43:05.000000', '/data/product-images/6.jpg', 1),
(4, '2019-12-23 00:43:19.000000', '2019-12-23 00:43:20.000000', '/data/product-images/12.jpg', 1),
(5, '2019-12-23 00:43:32.000000', '2019-12-23 00:43:33.000000', '/data/product-images/10.jpg', 1),
(6, '2019-12-23 00:43:53.000000', '2019-12-23 00:43:53.000000', '/data/product-images/2a.jpg', 2),
(7, '2019-12-23 00:44:17.000000', '2019-12-23 00:44:18.000000', '/data/product-images/4.jpg', 2),
(8, '2019-12-23 00:44:39.000000', '2019-12-23 00:44:39.000000', '/data/product-images/4c.jpg', 2),
(9, '2019-12-23 00:44:58.000000', '2019-12-23 00:44:58.000000', '/data/product-images/5.jpg', 2),
(10, '2019-12-23 00:45:10.000000', '2019-12-23 00:45:11.000000', '/data/product-images/3a.jpg', 2),
(11, '2019-12-23 00:45:34.000000', '2019-12-23 00:45:34.000000', '/data/product-images/4.jpg', 3),
(12, '2019-12-23 00:45:57.000000', '2019-12-23 00:45:58.000000', '/data/product-images/11.jpg', 3),
(13, '2019-12-23 00:46:24.000000', '2019-12-23 00:46:28.000000', '/data/product-images/6.jpg', 3),
(14, '2019-12-23 00:46:32.000000', '2019-12-23 00:46:33.000000', '/data/product-images/4c.jpg', 3),
(15, '2019-12-23 00:46:59.000000', '2019-12-23 00:47:00.000000', '/data/product-images/5b.jpg', 3),
(16, '2019-12-25 19:07:46.000000', '2019-12-25 19:07:47.000000', '/data/product-images/5b.jpg', 4),
(17, '2019-12-25 19:08:55.000000', '2019-12-25 19:08:54.000000', '/data/product-images/4c.jpg', 4),
(18, '2019-12-25 19:09:02.000000', '2019-12-25 19:09:02.000000', '/data/product-images/11\n.jpg', 4),
(19, '2019-12-25 19:09:44.000000', '2019-12-25 19:09:42.000000', '/data/product-images/12.jpg', 5),
(20, '2019-12-25 19:09:48.000000', '2019-12-25 19:09:49.000000', '/data/product-images/6.jpg', 5),
(21, '2019-12-25 19:10:18.000000', '2019-12-25 19:10:16.000000', '/data/product-images/10.jpg', 5),
(22, '2019-12-25 19:10:27.000000', '2019-12-25 19:10:34.000000', '/data/product-images/4b.jpg', 6),
(23, '2019-12-25 19:10:47.000000', '2019-12-25 19:10:46.000000', '/data/product-images/12.jpg', 6),
(24, '2019-12-25 19:43:49.000000', '2019-12-25 19:43:47.000000', '/data/product-images/6.jpg', 7),
(25, '2019-12-25 19:43:56.000000', '2019-12-25 19:43:57.000000', '/data/product-images/5a.jpg', 7),
(26, '2019-12-25 19:44:27.000000', '2019-12-25 19:44:26.000000', '/data/product-images/3a.jpg', 7),
(27, '2019-12-25 19:44:37.000000', '2019-12-25 19:44:38.000000', '/data/product-images/4c.jpg', 8),
(28, '2019-12-25 19:45:28.000000', '2019-12-25 19:45:27.000000', '/data/product-images/1a.jpg', 8),
(29, '2019-12-25 19:45:48.000000', '2019-12-25 19:46:03.000000', '/data/product-images/3a.jpg', 9),
(30, '2019-12-25 19:46:20.000000', '2019-12-25 19:46:21.000000', '/data/product-images/3b.jpg', 9),
(31, '2019-12-25 19:46:40.000000', '2019-12-25 19:46:41.000000', '/data/product-images/6a.jpg', 9),
(32, '2019-12-25 19:47:21.000000', '2019-12-25 19:47:21.000000', '/data/product-images/8.jpg', 10),
(33, '2019-12-25 19:47:37.000000', '2019-12-25 19:47:38.000000', '/data/product-images/10.jpg', 10);

-- --------------------------------------------------------

--
-- Table structure for table `orders`
--

CREATE TABLE `orders` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  `product` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL,
  `transaction_reference` varchar(255) DEFAULT NULL,
  `product_id` int(11) NOT NULL,
  `product_image_url` varchar(255) NOT NULL,
  `shipping_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `description` varchar(20000) NOT NULL,
  `name` varchar(255) NOT NULL,
  `price` double NOT NULL,
  `rating` double NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `image_url` varchar(255) NOT NULL,
  `on_sale` bit(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `created_at`, `description`, `name`, `price`, `rating`, `updated_at`, `category_id`, `image_url`, `on_sale`) VALUES
(1, '2019-12-23 00:32:57.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Lorem ipsum dolor sit amet', 12000, 4, '2019-12-23 00:33:38.000000', 1, '/data/product-images/1.jpg', b'1'),
(2, '2019-12-23 00:36:25.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Consectetur adipiscing elit', 30800, 2, '2019-12-23 00:36:57.000000', 2, '/data/product-images/2a.jpg', NULL),
(3, '2019-12-23 00:37:03.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Sed do eiusmod tempor', 50000, 5, '2019-12-23 00:37:27.000000', 3, '/data/product-images/4.jpg', b'1'),
(4, '2019-12-23 00:37:33.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Mollit anim id est laborum', 1500, 3, '2019-12-23 00:38:03.000000', 4, '/data/product-images/5b.jpg', NULL),
(5, '2019-12-23 00:38:42.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Sunt in culpa qui officia', 43000, 1, '2019-12-23 00:38:09.000000', 5, '/data/product-images/12.jpg', b'1'),
(6, '2019-12-23 00:38:44.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Excepteur sint occaecat ', 1000, 5, '2019-12-23 00:39:30.000000', 3, '/data/product-images/4b.jpg', b'1'),
(7, '2019-12-23 00:39:37.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Lorem ipsum dolor', 5000, 2, '2019-12-23 00:39:54.000000', 5, '/data/product-images/6.jpg', NULL),
(8, '2019-12-23 00:40:03.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Magna aliqua', 6290, 4, '2019-12-23 00:40:23.000000', 1, '/data/product-images/4c.jpg', b'1'),
(9, '2019-12-23 00:40:28.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Ut labore et dolore magna aliqua', 7500, 1, '2019-12-23 00:40:53.000000', 2, '/data/product-images/3a.jpg', NULL),
(10, '2019-12-23 00:41:02.000000', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Duis aute irure dolor', 24900, 5, '2019-12-23 00:41:24.000000', 5, '/data/product-images/8.jpg', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `review`
--

CREATE TABLE `review` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `rating` double NOT NULL,
  `review` varchar(20000) NOT NULL,
  `reviewer` varchar(255) NOT NULL,
  `reviewer_image_url` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `review`
--

INSERT INTO `review` (`id`, `created_at`, `rating`, `review`, `reviewer`, `reviewer_image_url`, `updated_at`, `product_id`) VALUES
(1, '2019-12-23 00:53:51.000000', 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'John Doe', '/data/reviewers/1.jpg', '2019-12-23 00:54:28.000000', 1),
(2, '2019-12-23 00:55:26.000000', 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Johnathan Doe', '/data/reviewers/5.jpg', '2019-12-23 00:54:34.000000', 1),
(3, '2019-12-23 00:55:27.000000', 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Maria Doe', '/data/reviewers/image-4.jpg', '2019-12-23 00:54:36.000000', 1),
(4, '2019-12-23 00:55:36.000000', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Jonathan Doe', '/data/reviewers/image-1.jpg', '2019-12-23 00:55:55.000000', 2),
(5, '2019-12-23 00:58:11.000000', 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'John Doe', '/data/reviewers/3.jpg', '2019-12-23 00:56:06.000000', 2),
(6, '2019-12-23 00:58:13.000000', 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Maria Doe', '/data/reviewers/2.jpg', '2019-12-23 00:56:07.000000', 2),
(7, '2019-12-23 00:58:17.000000', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Anthonia Doe', '/data/reviewers/image-4.jpg', '2019-12-23 00:56:11.000000', 2),
(8, '2019-12-23 00:58:19.000000', 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Greg Doe', '/data/reviewers/image-3-big.jpg', '2019-12-23 00:56:13.000000', 2),
(9, '2019-12-23 00:58:29.000000', 3, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Robert Doe', '/data/reviewers/4.jpg', '2019-12-23 00:58:50.000000', 3),
(10, '2019-12-23 01:00:41.000000', 2, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'John Doe', '/data/reviewers/image-1-big.jpg', '2019-12-23 00:58:59.000000', 3),
(11, '2019-12-23 01:00:42.000000', 5, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Buchi Doe', '/data/reviewers/4.jpg', '2019-12-23 01:00:50.000000', 3),
(12, '2019-12-23 01:00:46.000000', 4, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Labeelah Doe', '/data/reviewers/5.jpg', '2019-12-23 01:00:52.000000', 3),
(13, '2019-12-23 01:00:48.000000', 1, 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.', 'Ronkis Doe', '/data/reviewers/image-1.jpg', '2019-12-23 01:00:55.000000', 3);

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `role` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`id`, `created_at`, `role`, `updated_at`) VALUES
(1, '2019-12-22 21:00:01.650000', 'ROLE_USER', '2019-12-22 22:00:11.000000');

-- --------------------------------------------------------

--
-- Table structure for table `shipping`
--

CREATE TABLE `shipping` (
  `id` int(11) NOT NULL,
  `address` varchar(10000) NOT NULL,
  `city` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `customer_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE `video` (
  `id` int(11) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `url` varchar(255) NOT NULL,
  `product_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `video`
--

INSERT INTO `video` (`id`, `created_at`, `updated_at`, `url`, `product_id`) VALUES
(1, '2019-12-23 00:48:40.000000', '2019-12-23 00:48:41.000000', '/data/product-videos/video1.mp4', 1),
(2, '2019-12-23 00:48:46.000000', '2019-12-23 00:48:47.000000', '/data/product-videos/video2.mp4', 1),
(3, '2019-12-23 00:48:51.000000', '2019-12-23 00:48:51.000000', '/data/product-videos/video2.mp4', 1),
(4, '2019-12-23 00:48:53.000000', '2019-12-23 00:48:54.000000', '/data/product-videos/video2.mp4', 2),
(5, '2019-12-23 00:48:55.000000', '2019-12-23 00:48:56.000000', '/data/product-videos/video1.mp4', 2),
(6, '2019-12-23 00:48:58.000000', '2019-12-23 00:48:58.000000', '/data/product-videos/video2.mp4', 2),
(7, '2019-12-23 00:49:00.000000', '2019-12-23 00:49:00.000000', '/data/product-images/video1.mp4', 3),
(8, '2019-12-23 00:49:02.000000', '2019-12-23 00:49:02.000000', '/data/product-videos/video1.mp4', 3),
(9, '2019-12-23 00:49:04.000000', '2019-12-23 00:49:04.000000', '/data/product-videos/video2.mp4', 3),
(10, '2019-12-23 00:49:06.000000', '2019-12-23 00:49:06.000000', '/data/product-videos/video2.mp4', 4),
(11, '2019-12-23 00:49:08.000000', '2019-12-23 00:49:08.000000', '/data/product-videos/video1.mp4', 4),
(12, '2019-12-23 00:49:10.000000', '2019-12-23 00:49:11.000000', '/data/product-videos/video2.mp4', 4),
(13, '2019-12-23 00:49:12.000000', '2019-12-23 00:49:13.000000', '/data/product-videos/video2.mp4', 5),
(14, '2019-12-23 00:49:14.000000', '2019-12-23 00:49:15.000000', '/data/product-videos/video1.mp4', 5),
(15, '2019-12-23 00:49:16.000000', '2019-12-23 00:49:17.000000', '/data/product-vidoes/video1.mp4', 5);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `cart`
--
ALTER TABLE `cart`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKdebwvad6pp1ekiqy5jtixqbaj` (`customer_id`),
  ADD KEY `FKk5gbsbrtj0hblsyrijfse9jp` (`shipping_id`);

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_dwk6cx0afu8bs9o4t536v1j5v` (`email`),
  ADD KEY `FKo2oh87rk6lunf0lic1svc9y75` (`role_id`);

--
-- Indexes for table `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKgpextbyee3uk9u6o2381m7ft1` (`product_id`);

--
-- Indexes for table `orders`
--
ALTER TABLE `orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK624gtjin3po807j3vix093tlf` (`customer_id`),
  ADD KEY `FKsemahq4easllj6if07wtarony` (`shipping_id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`);

--
-- Indexes for table `review`
--
ALTER TABLE `review`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKiyof1sindb9qiqr9o8npj8klt` (`product_id`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_bjxn5ii7v7ygwx39et0wawu0q` (`role`);

--
-- Indexes for table `shipping`
--
ALTER TABLE `shipping`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmik3i0ljm7axxqm53w5a6n3bb` (`customer_id`);

--
-- Indexes for table `video`
--
ALTER TABLE `video`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK7fwitul6hsy9pep2ene1dhgux` (`product_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cart`
--
ALTER TABLE `cart`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `image`
--
ALTER TABLE `image`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;
--
-- AUTO_INCREMENT for table `orders`
--
ALTER TABLE `orders`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `review`
--
ALTER TABLE `review`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `shipping`
--
ALTER TABLE `shipping`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `video`
--
ALTER TABLE `video`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `cart`
--
ALTER TABLE `cart`
  ADD CONSTRAINT `FKdebwvad6pp1ekiqy5jtixqbaj` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FKk5gbsbrtj0hblsyrijfse9jp` FOREIGN KEY (`shipping_id`) REFERENCES `shipping` (`id`);

--
-- Constraints for table `customer`
--
ALTER TABLE `customer`
  ADD CONSTRAINT `FKo2oh87rk6lunf0lic1svc9y75` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`);

--
-- Constraints for table `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `FKgpextbyee3uk9u6o2381m7ft1` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `orders`
--
ALTER TABLE `orders`
  ADD CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  ADD CONSTRAINT `FKsemahq4easllj6if07wtarony` FOREIGN KEY (`shipping_id`) REFERENCES `shipping` (`id`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `review`
--
ALTER TABLE `review`
  ADD CONSTRAINT `FKiyof1sindb9qiqr9o8npj8klt` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

--
-- Constraints for table `shipping`
--
ALTER TABLE `shipping`
  ADD CONSTRAINT `FKmik3i0ljm7axxqm53w5a6n3bb` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);

--
-- Constraints for table `video`
--
ALTER TABLE `video`
  ADD CONSTRAINT `FK7fwitul6hsy9pep2ene1dhgux` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
