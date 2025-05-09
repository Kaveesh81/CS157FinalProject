import React, { useState, useEffect } from "react";
import {
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Paper,
  Typography,
  CircularProgress,
  Alert,
} from "@mui/material";

const ProjectList = () => {
  const [projects, setProjects] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchProjects = async () => {
      console.log('Starting to fetch projects...');
      try {
        const url = 'http://localhost:8081/mlservlet/api/projects';
        console.log('Fetching from URL:', url);
        
        const response = await fetch(url, {
          method: 'GET',
          credentials: 'include',
          headers: {
            'Accept': 'application/json',
          }
        });
        
        console.log('Response received:', {
          status: response.status,
          statusText: response.statusText,
          headers: Object.fromEntries(response.headers.entries())
        });

        const data = await response.json();
        console.log('Parsed JSON data:', data);

        if (data.status === 'success') {
          console.log('Setting projects data:', data.projects);
          setProjects(data.projects);
        } else {
          console.error('API returned error status:', data.message);
          setError(data.message || 'Failed to fetch projects');
        }
      } catch (err) {
        console.error('Error in fetchProjects:', err);
        setError(err.message || 'Failed to fetch projects');
      } finally {
        console.log('Setting loading to false');
        setLoading(false);
      }
    };

    console.log('useEffect triggered, calling fetchProjects');
    fetchProjects();
  }, []);

  if (loading) {
    return (
      <div style={{ display: "flex", justifyContent: "center", padding: "2rem" }}>
        <CircularProgress />
      </div>
    );
  }

  if (error) {
    return (
      <Alert severity="error" sx={{ margin: "1rem" }}>
        {error}
      </Alert>
    );
  }

  return (
    <div style={{ padding: "1rem" }}>
      <Typography variant="h4" gutterBottom>
        Projects
      </Typography>
      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>ID</TableCell>
              <TableCell>Title</TableCell>
              <TableCell>Description</TableCell>
              <TableCell>Topic</TableCell>
              <TableCell>Spots Available</TableCell>
              <TableCell>Status</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {projects.map((project) => (
              <TableRow key={project.id}>
                <TableCell>{project.id}</TableCell>
                <TableCell>{project.title}</TableCell>
                <TableCell>{project.description}</TableCell>
                <TableCell>{project.topic}</TableCell>
                <TableCell>{project.spotsAvailable}</TableCell>
                <TableCell>
                  <span style={{ 
                    color: project.isApproved ? 'green' : 'orange',
                    fontWeight: 'bold'
                  }}>
                    {project.isApproved ? 'Approved' : 'Pending'}
                  </span>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </div>
  );
};

export default ProjectList;
