import { useState, useEffect } from 'react';

export default function Members() {
    const [members, setMembers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchMembers = async () => {
            console.log('Starting to fetch members...');
            try {
                const url = 'http://localhost:8081/mlservlet/api/members';
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
                    console.log('Setting members data:', data.members);
                    setMembers(data.members);
                } else {
                    console.error('API returned error status:', data.message);
                    setError(data.message || 'Failed to fetch members');
                }
            } catch (err) {
                console.error('Error in fetchMembers:', err);
                setError(err.message || 'Failed to fetch members');
            } finally {
                console.log('Setting loading to false');
                setLoading(false);
            }
        };

        console.log('useEffect triggered, calling fetchMembers');
        fetchMembers();
    }, []);

    if (loading) {
        console.log('Rendering loading state');
        return (
            <div className="p-4">
                <h2 className="text-xl font-bold mb-4">Team Members</h2>
                <p>Loading members...</p>
            </div>
        );
    }

    if (error) {
        console.log('Rendering error state:', error);
        return (
            <div className="p-4">
                <h2 className="text-xl font-bold mb-4">Team Members</h2>
                <p className="text-red-500">Error: {error}</p>
            </div>
        );
    }

    console.log('Rendering members list:', members);
    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Team Members</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4">
                {members.map((member) => (
                    <div key={member.id} className="border rounded-lg p-4 shadow-sm">
                        <h3 className="text-lg font-semibold mb-2">{member.name}</h3>
                        <div className="text-sm text-gray-500">
                            <p>Email: <span className="font-medium">{member.email}</span></p>
                            <p>Role: <span className="font-medium">{member.role}</span></p>
                            <p>Join Date: <span className="font-medium">{new Date(member.joinDate).toLocaleDateString()}</span></p>
                        </div>
                    </div>
                ))}
            </div>
        </div>
    );
} 