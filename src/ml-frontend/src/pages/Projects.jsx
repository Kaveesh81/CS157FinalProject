import Grid from '@mui/material/Grid';
import ProjectList from '../components/ProjectList';
import ProjectForm from '../components/ProjectForm';

export default function Projects({ projects, onSubmit, currentSemester }) {
  return (
    <Grid container spacing={4}>
      <Grid item xs={12}>
        <ProjectList
          projects={projects}
          currentSemester={currentSemester}
        />
      </Grid>
      <Grid item xs={12} md={6}>
        <ProjectForm onSubmit={onSubmit} />
      </Grid>
    </Grid>
  );
}
