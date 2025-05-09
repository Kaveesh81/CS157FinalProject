USE mlatsjsu_db;

-- Semesters
INSERT INTO semesters (term, year) VALUES
('Spring', 2024), ('Fall', 2023), ('Spring', 2023), ('Fall', 2022),
('Spring', 2022), ('Fall', 2021), ('Spring', 2021), ('Fall', 2020),
('Spring', 2020), ('Fall', 2019), ('Spring', 2019), ('Fall', 2018),
('Spring', 2018), ('Fall', 2017), ('Spring', 2017);

-- Members
INSERT INTO users (name, email, linkedin, grad_date, start_date, is_manager) VALUES
('Alice Zhang', 'alice@sjsu.edu', 'linkedin.com/alice', '2025-05-01', '2023-01-10', false),
('Bob Kumar', 'bob@sjsu.edu', 'linkedin.com/bob', '2024-12-01', '2022-08-20', false),
('Carlos Lee', 'carlos@sjsu.edu', 'linkedin.com/carlos', '2025-05-01', '2022-01-15', false),
('Diana Wu', 'diana@sjsu.edu', 'linkedin.com/diana', '2026-05-01', '2022-01-15', true),
('Eva Tran', 'eva@sjsu.edu', 'linkedin.com/eva', '2024-12-01', '2023-08-01', false),
('Frank Gomez', 'frank@sjsu.edu', 'linkedin.com/frank', '2025-12-01', '2023-01-10', false),
('Grace Patel', 'grace@sjsu.edu', 'linkedin.com/grace', '2026-05-01', '2022-08-20', false),
('Henry Lin', 'henry@sjsu.edu', 'linkedin.com/henry', '2025-12-01', '2022-01-15', false),
('Ivy Chen', 'ivy@sjsu.edu', 'linkedin.com/ivy', '2024-12-01', '2021-08-01', true),
('John Smith', 'john@sjsu.edu', 'linkedin.com/john', '2025-05-01', '2023-01-10', false),
('Karen Singh', 'karen@sjsu.edu', 'linkedin.com/karen', '2025-08-01', '2023-01-10', false),
('Leo Park', 'leo@sjsu.edu', 'linkedin.com/leo', '2026-05-01', '2023-08-01', false),
('Mia Lopez', 'mia@sjsu.edu', 'linkedin.com/mia', '2024-08-01', '2023-01-10', false),
('Nina Patel', 'nina@sjsu.edu', 'linkedin.com/nina', '2025-05-01', '2022-01-15', false),
('Oscar Ray', 'oscar@sjsu.edu', 'linkedin.com/oscar', '2026-12-01', '2024-01-10', false);

-- Projects
INSERT INTO projects (title, description, topic, semester_id, github_link, spots_available, is_approved) VALUES
('AI for Healthcare', 'A project applying ML to healthcare diagnostics.', 'Healthcare AI', 1, 'https://github.com/mlsjsu/ai-health', 5, true),
('NLP Chatbot', 'Natural Language chatbot for student Q&A.', 'NLP', 2, 'https://github.com/mlsjsu/nlp-chatbot', 4, true),
('CV for Wildlife', 'Image classification for endangered species.', 'Computer Vision', 3, 'https://github.com/mlsjsu/cv-wildlife', 6, true),
('Student Success Predictor', 'Predict student outcomes from academic data.', 'Predictive Analytics', 1, 'https://github.com/mlsjsu/student-success', 3, true),
('ML Pipeline Builder', 'Tools for building reusable ML pipelines.', 'MLOps', 2, 'https://github.com/mlsjsu/ml-pipeline', 5, true),
('Hackathon Helper', 'Idea manager and team matcher for ML hackathons.', 'Community Tools', 1, 'https://github.com/mlsjsu/hackathon-helper', 4, true),
('Smart Scheduler', 'Optimize event scheduling using ML.', 'Optimization', 4, 'https://github.com/mlsjsu/scheduler', 6, true),
('Research Paper Classifier', 'NLP model to classify ML papers by topic.', 'Research', 5, 'https://github.com/mlsjsu/paper-classifier', 5, true),
('Bias Detection in ML', 'Fairness analysis in datasets.', 'AI Ethics', 1, 'https://github.com/mlsjsu/bias-detection', 3, true),
('Project Showcase Portal', 'Web portal to display ML@SJSU projects.', 'Web Dev', 1, 'https://github.com/mlsjsu/showcase-portal', 5, true);

-- ProjectMemberships
INSERT INTO project_memberships (user_id, project_id, role, status) VALUES
(1, 1, 'Member', 'Approved'), (2, 1, 'Member', 'Approved'), (5, 1, 'Member', 'Approved'), 
(6, 2, 'Member', 'Approved'), (7, 2, 'Member', 'Approved'), (10, 2, 'Member', 'Approved'), 
(11, 3, 'Member', 'Approved'), (12, 3, 'Member', 'Approved'), (14, 3, 'Member', 'Approved'), 
(1, 4, 'Member', 'Approved'), (2, 5, 'Member', 'Approved'), (5, 5, 'Member', 'Approved'), 
(6, 5, 'Member', 'Approved'), (10, 6, 'Member', 'Approved'), (11, 6, 'Member', 'Approved'),
(3, 1, 'Lead', 'Approved'), (8, 2, 'Lead', 'Approved'), (13, 3, 'Lead', 'Approved'), 
(9, 4, 'Lead', 'Approved'), (3, 5, 'Lead', 'Approved'), (13, 6, 'Lead', 'Approved'), 
(3, 7, 'Lead', 'Approved'), (9, 8, 'Lead', 'Approved'), (8, 9, 'Lead', 'Approved'), 
(9, 10, 'Lead', 'Approved');
