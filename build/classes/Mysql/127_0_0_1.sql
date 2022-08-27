-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Aug 27, 2022 at 12:54 PM
-- Server version: 5.7.36
-- PHP Version: 7.4.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library_management_system`
--
CREATE DATABASE IF NOT EXISTS `library_management_system` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `library_management_system`;

-- --------------------------------------------------------

--
-- Table structure for table `book_details`
--

DROP TABLE IF EXISTS `book_details`;
CREATE TABLE IF NOT EXISTS `book_details` (
  `book_id` int(11) NOT NULL AUTO_INCREMENT,
  `book_name` varchar(255) DEFAULT NULL,
  `author` varchar(255) DEFAULT NULL,
  `quantity` int(100) DEFAULT NULL,
  PRIMARY KEY (`book_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `book_details`
--

INSERT INTO `book_details` (`book_id`, `book_name`, `author`, `quantity`) VALUES
(2, 'Java for everyone', 'Jack', 44),
(3, 'Learn Python', 'John', 15),
(4, 'PHP', 'Rose', 15),
(5, 'HTML', 'Bruce', 49),
(6, 'CSS', 'Daniel', 0),
(7, 'Golang programming', 'Jack', 45),
(8, 'Css Tricks ', 'Jack', 50);

-- --------------------------------------------------------

--
-- Table structure for table `issue_book_details`
--

DROP TABLE IF EXISTS `issue_book_details`;
CREATE TABLE IF NOT EXISTS `issue_book_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `book_id` int(11) NOT NULL,
  `book_name` varchar(255) NOT NULL,
  `student_id` int(11) NOT NULL,
  `student_name` varchar(255) NOT NULL,
  `issue_date` date NOT NULL,
  `due_date` date NOT NULL,
  `status` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `issue_book_details`
--

INSERT INTO `issue_book_details` (`id`, `book_id`, `book_name`, `student_id`, `student_name`, `issue_date`, `due_date`, `status`) VALUES
(11, 3, 'Learn Python', 1, 'Naveen', '2022-08-24', '2022-09-24', 'pending'),
(9, 2, 'Java for everyone', 2, 'Praveen', '2022-08-23', '2022-09-23', 'returned'),
(10, 6, 'CSS', 3, 'Jack', '2022-08-23', '2022-09-23', 'pending'),
(12, 3, 'Learn Python', 4, 'Rose', '2022-09-24', '2022-09-24', 'pending'),
(13, 2, 'Java for everyone', 4, 'Rose', '2022-07-01', '2022-08-01', 'pending'),
(14, 3, 'Learn Python', 2, 'Praveen', '2022-08-25', '2022-09-25', 'pending'),
(15, 4, 'PHP', 2, 'Praveen', '2022-08-25', '2022-09-25', 'pending'),
(16, 4, 'PHP', 3, 'Jack', '2022-08-25', '2022-09-25', 'pending'),
(17, 4, 'PHP', 4, 'Rose', '2022-08-25', '2022-09-25', 'pending'),
(18, 2, 'Java for everyone', 5, 'Mary', '2022-07-20', '2022-08-20', 'pending'),
(19, 4, 'PHP', 6, 'Iron Man', '2022-07-20', '2022-08-20', 'pending'),
(20, 2, 'Java for everyone', 6, 'Iron Man', '2022-07-20', '2022-08-20', 'pending'),
(21, 3, 'Learn Python', 6, 'Iron Man', '2022-07-20', '2022-08-20', 'returned'),
(22, 5, 'HTML', 6, 'Iron Man', '2022-07-20', '2022-08-20', 'pending'),
(23, 3, 'Learn Python', 7, 'Captain America', '2022-08-27', '2022-09-27', 'returned'),
(24, 4, 'PHP', 7, 'Captain America', '2022-08-27', '2022-09-27', 'pending');

-- --------------------------------------------------------

--
-- Table structure for table `student_details`
--

DROP TABLE IF EXISTS `student_details`;
CREATE TABLE IF NOT EXISTS `student_details` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `student_name` varchar(255) NOT NULL,
  `course` varchar(255) NOT NULL,
  `branch` varchar(255) NOT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student_details`
--

INSERT INTO `student_details` (`student_id`, `student_name`, `course`, `branch`) VALUES
(1, 'Naveen', 'M.Sc', 'Computer Science'),
(2, 'Praveen', 'M.Sc', 'Chemistry'),
(3, 'Jack', 'M.Sc', 'Physics'),
(4, 'Rose', 'PHD', 'Computer Science'),
(5, 'Mary', 'PHD', 'Computer Science'),
(6, 'Iron Man', 'M.Sc', 'Chemistry'),
(7, 'Captain America', 'PHD', 'Computer Science');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `usertype` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `username`, `password`, `email`, `contact`, `usertype`) VALUES
(12, '18MIS0395', '1234', 'user395@gmail.com', '9xxxxxxxx5', 'Admin'),
(13, 'guest', 'guest', 'guest@gmail.com', '9xxxxxxxx7', 'Guest'),
(14, 'student1', '1234', 'student1@gmail.com', '9xxxxxxxx6', 'Student');
--
-- Database: `school_management_system`
--
CREATE DATABASE IF NOT EXISTS `school_management_system` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `school_management_system`;

