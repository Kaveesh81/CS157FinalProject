-- Drop database if it already exists
DROP DATABASE IF EXISTS mlsjsu_db;

-- Create and use new database
CREATE DATABASE mlsjsu_db;
USE mlsjsu_db;

-- Table: Semesters
CREATE TABLE Semesters (
    SemesterID INT PRIMARY KEY AUTO_INCREMENT,
    Term VARCHAR(10) NOT NULL,       -- e.g., Fall, Spring
    Year INT NOT NULL
);

-- Table: Members
CREATE TABLE Members (
    MemberID INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    LinkedIn VARCHAR(255) NOT NULL,
    GradDate DATE NOT NULL,
    Role ENUM('General', 'Project', 'Lead', 'Manager') NOT NULL,
    StartDate DATE NOT NULL
);

-- Table: Projects
CREATE TABLE Projects (
    ProjectID INT PRIMARY KEY AUTO_INCREMENT,
    Title VARCHAR(150) NOT NULL,
    Description TEXT NOT NULL,
    Topic VARCHAR(100) NOT NULL,
    SemesterID INT NOT NULL,
    ProjectLeadID INT NOT NULL,
    GitHubLink VARCHAR(255) NOT NULL,
    SpotsAvailable INT NOT NULL CHECK (SpotsAvailable >= 0),
    
    FOREIGN KEY (SemesterID) REFERENCES Semesters(SemesterID) ON DELETE CASCADE,
    FOREIGN KEY (ProjectLeadID) REFERENCES Members(MemberID) ON DELETE CASCADE
);

-- Table: ProjectMemberships (Many-to-Many)
CREATE TABLE ProjectMemberships (
    MemberID INT NOT NULL,
    ProjectID INT NOT NULL,
    Role ENUM('Member', 'Lead', 'Manager') NOT NULL,
    PRIMARY KEY (MemberID, ProjectID),

    FOREIGN KEY (MemberID) REFERENCES Members(MemberID) ON DELETE CASCADE,
    FOREIGN KEY (ProjectID) REFERENCES Projects(ProjectID) ON DELETE CASCADE
);
