CREATE DATABASE cinema;

CREATE TABLE users (
ID INT AUTO_INCREMENT PRIMARY KEY,
USERNAME VARCHAR(255) UNIQUE NOT NULL,
PASSWORD VARCHAR(255) NOT NULL,
ROLE VARCHAR(255) NOT NULL DEFAULT "USER"
)

INSERT INTO users (USERNAME, PASSWORD, ROLE) VALUES
("user", "user", "USER"),
("admin", "admin", "ADMIN");

CREATE TABLE cinema_halls (
ID INT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(255) UNIQUE NOT NULL,
SITES INT NOT NULL
);

INSERT INTO cinema_halls (NAME, SITES) VALUES
("ZŁOTA", 30),
("SREBNA", 50),
("VIP", 15);

CREATE TABLE products (
ID INT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(255) UNIQUE NOT NULL,
PRICE DECIMAL(10,2) NOT NULL
);

INSERT INTO products (NAME, PRICE) VALUES
("POPCORN MAŁY", 10),
("POPCORN ŚREDNI", 15),
("POPCORN DUŻY", 20);

CREATE TABLE movies (
ID INT AUTO_INCREMENT PRIMARY KEY,
NAME VARCHAR(255)  NOT NULL,
DIRECTOR VARCHAR(255)  NOT NULL,
CINEMA_HALL VARCHAR(255)  NOT NULL,
FREE_SITES INT NOT NULL,
START_TIME DATETIME,
END_TIME DATETIME,
TICKET_PRICE DECIMAL(10,2) NOT NULL
);

INSERT INTO movies (NAME, DIRECTOR, CINEMA_HALL, FREE_SITES, START_TIME, END_TIME, TICKET_PRICE) VALUES
("Harry Potter i Komnata Tajemnic", "Steve Kloves", "VIP", 10, "2023-01-28 18:00:00", "2023-01-28 21:00:00", 30);

CREATE TABLE tickets (
ID INT AUTO_INCREMENT PRIMARY KEY,
MOVIE_NAME VARCHAR(255)  NOT NULL,
CINEMA_HALL VARCHAR(255)  NOT NULL,
TICKET_NUMBER INT NOT NULL,
START_TIME DATETIME,
END_TIME DATETIME,
TOTAL_PRICE DECIMAL(10,2) NOT NULL,
USER_ID INT NOT NULL,
FOREIGN KEY (USER_ID) REFERENCES users(ID)
);