-- --------------------------------------------------------

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
CREATE TABLE IF NOT EXISTS `class` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `classname` varchar(255) NOT NULL,
  `section` varchar(255) NOT NULL,
  `student_strength` int(22) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `class`
--

INSERT INTO `class` (`id`, `classname`, `section`, `student_strength`) VALUES
(1, '1', 'A', 55),
(2, '2', 'A', 60);

-- --------------------------------------------------------

--
-- Table structure for table `exam`
--

DROP TABLE IF EXISTS `exam`;
CREATE TABLE IF NOT EXISTS `exam` (
  `examid` int(22) NOT NULL AUTO_INCREMENT,
  `examname` varchar(255) NOT NULL,
  `examterm` varchar(255) NOT NULL,
  `examdate` varchar(255) NOT NULL,
  `examclass` varchar(255) NOT NULL,
  `examsection` varchar(255) NOT NULL,
  `examsubject` varchar(255) NOT NULL,
  PRIMARY KEY (`examid`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `exam`
--

INSERT INTO `exam` (`examid`, `examname`, `examterm`, `examdate`, `examclass`, `examsection`, `examsubject`) VALUES
(1, 'Public Exam', 'First Term', '2022-08-02', '1', 'A', 'Tamil'),
(2, 'Public Exam', 'First Term', '2022-08-03', '1', 'A', 'English'),
(3, 'Public Exam', 'First Term', '2022-08-04', '1', 'A', 'Maths'),
(4, 'Public Exam', 'First Term', '2022-08-08', '2', 'A', 'Tamil'),
(5, 'Public Exam', 'First Term', '2022-08-09', '2', 'A', 'English'),
(6, 'Public Exam', 'First Term', '2022-08-10', '2', 'A', 'Maths');

-- --------------------------------------------------------

--
-- Table structure for table `mark`
--

DROP TABLE IF EXISTS `mark`;
CREATE TABLE IF NOT EXISTS `mark` (
  `studentid` int(22) NOT NULL,
  `studentname` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL,
  `class` varchar(255) NOT NULL,
  `marks` varchar(255) NOT NULL,
  `term` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mark`
--

INSERT INTO `mark` (`studentid`, `studentname`, `subject`, `class`, `marks`, `term`) VALUES
(1, 'John', 'Tamil', '1', '458', 'First Term');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `studentid` int(22) NOT NULL AUTO_INCREMENT,
  `studentname` varchar(255) NOT NULL,
  `parentname` varchar(255) NOT NULL,
  `dateofbirth` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `studentmobileno` varchar(255) NOT NULL,
  `studentaddress` varchar(255) NOT NULL,
  `studentclass` varchar(255) NOT NULL,
  `studentsection` varchar(255) NOT NULL,
  PRIMARY KEY (`studentid`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`studentid`, `studentname`, `parentname`, `dateofbirth`, `gender`, `studentmobileno`, `studentaddress`, `studentclass`, `studentsection`) VALUES
(1, 'John', 'Jack', '2001-08-06', 'Male', '9653214780', 'Chennai, India', '1', 'A');

-- --------------------------------------------------------

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
CREATE TABLE IF NOT EXISTS `subject` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `subjectcode` varchar(22) NOT NULL,
  `subjectname` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subject`
--

INSERT INTO `subject` (`id`, `subjectcode`, `subjectname`) VALUES
(1, 'TAM', 'Tamil'),
(2, 'ENG', 'English'),
(3, 'MAT', 'Maths');

-- --------------------------------------------------------

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
CREATE TABLE IF NOT EXISTS `teacher` (
  `id` int(22) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `qualification` varchar(255) NOT NULL,
  `salary` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `qualification`, `salary`, `phone`, `email`, `address`) VALUES
(1, 'Rose', 'M.Tech Computer Science', '500000', '9784563210', 'rose@gmail.com', 'Chennai, India');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phone` varchar(25) NOT NULL,
  `address` varchar(255) NOT NULL,
  `uname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `utype` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `name`, `phone`, `address`, `uname`, `password`, `utype`) VALUES
(1, 'Naveenkumar J', '9080965525', 'Vellore, India', '18MIS0395', '1234', 'Admin'),
(2, 'Praveen', '9846456321', 'Vellore, India', '18MIS001', '1234', 'Student'),
(3, 'Jack', '9632145874', 'Vellore, India', '101', '1234', 'Teacher'),
(4, 'Guest', '9874569854', 'Bangalore, India', 'guest', 'guest', 'Guest');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
