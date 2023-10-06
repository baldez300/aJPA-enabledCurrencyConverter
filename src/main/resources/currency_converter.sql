
-- Statement to drop the previous version of the database, if it exists
DROP DATABASE IF EXISTS currency_converter;

-- Statement to create the database
CREATE DATABASE currency_converter;

-- Use the newly created database
USE currency_converter;

-- Statement to create a table for storing Currency objects
CREATE TABLE currencies (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            abbreviation VARCHAR(3) NOT NULL,
                            currency_name VARCHAR(255) NOT NULL,
                            conversion_rate DECIMAL(10, 6) NOT NULL
);

-- Statements for populating the table with data (example data)
INSERT INTO currencies (abbreviation, currency_name, conversion_rate) VALUES
                                                                 ('USD', 'United States Dollar', 1.000000),
                                                                 ('EUR', 'Euro', 0.942540),
                                                                 ('GBP', 'British Pound Sterling', 0.818047),
                                                                 ('JPY', 'Japanese Yen', 108.450000),
                                                                 -- More currencies for at least eight currencies
                                                                 ('AUD', 'Australian Dollar', 1.403230),
                                                                 ('CAD', 'Canadian Dollar', 1.362080),
                                                                 ('CHF', 'Swiss Franc', 0.974388),
                                                                 ('CNY', 'Chinese Yuan', 6.524000);

-- Statement to drop the user account appuser, if it exists
DROP USER IF EXISTS 'appuser'@'localhost';

-- Statement to create the user account appuser
CREATE USER 'appuser'@'localhost' IDENTIFIED BY 'password'; -- Replace 'password' with a strong password

-- Statements for granting the privileges to the user account appuser
GRANT SELECT, INSERT, UPDATE, DELETE ON currency_converter.* TO 'appuser'@'localhost';
