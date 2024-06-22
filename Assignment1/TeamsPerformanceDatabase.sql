CREATE DATABASE IPL;

USE IPL;

CREATE TABLE IPL_Team_Performance (
    team_id INT AUTO_INCREMENT PRIMARY KEY,
    team_name VARCHAR(100) NOT NULL,
    wins INT NOT NULL,
    losses INT NOT NULL,
    run_rate DECIMAL(5, 2) NOT NULL
);

INSERT INTO IPL_Team_Performance (team_name, wins, losses, run_rate) VALUES
('Mumbai Indians', 5, 3, 8.20),
('Chennai Super Kings', 8, 2, 9.10),
('Royal Challengers Bangalore', 4, 4, 7.80),
('Rajasthan Royals', 4, 3, 2.20),
('Delhi Capitals', 3, 6, -1.10),
('Kolkata Knight Riders', 6, 3, 3.80),
('Kings XI Panjab', 7, 1, 5.20),
('Gujarat Titans', 6, 2, 4.10),
('Sunrisers Hyderabad', 1, 8, -3.10),
('Lucknow Super Giants', 4, 4, 7.80);