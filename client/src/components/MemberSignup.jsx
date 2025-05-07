import { useState } from 'react';
import Box from '@mui/material/Box';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';

export default function MemberSignup({ onSignUp }) {
  const [name, setName] = useState('');
  const [email, setEmail] = useState('');
  const [studentId, setStudentId] = useState('');
  const [message, setMessage] = useState('');

  const validateEligibility = () => {
    const emailValid = email.toLowerCase().endsWith('@sjsu.edu');
    const idValid = /^\d{9}$/.test(studentId);
    if (!name || !email || !studentId) return 'Please fill out all fields.';
    if (!emailValid) return 'Email must be an @sjsu.edu address.';
    if (!idValid) return 'Student ID must be a 9-digit number.';
    return 'eligible';
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const result = validateEligibility();
    if (result === 'eligible') {
      onSignUp({ id: studentId, name, email, role: 'General' });
      setMessage('✅ Sign-up successful! Eligible member added.');
      setName(''); setEmail(''); setStudentId('');
    } else {
      setMessage(`❌ Not eligible: ${result}`);
    }
  };

  return (
    <Box sx={{ maxWidth: 400, p: 3, boxShadow: 1, borderRadius: 2 }}>
      <Typography variant="h5" gutterBottom>
        Member Signup
      </Typography>
      <Box component="form" noValidate onSubmit={handleSubmit}>
        <TextField
          label="Full Name"
          fullWidth
          value={name}
          onChange={e => setName(e.target.value)}
          sx={{ mb: 2 }}
        />
        <TextField
          label="SJSU Email"
          fullWidth
          value={email}
          onChange={e => setEmail(e.target.value)}
          sx={{ mb: 2 }}
        />
        <TextField
          label="Student ID"
          fullWidth
          value={studentId}
          onChange={e => setStudentId(e.target.value)}
          helperText="9-digit SJSU ID"
          sx={{ mb: 2 }}
        />
        <Button type="submit" variant="contained" fullWidth>
          Sign Up
        </Button>
      </Box>
      {message && (
        <Typography variant="body2" sx={{ mt: 2 }}>
          {message}
        </Typography>
      )}
    </Box>
  );
}
