import React, { useState } from 'react';
import {
  Box,
  Button,
  TextField,
  Typography,
  Paper,
  Grid,
  Alert,
} from '@mui/material';

const TestCRUD = () => {
  const [projects, setProjects] = useState([]);
  const [members, setMembers] = useState([]);
  const [error, setError] = useState(null);
  const [success, setSuccess] = useState(null);

  // Test GET operations
  const testGetProjects = async () => {
    try {
      const response = await fetch('/api/projects', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
        }
      });
      
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
      
      const data = await response.json();
      if (data.status === 'success') {
        setProjects(data.projects);
        setSuccess('Successfully fetched projects');
      } else {
        setError(data.message || 'Failed to fetch projects');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  const testGetMembers = async () => {
    try {
      const response = await fetch('/api/members', {
        method: 'GET',
        headers: {
          'Accept': 'application/json',
        }
      });
      const data = await response.json();
      if (data.status === 'success') {
        setMembers(data.members);
        setSuccess('Successfully fetched members');
      } else {
        setError(data.message || 'Failed to fetch members');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  // Test POST operations
  const testCreateProject = async () => {
    try {
      const newProject = {
        title: 'Test Project',
        description: 'This is a test project',
        topic: 'Testing',
        spotsAvailable: 3,
        isApproved: false
      };

      const response = await fetch('/api/projects', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(newProject)
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      if (data.status === 'success') {
        setSuccess('Successfully created project');
        testGetProjects(); // Refresh the list
      } else {
        setError(data.message || 'Failed to create project');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  const testCreateMember = async () => {
    try {
      const newMember = {
        name: 'Test Member',
        email: 'test@example.com',
        role: 'Tester',
        joinDate: new Date().toISOString().split('T')[0]
      };

      const response = await fetch('/api/members', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(newMember)
      });
      const data = await response.json();
      if (data.status === 'success') {
        setSuccess('Successfully created member');
        testGetMembers(); // Refresh the list
      } else {
        setError(data.message || 'Failed to create member');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  // Test PUT operations
  const testUpdateProject = async (id) => {
    try {
      const updatedProject = {
        title: 'Updated Project',
        description: 'This is an updated project',
        topic: 'Updated Topic',
        spotsAvailable: 2,
        isApproved: true
      };

      const response = await fetch(`/api/projects/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(updatedProject)
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      if (data.status === 'success') {
        setSuccess('Successfully updated project');
        testGetProjects(); // Refresh the list
      } else {
        setError(data.message || 'Failed to update project');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  const testUpdateMember = async (id) => {
    try {
      const updatedMember = {
        name: 'Updated Member',
        email: 'updated@example.com',
        role: 'Updated Role',
        joinDate: new Date().toISOString().split('T')[0]
      };

      const response = await fetch(`/api/members/${id}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
          'Accept': 'application/json',
        },
        body: JSON.stringify(updatedMember)
      });
      const data = await response.json();
      if (data.status === 'success') {
        setSuccess('Successfully updated member');
        testGetMembers(); // Refresh the list
      } else {
        setError(data.message || 'Failed to update member');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  // Test DELETE operations
  const testDeleteProject = async (id) => {
    try {
      const response = await fetch(`/api/projects/${id}`, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
        }
      });

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const data = await response.json();
      if (data.status === 'success') {
        setSuccess('Successfully deleted project');
        testGetProjects(); // Refresh the list
      } else {
        setError(data.message || 'Failed to delete project');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  const testDeleteMember = async (id) => {
    try {
      const response = await fetch(`/api/members/${id}`, {
        method: 'DELETE',
        headers: {
          'Accept': 'application/json',
        }
      });
      const data = await response.json();
      if (data.status === 'success') {
        setSuccess('Successfully deleted member');
        testGetMembers(); // Refresh the list
      } else {
        setError(data.message || 'Failed to delete member');
      }
    } catch (err) {
      setError(err.message);
    }
  };

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        CRUD Operations Test
      </Typography>

      {error && (
        <Alert severity="error" sx={{ mb: 2 }}>
          {error}
        </Alert>
      )}

      {success && (
        <Alert severity="success" sx={{ mb: 2 }}>
          {success}
        </Alert>
      )}

      <Grid container spacing={3}>
        {/* Projects Section */}
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Projects
            </Typography>
            <Box sx={{ mb: 2 }}>
              <Button variant="contained" onClick={testGetProjects} sx={{ mr: 1 }}>
                Get Projects
              </Button>
              <Button variant="contained" onClick={testCreateProject} sx={{ mr: 1 }}>
                Create Project
              </Button>
            </Box>
            {projects.map((project) => (
              <Paper key={project.id} sx={{ p: 2, mb: 2 }}>
                <Typography variant="subtitle1">{project.title}</Typography>
                <Typography variant="body2">{project.description}</Typography>
                <Box sx={{ mt: 1 }}>
                  <Button
                    size="small"
                    onClick={() => testUpdateProject(project.id)}
                    sx={{ mr: 1 }}
                  >
                    Update
                  </Button>
                  <Button
                    size="small"
                    color="error"
                    onClick={() => testDeleteProject(project.id)}
                  >
                    Delete
                  </Button>
                </Box>
              </Paper>
            ))}
          </Paper>
        </Grid>

        {/* Members Section */}
        <Grid item xs={12} md={6}>
          <Paper sx={{ p: 2 }}>
            <Typography variant="h6" gutterBottom>
              Members
            </Typography>
            <Box sx={{ mb: 2 }}>
              <Button variant="contained" onClick={testGetMembers} sx={{ mr: 1 }}>
                Get Members
              </Button>
              <Button variant="contained" onClick={testCreateMember} sx={{ mr: 1 }}>
                Create Member
              </Button>
            </Box>
            {members.map((member) => (
              <Paper key={member.id} sx={{ p: 2, mb: 2 }}>
                <Typography variant="subtitle1">{member.name}</Typography>
                <Typography variant="body2">{member.email}</Typography>
                <Box sx={{ mt: 1 }}>
                  <Button
                    size="small"
                    onClick={() => testUpdateMember(member.id)}
                    sx={{ mr: 1 }}
                  >
                    Update
                  </Button>
                  <Button
                    size="small"
                    color="error"
                    onClick={() => testDeleteMember(member.id)}
                  >
                    Delete
                  </Button>
                </Box>
              </Paper>
            ))}
          </Paper>
        </Grid>
      </Grid>
    </Box>
  );
};

export default TestCRUD; 