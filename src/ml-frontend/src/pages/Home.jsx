import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

export default function Home() {
  return (
    <Box sx={{ textAlign: 'center', mt: 8 }}>
      <Typography variant="h4" gutterBottom>
        Welcome to ML@SJSU Project Management System
      </Typography>
      <Typography variant="body1">
        Use the navigation above to sign up, view projects, or manage roles.
      </Typography>
    </Box>
  );
}
