import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import Card from '@mui/material/Card';
import CardContent from '@mui/material/CardContent';
import Link from '@mui/material/Link';
import Grid from '@mui/material/Grid';

export default function ProjectList({ projects, currentSemester }) {
  const currentProjects = projects.filter(p => p.semester === currentSemester);
  const pastProjects = projects.filter(p => p.semester !== currentSemester);

  return (
    <Box>
      <Typography variant="h6" gutterBottom>
        Current Projects ({currentSemester})
      </Typography>
      <Grid container spacing={2}>
        {currentProjects.length > 0 ? (
          currentProjects.map((proj, idx) => (
            <Grid item xs={12} md={6} key={proj.title + idx}>
              <Card variant="outlined">
                <CardContent>
                  <Typography variant="h6">{proj.title}</Typography>
                  <Typography variant="body2" color="textSecondary" sx={{ mb: 1 }}>
                    Topic: {proj.topic} • Semester: {proj.semester}
                  </Typography>
                  <Typography variant="body2">Lead: {proj.lead}</Typography>
                  <Typography variant="body2">
                    GitHub: <Link href={proj.github} target="_blank">{proj.github}</Link>
                  </Typography>
                  <Typography variant="body2">
                    Spots available: {proj.spots}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          ))
        ) : (
          <Typography>No current projects available.</Typography>
        )}
      </Grid>

      <Typography variant="h6" gutterBottom sx={{ mt: 4 }}>
        Past Projects
      </Typography>
      <Grid container spacing={2}>
        {pastProjects.length > 0 ? (
          pastProjects.map((proj, idx) => (
            <Grid item xs={12} md={6} key={proj.title + idx}>
              <Card variant="outlined" sx={{ bgcolor: 'grey.100' }}>
                <CardContent>
                  <Typography variant="h6">{proj.title}</Typography>
                  <Typography variant="body2" color="textSecondary" sx={{ mb: 1 }}>
                    Topic: {proj.topic} • Semester: {proj.semester}
                  </Typography>
                  <Typography variant="body2">Lead: {proj.lead}</Typography>
                  <Typography variant="body2">
                    GitHub: <Link href={proj.github} target="_blank">{proj.github}</Link>
                  </Typography>
                  <Typography variant="body2">
                    Spots available: {proj.spots}
                  </Typography>
                </CardContent>
              </Card>
            </Grid>
          ))
        ) : (
          <Typography>No past projects.</Typography>
        )}
      </Grid>
    </Box>
  );
}
