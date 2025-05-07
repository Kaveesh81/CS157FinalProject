import Box from '@mui/material/Box';
import MemberSignup from '../components/MemberSignup';

export default function Signup({ onSignUp }) {
  return (
    <Box sx={{ display: 'flex', justifyContent: 'center', mt: 4 }}>
      <MemberSignup onSignUp={onSignUp} />
    </Box>
  );
}
