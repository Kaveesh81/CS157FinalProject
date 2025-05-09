// src/components/TestConnection.jsx
import { useState, useEffect } from 'react';

export default function TestConnection() {
    const [status, setStatus] = useState('Loading...');
    const [error, setError] = useState(null);

    useEffect(() => {
        const testConnection = async () => {
            try {
                const response = await fetch('http://localhost:8081/mlservlet/api/test', {
                    method: 'GET',
                    credentials: 'include',
                });
                const data = await response.json();
                setStatus(data.message);
                if (data.status === 'error') {
                    setError(data.message);
                }
            } catch (err) {
                setError(err.message);
                setStatus('Failed to connect');
            }
        };

        testConnection();
    }, []);

    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Database Connection Test</h2>
            <div className="p-4 border rounded">
                <p className="mb-2">Status: {status}</p>
                {error && (
                    <p className="text-red-500">Error: {error}</p>
                )}
            </div>
        </div>
    );
}