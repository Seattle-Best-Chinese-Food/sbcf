-- create database test_db 
CREATE DATABASE IF NOT EXISTS `test_db`;

USE `test_db`;
-- create table sbcf_item
CREATE TABLE IF NOT EXISTS `sbcf_item` (
    `id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NOT NULL,
    `published` BOOLEAN NOT NULL
);

-- insert data into sbcf_item
INSERT INTO `sbcf_item` (`name`, `description`, `published`) VALUES 
('Pizza', 'Pizza description', true), 
('Burger', 'Burger description', false), 
('Salad', 'Salad description', true), 
('Soda', 'Soda description', true), 
('Ice Cream', 'Ice Cream description', false);