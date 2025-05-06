USE mlsjsu_db;

-- Semesters
INSERT INTO semesters (term, year) VALUES
('Spring', 2024), ('Fall', 2023), ('Spring', 2023), ('Fall', 2022),
('Spring', 2022), ('Fall', 2021), ('Spring', 2021), ('Fall', 2020),
('Spring', 2020), ('Fall', 2019), ('Spring', 2019), ('Fall', 2018),
('Spring', 2018), ('Fall', 2017), ('Spring', 2017);

-- Members
INSERT INTO users (name, email, linkedin, grad_date, role, start_date) VALUES
('Alice Zhang', 'alice@sjsu.edu', 'linkedin.com/alice', '2025-05-01', 'General', '2023-01-10'),
('Bob Kumar', 'bob@sjsu.edu', 'linkedin.com/bob', '2024-12-01', 'Project', '2022-08-20'),
('Carlos Lee', 'carlos@sjsu.edu', 'linkedin.com/carlos', '2025-05-01', 'Lead', '2022-01-15'),
('Diana Wu', 'diana@sjsu.edu', 'linkedin.com/diana', '2026-05-01', 'Manager', '2022-01-15'),
('Eva Tran', 'eva@sjsu.edu', 'linkedin.com/eva', '2024-12-01', 'Project', '2023-08-01'),
('Frank Gomez', 'frank@sjsu.edu', 'linkedin.com/frank', '2025-12-01', 'General', '2023-01-10'),
('Grace Patel', 'grace@sjsu.edu', 'linkedin.com/grace', '2026-05-01', 'General', '2022-08-20'),
('Henry Lin', 'henry@sjsu.edu', 'linkedin.com/henry', '2025-12-01', 'Lead', '2022-01-15'),
('Ivy Chen', 'ivy@sjsu.edu', 'linkedin.com/ivy', '2024-12-01', 'Manager', '2021-08-01'),
('John Smith', 'john@sjsu.edu', 'linkedin.com/john', '2025-05-01', 'Project', '2023-01-10'),
('Karen Singh', 'karen@sjsu.edu', 'linkedin.com/karen', '2025-08-01', 'General', '2023-01-10'),
('Leo Park', 'leo@sjsu.edu', 'linkedin.com/leo', '2026-05-01', 'Project', '2023-08-01'),
('Mia Lopez', 'mia@sjsu.edu', 'linkedin.com/mia', '2024-08-01', 'Project', '2023-01-10'),
('Nina Patel', 'nina@sjsu.edu', 'linkedin.com/nina', '2025-05-01', 'Lead', '2022-01-15'),
('Oscar Ray', 'oscar@sjsu.edu', 'linkedin.com/oscar', '2026-12-01', 'General', '2024-01-10');

-- Projects
INSERT INTO projects (title, description, topic, semester_id, project_lead_id, github_link, spots_available) VALUES
('AI for Healthcare', 'A project applying ML to healthcare diagnostics.', 'Healthcare AI', 1, 3, 'https://github.com/mlsjsu/ai-health', 5),
('NLP Chatbot', 'Natural Language chatbot for student Q&A.', 'NLP', 2, 8, 'https://github.com/mlsjsu/nlp-chatbot', 4),
('CV for Wildlife', 'Image classification for endangered species.', 'Computer Vision', 3, 13, 'https://github.com/mlsjsu/cv-wildlife', 6),
('Student Success Predictor', 'Predict student outcomes from academic data.', 'Predictive Analytics', 1, 9, 'https://github.com/mlsjsu/student-success', 3),
('ML Pipeline Builder', 'Tools for building reusable ML pipelines.', 'MLOps', 2, 3, 'https://github.com/mlsjsu/ml-pipeline', 5),
('Hackathon Helper', 'Idea manager and team matcher for ML hackathons.', 'Community Tools', 1, 13, 'https://github.com/mlsjsu/hackathon-helper', 4),
('Smart Scheduler', 'Optimize event scheduling using ML.', 'Optimization', 4, 3, 'https://github.com/mlsjsu/scheduler', 6),
('Research Paper Classifier', 'NLP model to classify ML papers by topic.', 'Research', 5, 9, 'https://github.com/mlsjsu/paper-classifier', 5),
('Bias Detection in ML', 'Fairness analysis in datasets.', 'AI Ethics', 1, 8, 'https://github.com/mlsjsu/bias-detection', 3),
('Project Showcase Portal', 'Web portal to display ML@SJSU projects.', 'Web Dev', 1, 9, 'https://github.com/mlsjsu/showcase-portal', 5);

-- ProjectMemberships
INSERT INTO project_memberships (user_id, project_id, role) VALUES
(1, 1, 'Member'), (2, 1, 'Member'), (5, 1, 'Member'), (6, 2, 'Member'), (7, 2, 'Member'),
(10, 2, 'Member'), (11, 3, 'Member'), (12, 3, 'Member'), (14, 3, 'Member'), (1, 4, 'Member'),
(2, 5, 'Member'), (5, 5, 'Member'), (6, 5, 'Member'), (10, 6, 'Member'), (11, 6, 'Member'),
(3, 1, 'Lead'), (8, 2, 'Lead'), (13, 3, 'Lead'), (9, 4, 'Lead'), (3, 5, 'Lead'),
(13, 6, 'Lead'), (3, 7, 'Lead'), (9, 8, 'Lead'), (8, 9, 'Lead'), (9, 10, 'Lead');
