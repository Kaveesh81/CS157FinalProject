-- Drop database if it already exists
-- Required to make create_schema.sql idempotent
DROP DATABASE IF EXISTS mlatsjsu_db;

-- Create and use new database
CREATE DATABASE mlatsjsu_db;
USE mlatsjsu_db;

-- Table: Semesters
CREATE TABLE semesters (
    semester_id     INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    term            ENUM('Spring', 'Summer', 'Fall', 'Winter') NOT NULL,
    year            YEAR NOT NULL,
    CONSTRAINT unique_semester UNIQUE (term, year)
);

-- Table: Users
CREATE TABLE users (
    user_id         INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL,
    email           VARCHAR(100) NOT NULL UNIQUE,
    linkedin        VARCHAR(255) NOT NULL UNIQUE,
    grad_date       DATE NOT NULL,
    start_date      DATE NOT NULL,
    is_manager      BOOLEAN NOT NULL DEFAULT FALSE
);

-- Table: Projects
CREATE TABLE projects (
    project_id      INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(150) NOT NULL,
    description     TEXT NOT NULL,
    topic           VARCHAR(100) NOT NULL,
    semester_id     INT NOT NULL,
    github_link     VARCHAR(255) NOT NULL,
    spots_available INT NOT NULL CHECK (spots_available >= 0),
    is_approved     BOOLEAN NOT NULL DEFAULT FALSE,
    
    FOREIGN KEY (semester_id) REFERENCES semesters(semester_id) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- Table: project-membership (Many-to-Many)
CREATE TABLE project_memberships (
    user_id         INT NOT NULL,
    project_id      INT NOT NULL,
    role            ENUM('Member', 'Lead') NOT NULL DEFAULT 'Member',
    status          ENUM('Pending', 'Approved', 'Rejected') NOT NULL DEFAULT 'Pending',
    
    PRIMARY KEY (user_id, project_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (project_id) REFERENCES projects(project_id) ON DELETE RESTRICT ON UPDATE CASCADE
);
