-- Create Database
CREATE DATABASE library_db;
USE library_db;

-- Books Table
CREATE TABLE books (
    book_id INT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    status VARCHAR(20) DEFAULT 'Available'
);

-- Students Table
CREATE TABLE students (
    roll_no INT PRIMARY KEY,
    name VARCHAR(100),
    issued_book_id INT DEFAULT NULL,
    FOREIGN KEY (issued_book_id) REFERENCES books(book_id)
);

-- Transactions Table
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    roll_no INT,
    book_id INT,
    issue_date DATE,
    return_date DATE,
    fine INT DEFAULT 0,
    FOREIGN KEY (roll_no) REFERENCES students(roll_no),
    FOREIGN KEY (book_id) REFERENCES books(book_id)
);

-- Sample Data for Books
INSERT INTO books (book_id, title, author, status) VALUES
(1, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Available'),
(2, 'To Kill a Mockingbird', 'Harper Lee', 'Available'),
(3, '1984', 'George Orwell', 'Issued'),
(4, 'The Alchemist', 'Paulo Coelho', 'Available'),
(5, 'Clean Code', 'Robert C. Martin', 'Issued');

-- Sample Data for Students
INSERT INTO students (roll_no, name, issued_book_id) VALUES
(101, 'Rahul Sharma', NULL),
(102, 'Sneha Reddy', 3),
(103, 'Arjun Patel', NULL),
(104, 'Meera Rao', 5),
(105, 'Vikram Singh', NULL);

-- Sample Data for Transactions
INSERT INTO transactions (roll_no, book_id, issue_date, return_date, fine) VALUES
(102, 3, '2025-01-10', NULL, 0),
(104, 5, '2025-01-05', '2025-01-15', 0),
(101, 1, '2025-01-02', '2025-01-09', 0),
(103, 2, '2025-01-08', NULL, 0),
(105, 4, '2025-01-12', '2025-01-20', 10);
