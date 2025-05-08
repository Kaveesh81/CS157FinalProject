// src/components/TestConnection.jsx
import { useState } from 'react';
import api from '../services/api';
import { Button, Typography, Box, Alert } from '@mui/material';

const TestConnection = () => {
    const [message, setMessage] = useState('');
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    const testConnection = async () => {
        setLoading(true);
        setError(null);
        try {
            const response = await api.get('/test/connection');
            setMessage(response.data);
        } catch (err) {
            console.error('Connection test error:', err);
            setError(err.response?.data || err.message || 'Failed to connect to backend');
        } finally {
            setLoading(false);
        }
    };

    return (
        <Box sx={{ p: 2 }}>
            <Typography variant="h5" gutterBottom>
                Connection Test
            </Typography>
            <Button 
                variant="contained" 
                onClick={testConnection}
                disabled={loading}
                sx={{ mb: 2 }}
            >
                {loading ? 'Testing...' : 'Test Connection'}
            </Button>
            
            {message && (
                <Alert severity="success" sx={{ mt: 2 }}>
                    {message}
                </Alert>
            )}
            
            {error && (
                <Alert severity="error" sx={{ mt: 2 }}>
                    Error: {error}
                </Alert>
            )}
        </Box>
    );
};

export default TestConnection;