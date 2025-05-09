-- Create projects table
CREATE TABLE IF NOT EXISTS projects (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    start_date DATE NOT NULL,
    end_date DATE,
    status VARCHAR(50) NOT NULL
);

-- Create members table
CREATE TABLE IF NOT EXISTS members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(100) NOT NULL,
    join_date DATE NOT NULL
);

-- Insert sample data for projects
INSERT INTO projects (name, description, start_date, end_date, status) VALUES
('Machine Learning Model Development', 'Developing a new ML model for image recognition', '2024-01-01', NULL, 'Active'),
('Data Analysis Pipeline', 'Creating an automated data analysis pipeline', '2024-02-15', '2024-04-15', 'Completed'),
('AI Chatbot Integration', 'Integrating AI chatbot into customer service', '2024-03-01', NULL, 'Active');

-- Insert sample data for members
INSERT INTO members (name, email, role, join_date) VALUES
('John Doe', 'john.doe@example.com', 'ML Engineer', '2024-01-01'),
('Jane Smith', 'jane.smith@example.com', 'Data Scientist', '2024-01-15'),
('Mike Johnson', 'mike.johnson@example.com', 'Project Manager', '2024-02-01'); 