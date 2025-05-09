import { useState } from 'react';
import { Routes, Route } from 'react-router-dom';
import Container from '@mui/material/Container';

import NavBar from './components/NavBar';
import Home from './pages/Home';
import Signup from './pages/Signup';
import Projects from './pages/Projects';
import Admin from './pages/Admin';
import Members from './components/Members';
import TestCRUD from './components/TestCRUD';

import { membersData, projectsData } from './data/SampleData';

export default function App() {
  const [members, setMembers] = useState(membersData);
  const [projects, setProjects] = useState(projectsData);
  const currentSemester = 'Spring 2025';

  const handleAddMember = (newMember) =>
    setMembers(prev => [...prev, newMember]);

  const handleAddProject = (newProject) =>
    setProjects(prev => [...prev, newProject]);

  const handleRoleChange = (id, role) =>
    setMembers(prev =>
      prev.map(m => (m.id === id ? { ...m, role } : m))
    );

  return (
    <>
      <NavBar />
      <Container sx={{ mt: 4 }}>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route
            path="/signup"
            element={<Signup onSignUp={handleAddMember} />}
          />
          <Route
            path="/projects"
            element={
              <Projects
                projects={projects}
                onSubmit={handleAddProject}
                currentSemester={currentSemester}
              />
            }
          />
          <Route
            path="/admin"
            element={
              <Admin
                members={members}
                onRoleChange={handleRoleChange}
              />
            }
          />
          <Route path="/members" element={<Members />} />
          <Route path="/test-crud" element={<TestCRUD />} />
        </Routes>
      </Container>
    </>
  );
}
