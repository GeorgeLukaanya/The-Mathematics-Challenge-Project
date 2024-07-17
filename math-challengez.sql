-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2024 at 03:54 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `math-challengez`
--

-- --------------------------------------------------------

--
-- Table structure for table `acceptedparticipants`
--

CREATE TABLE `acceptedparticipants` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `schoolRegNo` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `dateOfBirth` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `acceptedparticipants`
--

INSERT INTO `acceptedparticipants` (`id`, `username`, `schoolRegNo`, `email`, `firstName`, `lastName`, `dateOfBirth`) VALUES
(1, 'georgelukaanya', '2300700696', 'ltgwgeorge@gmail.com', 'George', 'Lukaanya', '2003-01-30');

-- --------------------------------------------------------

--
-- Table structure for table `answers`
--

CREATE TABLE `answers` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `answers` varchar(255) NOT NULL,
  `marks` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `answers`
--

INSERT INTO `answers` (`id`, `answers`, `marks`, `created_at`, `updated_at`) VALUES
(1, 'answers', 0, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(2, '2500', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(3, '68', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(4, '9', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(5, '9600', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(6, '2', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(7, '1683', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(8, '0.8636', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(9, '72', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(10, '6sqrt(2)', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(11, '5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(12, '16', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(13, '15', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(14, '6', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(15, '5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(16, '48', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(17, '45477', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(18, '70', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(19, '10', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(20, '120', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(21, '19', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(22, '3', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(23, '45511', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(24, '540', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(25, '12', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(26, '24', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(27, '24', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(28, '27', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(29, '150', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(30, '14', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(31, '50', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(32, '180', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(33, '45385', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(34, '50', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(35, '1005', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(36, '9600', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(37, '36', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(38, '5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(39, '12', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(40, '10', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(41, '54', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(42, '75', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(43, '8', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(44, '10', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(45, '1', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(46, '80', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(47, '4.5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(48, '17.5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(49, '28', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(50, '24', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(51, '1000', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(52, '30', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(53, '40°, 60°, 80°.', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(54, '50', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(55, '6', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(56, '72', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(57, '13', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(58, '32', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(59, '129', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(60, '12', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(61, '0.35', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(62, '4', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(63, '24', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(64, '60', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(65, '24', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(66, '27', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(67, '2', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(68, '72', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(69, '30', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(70, '63', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(71, '25', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(72, '43', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(73, '121', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(74, '96', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(75, '64', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(76, '28', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(77, '5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(78, '7', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(79, '41', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(80, '0.3', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(81, '19', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(82, '24', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(83, '36', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(84, '28', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(85, '60', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(86, '30', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(87, '24', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(88, '225', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(89, '6', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(90, '180', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(91, '16', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(92, '160', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(93, '7.5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(94, '120', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(95, '3.5', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(96, '37X1', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(97, '4', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(98, '108', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(99, '1026', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(100, '80', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06'),
(101, '270', 6, '2024-07-04 18:49:06', '2024-07-04 18:49:06');

-- --------------------------------------------------------

--
-- Table structure for table `challenges`
--

CREATE TABLE `challenges` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `open_date` date NOT NULL,
  `close_date` date NOT NULL,
  `duration` int(11) NOT NULL,
  `question_count` int(11) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `challenges`
--

INSERT INTO `challenges` (`id`, `name`, `open_date`, `close_date`, `duration`, `question_count`, `created_at`, `updated_at`) VALUES
(1, 'algebra', '2024-07-02', '2024-07-03', 60, 10, '2024-07-02 06:14:39', '2024-07-02 06:14:39'),
(6, 'calculus', '2024-07-05', '2024-07-06', 60, 10, '2024-07-02 06:19:50', '2024-07-02 06:19:50'),
(7, 'algebra', '2024-07-02', '2024-07-03', 30, 15, '2024-07-02 06:55:01', '2024-07-02 06:55:01');

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `column1` varchar(255) NOT NULL,
  `column2` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `failed_jobs`
--

CREATE TABLE `failed_jobs` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `connection` text NOT NULL,
  `queue` text NOT NULL,
  `payload` longtext NOT NULL,
  `exception` longtext NOT NULL,
  `failed_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `migrations`
--

CREATE TABLE `migrations` (
  `id` int(10) UNSIGNED NOT NULL,
  `migration` varchar(255) NOT NULL,
  `batch` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `migrations`
--

INSERT INTO `migrations` (`id`, `migration`, `batch`) VALUES
(1, '2014_10_12_000000_create_users_table', 1),
(2, '2014_10_12_100000_create_password_resets_table', 1),
(3, '2019_08_19_000000_create_failed_jobs_table', 1),
(4, '2019_12_14_000001_create_personal_access_tokens_table', 1),
(5, '2024_06_28_082559_create_schools_table', 1),
(6, '2024_06_28_083917_create_file_uploads_table', 2),
(7, '2024_06_28_092158_create_schools_table', 3),
(8, '2024_06_28_104319_create_file_uploads_table', 4),
(9, '2024_07_01_130604_create_data_table', 5),
(10, '2024_07_02_085841_create_challenges_table', 6),
(11, '2024_07_02_101043_create_questions_table', 7),
(12, '2024_07_02_102915_create_questions_table', 8),
(13, '2024_07_02_103346_create_questions_table', 9),
(15, '2024_07_02_105540_create_questions_table', 10),
(16, '2024_07_02_123531_create_questions_table', 11),
(17, '2024_07_02_125803_create_answers_table', 12),
(18, '2024_07_04_163934_create_questions_table', 13),
(19, '2024_07_04_203651_create_questions_table', 14),
(20, '2024_07_04_205823_create_questions_table', 15),
(21, '2024_07_04_211509_create_answers_table', 16),
(22, '2024_07_04_213759_create_answers_table', 17);

-- --------------------------------------------------------

--
-- Table structure for table `password_resets`
--

CREATE TABLE `password_resets` (
  `email` varchar(255) NOT NULL,
  `token` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `personal_access_tokens`
--

CREATE TABLE `personal_access_tokens` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `tokenable_type` varchar(255) NOT NULL,
  `tokenable_id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `token` varchar(64) NOT NULL,
  `abilities` text DEFAULT NULL,
  `last_used_at` timestamp NULL DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `questions`
--

CREATE TABLE `questions` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `question_text` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `questions`
--

INSERT INTO `questions` (`id`, `question_text`, `created_at`, `updated_at`) VALUES
(1, 'question_text', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(2, ' What is the sum of the first 50 odd numbers?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(3, 'A rectangular garden has a length of 15 meters and a width of 10 meters. If a pathway of 1 meter wide is built around it, what is the area of the pathway?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(4, '3𝑥=813 x =81, what is the value of 2𝑥+12x+1', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(5, 'If a car\'s value decreases by 20% each year, what will its value be after 2 years if it is currently worth $15,000?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(6, '9x−1=27.find x.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(7, 'Find the sum of all the multiples of 3 between 1 and 100.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(8, 'Convert 19/22 to a decimal (rounded to 4 decimal places).', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(9, 'What is the least common multiple (LCM) of 8, 9, and 12?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(10, 'Simplify: sqrt(50)+sqrt(32)−sqrt(18) ', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(11, 'If 2^x=32, what is 𝑥', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(12, 'If the sum of three consecutive even numbers is 54, what is the smallest number?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(13, 'What is the square root of 225?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(14, 'Find the greatest common divisor (GCD) of 54 and 24.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(15, 'Solve for x: 5x−7=18', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(16, 'What is 15% of 320?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(17, 'Simplify 98/56', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(18, 'A triangle has angles of 35° and 75°. What is the measure of the third angle?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(19, 'If the average of four numbers is 8 and the sum of three of them is 22, what is the fourth number?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(20, 'What is the value of 5!', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(21, 'Simplify the expression: 3(4+5)−2^3', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(22, 'A train travels at a speed of 60 km/h. How long will it take to travel 180 km?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(23, 'Convert 0.875 to a fraction in simplest form.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(24, 'What is the sum of the interior angles of a pentagon?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(25, 'What is the square root of 144?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(26, 'Calculate the least common multiple (LCM) of 8 and 12.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(27, 'A rectangle has a length of 10 cm and a diagonal of 26 cm. What is the width of the rectangle?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(28, 'What is the volume of a cube with side length of 3 cm?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(29, 'Convert 2.5 hours into minutes.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(30, 'What is the next number in the sequence: 2, 5, 8, 11, ...?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(31, 'If a book costs $12.50 and you buy 4 books, what is the total cost?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(32, 'How many minutes are there in 3 hours?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(33, 'Convert 0.75 to a fraction.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(34, 'What is 25% of 200?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(35, 'What is the sum of 348 and 657?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(36, 'If a car\'s value decreases by 20% each year, what will its value be after 2 years if it is currently worth $15,000?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(37, 'What is the perimeter of a right triangle with legs of 9 cm and 12 cm?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(38, 'If 3 times a number plus 5 is equal to 20, what is the number?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(39, 'A farmer has chickens and cows. There are 20 animals in total, and the number of legs is 56. How many chickens are there?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(40, 'The sum of the ages of a father and his son is 50 years. The father is 4 times as old as his son. How old is the son?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(41, 'The length of a rectangle is twice its width. If the perimeter of the rectangle is 36 cm, what is the area of the rectangle?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(42, 'If you spend $45, which is 3/5 of your total money, how much money did you have initially?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(43, 'The difference between the squares of two consecutive even numbers is 60. What are the numbers?v', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(44, 'If 5 more than twice a number is 25, what is the number?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(45, 'A number when divided by 4 leaves a remainder of 3. What is the remainder when the square of this number is divided by 4?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(46, 'A train travels 60 km in 45 minutes. What is the train’s speed in km/h?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(47, 'If a person walks 5 km in an hour and jogs 10 km in an hour, how long will it take them to cover 35 km if they walk for 2 hours and jog for the rest?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(48, 'A bucket can be filled by a tap in 5 minutes and emptied by a hole in 7 minutes. If the tap and the hole are both open, how long will it take to fill the bucket?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(49, 'If the ratio of the lengths of two sides of a rectangle is 3:4 and the perimeter is 56 cm, what are the dimensions of the rectangle?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(50, 'A sum of money triples in 8 years at simple interest. In how many years will it become nine times itself?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(51, 'A man spends 80% of his income and saves the rest. If he spends $200 more than he saves, what is his total income?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(52, 'A jar contains 50 coins consisting of quarters and dimes. If the total value of the coins is $9.50, how many quarters are in the jar?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(53, 'If the angles of a triangle are in the ratio 2:3:4, what are the angles?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(54, 'A sum of money becomes 7 times itself in 12 years at simple interest. What is the rate of interest per annum?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(55, '54. What is the remainder when 12345 is divided by 9?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(56, 'Find the LCM of 18 and 24.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(57, 'What is the square root of 169?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(58, 'What is 2^5', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(59, 'What is the sum of the first 10 prime numbers?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(60, ' What is 15% of 80', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(61, 'Convert 7/20to a decimal.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(62, 'What is the cube root of 64?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(63, 'Find the least common multiple of 6 and 8.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(64, 'What is 30% of 200?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(65, 'Find the least common multiple of 6 and 8.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(66, 'What is 3^3', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(67, '8−3×2', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(68, 'What is half of 144?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(69, 'Subtract 15 from 45.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(70, 'What is the product of 9 and 7?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(71, 'Divide 100 by 4.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(72, 'Add 17 and 26.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(73, 'What is the square of 11?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(74, 'Multiply 8 by 12.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(75, 'What is 4 cubed?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(76, 'Find the perimeter of a square with side length 7 cm.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(77, 'What is the remainder when 47 is divided by 6?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(78, 'If you multiply a number by 6 and get 42, what is the number?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(79, 'Subtract 19 from 60.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(80, 'What is 0.6×0.5', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(81, 'What is 3^3−2^3', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(82, 'Find the value of 4! ', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(83, 'Simplify 7×8−5×4.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(84, 'What is the sum of the first 5 prime numbers?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(85, 'What is the least common multiple of 15 and 20?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(86, 'What is the area of a triangle with base 10 cm and height 6 cm?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(87, 'Find the value of 3!×4.', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(88, 'What is 15^2 ', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(89, 'What is the greatest common divisor of 54 and 24?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(90, 'A car rental company charges $50 per day and an additional $0.20 per mile driven. If a customer rents a car for 3 days and drives 150 miles, what is the total cost?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(91, 'In a class of 40 students, 60% are girls. How many boys are in the class?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(92, 'A bicycle shop sells a bicycle for $200, making a 25% profit on the cost price. What is the cost price of the bicycle?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(93, 'If 5 workers can complete a task in 12 days, how many days will it take for 8 workers to complete the same task, assuming they work at the same rate?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(94, 'A farmer has 180 apples. He gives 1/3 of them to his friend and sells the rest. How many apples does he sell?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(95, 'If a person walks 5 km in 1 hour and jogs 10 km in 1 hour, how long will it take to cover a distance of 35 km if they walk for 2 hours and jog for the rest?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(96, 'Which of these numbers have only two factors, one and the number itself?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(97, 'Meena divides a number by 2 and then divides the result by 2 again. What is this equivalent to dividing the original number by?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(98, 'John has stamps from different countries. 1/3rd of them are India\'s stamps. If he has 36 Indian stamps, how many stamps does he have in total?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(99, '1024+1025+___=1025+1025+1025 What number will come in the blank to make the number sentence correct', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(100, 'Two angles of a triangle measure 35 degrees and 65 degrees. What is the measure of the third angle of the triangle?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(101, 'What is the sum of any THREE angles of a rectangle?', '2024-07-04 18:07:47', '2024-07-04 18:07:47'),
(102, 'answers', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(103, '2500', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(104, '68', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(105, '9', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(106, '9600', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(107, '2', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(108, '1683', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(109, '0.8636', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(110, '72', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(111, '6sqrt(2)', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(112, '5', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(113, '16', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(114, '15', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(115, '6', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(116, '5', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(117, '48', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(118, '45477', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(119, '70', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(120, '10', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(121, '120', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(122, '19', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(123, '3', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(124, '45511', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(125, '540', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(126, '12', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(127, '24', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(128, '24', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(129, '27', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(130, '150', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(131, '14', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(132, '50', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(133, '180', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(134, '45385', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(135, '50', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(136, '1005', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(137, '9600', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(138, '36', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(139, '5', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(140, '12', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(141, '10', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(142, '54', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(143, '75', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(144, '8', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(145, '10', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(146, '1', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(147, '80', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(148, '4.5', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(149, '17.5', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(150, '28', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(151, '24', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(152, '1000', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(153, '30', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(154, '40°, 60°, 80°.', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(155, '50', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(156, '6', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(157, '72', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(158, '13', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(159, '32', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(160, '129', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(161, '12', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(162, '0.35', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(163, '4', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(164, '24', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(165, '60', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(166, '24', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(167, '27', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(168, '2', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(169, '72', '2024-07-04 18:28:14', '2024-07-04 18:28:14'),
(170, '30', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(171, '63', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(172, '25', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(173, '43', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(174, '121', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(175, '96', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(176, '64', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(177, '28', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(178, '5', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(179, '7', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(180, '41', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(181, '0.3', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(182, '19', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(183, '24', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(184, '36', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(185, '28', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(186, '60', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(187, '30', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(188, '24', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(189, '225', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(190, '6', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(191, '180', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(192, '16', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(193, '160', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(194, '7.5', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(195, '120', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(196, '3.5', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(197, '37X1', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(198, '4', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(199, '108', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(200, '1026', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(201, '80', '2024-07-04 18:28:15', '2024-07-04 18:28:15'),
(202, '270', '2024-07-04 18:28:15', '2024-07-04 18:28:15');

-- --------------------------------------------------------

--
-- Table structure for table `rejectedparticipants`
--

CREATE TABLE `rejectedparticipants` (
  `id` int(11) NOT NULL,
  `username` varchar(255) NOT NULL,
  `schoolRegNo` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `firstName` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `dateOfBirth` date NOT NULL,
  `reason` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `schoolrepresentative`
--

CREATE TABLE `schoolrepresentative` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `RepresentativeName` varchar(100) NOT NULL,
  `password` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  `school_id` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `schoolrepresentative`
--

INSERT INTO `schoolrepresentative` (`id`, `username`, `RepresentativeName`, `password`, `email`, `school_id`) VALUES
(1, 'ryankizito', 'Kizito Ryan', 'password1', 'ryankizito@gmail.com', '230012139'),
(2, 'paulokema342', 'Okema Paul Mark', 'password2', 'paulokema342@gmail.com', '230016648'),
(3, 'georgelukaanya348', 'Lukaanya George', 'password3', 'georgelukaanya348@gmail.com', '2300700696');

-- --------------------------------------------------------

--
-- Table structure for table `schools`
--

CREATE TABLE `schools` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `name` varchar(255) NOT NULL,
  `district` varchar(255) NOT NULL,
  `reg_no` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `rep_name` varchar(255) NOT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `schools`
--

INSERT INTO `schools` (`id`, `name`, `district`, `reg_no`, `email`, `rep_name`, `created_at`, `updated_at`) VALUES
(1, 'makerere', 'kampala', '2300700696', 'georgelukaanya348@gmail.com', 'Lukaanya George', '2024-06-28 06:30:32', '2024-06-28 06:30:32'),
(2, 'kyambogo', 'wakiso', '230016648', 'paulokema342@gmail.com', 'Okema Paul', '2024-06-28 06:32:35', '2024-06-28 06:32:35'),
(3, 'kiu', 'Kansanga', '230012139', 'ryankizito@gmail.com', 'Kizito Ryan', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(255) NOT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `usertype` varchar(255) NOT NULL DEFAULT 'user',
  `email_verified_at` timestamp NULL DEFAULT NULL,
  `password` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `postal` varchar(255) DEFAULT NULL,
  `about` text DEFAULT NULL,
  `remember_token` varchar(100) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT NULL,
  `updated_at` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `firstname`, `lastname`, `email`, `usertype`, `email_verified_at`, `password`, `address`, `city`, `country`, `postal`, `about`, `remember_token`, `created_at`, `updated_at`) VALUES
(1, 'khemi', 'okema', 'paul', 'admin@argon.com', 'admin', NULL, '$2y$10$kFXMItbX2ohKIyIzgHPJbeHdj.BfI1AUXi8KYW3/VWxRVrCE5K5fa', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, 'zippy', NULL, NULL, 'user@gmail.com', 'user', NULL, '$2y$10$6kStbATqfK/9NoPs/9WcnuLDWxX.CObXBKUX/puhXovLiVpRSqxYO', NULL, NULL, NULL, NULL, NULL, NULL, '2024-06-28 08:35:27', '2024-06-28 08:35:27');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `acceptedparticipants`
--
ALTER TABLE `acceptedparticipants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `answers`
--
ALTER TABLE `answers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `challenges`
--
ALTER TABLE `challenges`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `failed_jobs_uuid_unique` (`uuid`);

--
-- Indexes for table `migrations`
--
ALTER TABLE `migrations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `password_resets`
--
ALTER TABLE `password_resets`
  ADD KEY `password_resets_email_index` (`email`);

--
-- Indexes for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `personal_access_tokens_token_unique` (`token`),
  ADD KEY `personal_access_tokens_tokenable_type_tokenable_id_index` (`tokenable_type`,`tokenable_id`);

--
-- Indexes for table `questions`
--
ALTER TABLE `questions`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rejectedparticipants`
--
ALTER TABLE `rejectedparticipants`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schoolrepresentative`
--
ALTER TABLE `schoolrepresentative`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `schools`
--
ALTER TABLE `schools`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `schools_reg_no_unique` (`reg_no`),
  ADD UNIQUE KEY `schools_email_unique` (`email`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `users_email_unique` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `acceptedparticipants`
--
ALTER TABLE `acceptedparticipants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `answers`
--
ALTER TABLE `answers`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=102;

--
-- AUTO_INCREMENT for table `challenges`
--
ALTER TABLE `challenges`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `data`
--
ALTER TABLE `data`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `failed_jobs`
--
ALTER TABLE `failed_jobs`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `migrations`
--
ALTER TABLE `migrations`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `personal_access_tokens`
--
ALTER TABLE `personal_access_tokens`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `questions`
--
ALTER TABLE `questions`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=203;

--
-- AUTO_INCREMENT for table `rejectedparticipants`
--
ALTER TABLE `rejectedparticipants`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `schoolrepresentative`
--
ALTER TABLE `schoolrepresentative`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `schools`
--
ALTER TABLE `schools`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
