import { useState } from 'react';
import {
  Box, TextField, Button, Typography, Alert,
  FormControl, InputLabel, Select, MenuItem
} from '@mui/material';

export default function ProjectForm({ onSubmit }) {
  const [data, setData] = useState({
    title: '', description: '', topic: '',
    semester: '', lead: '', github: '', spots: ''
  });
  const [message, setMessage] = useState(null);

  const handleChange = e => {
    const { name, value } = e.target;
    setData(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = e => {
    e.preventDefault();
    const { title, description, topic, semester, lead, github, spots } = data;
    if (!title||!description||!topic||!semester||!lead||!github||!spots) {
      setMessage({ type:'error', text:'Fill in all fields.' });
      return;
    }
    onSubmit({ ...data, spots: Number(spots) });
    setMessage({ type:'success', text:'Project created successfully!' });
    setData({ title:'', description:'', topic:'', semester:'', lead:'', github:'', spots:'' });
  };

  return (
    <Box sx={{ p:4, boxShadow:1, borderRadius:2 }}>
      <Typography variant="h5" gutterBottom>Create / Edit Project</Typography>
      <Box component="form" onSubmit={handleSubmit} noValidate>
        <TextField
          label="Project Title"
          name="title"
          value={data.title}
          onChange={handleChange}
          fullWidth margin="normal"
        />
        <TextField
          label="Description"
          name="description"
          value={data.description}
          onChange={handleChange}
          fullWidth multiline rows={3} margin="normal"
        />
        <TextField
          label="Topic/Category"
          name="topic"
          value={data.topic}
          onChange={handleChange}
          fullWidth margin="normal"
        />

        <FormControl fullWidth margin="normal">
          <InputLabel>Semester</InputLabel>
          <Select
            label="Semester"
            name="semester"
            value={data.semester}
            onChange={handleChange}
          >
            <MenuItem value="Spring 2025">Spring 2025</MenuItem>
            <MenuItem value="Fall 2024">Fall 2024</MenuItem>
          </Select>
        </FormControl>

        <TextField
          label="Project Lead"
          name="lead"
          value={data.lead}
          onChange={handleChange}
          fullWidth margin="normal"
        />
        <TextField
          label="GitHub URL"
          name="github"
          value={data.github}
          onChange={handleChange}
          fullWidth margin="normal"
        />
        <TextField
          label="Spots Available"
          name="spots"
          type="number"
          value={data.spots}
          onChange={handleChange}
          fullWidth margin="normal"
        />

        <Button
          type="submit"
          variant="contained"
          color="success"
          sx={{ mt:2 }}
        >
          Save Project
        </Button>
      </Box>

      {message && (
        <Alert severity={message.type} sx={{ mt:2 }}>
          {message.text}
        </Alert>
      )}
    </Box>
  );
}
