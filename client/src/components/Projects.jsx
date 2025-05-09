import { useState, useEffect } from 'react';

export default function Projects() {
    const [projects, setProjects] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [rawJson, setRawJson] = useState(null);

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
                setRawJson(JSON.stringify(data, null, 2));

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

    console.log('Current state:', { projects, loading, error, rawJson });

    if (loading) {
        console.log('Rendering loading state');
        return (
            <div className="p-4">
                <h2 className="text-xl font-bold mb-4">Projects</h2>
                <p>Loading projects...</p>
            </div>
        );
    }

    if (error) {
        console.log('Rendering error state:', error);
        return (
            <div className="p-4">
                <h2 className="text-xl font-bold mb-4">Projects</h2>
                <p className="text-red-500">Error: {error}</p>
            </div>
        );
    }

    console.log('Rendering projects list:', projects);
    return (
        <div className="p-4">
            <h2 className="text-xl font-bold mb-4">Projects</h2>
            <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-4 mb-8">
                {projects.map((project) => (
                    <div key={project.id} className="border rounded-lg p-4 shadow-sm">
                        <h3 className="text-lg font-semibold mb-2">{project.title}</h3>
                        <p className="text-gray-600 mb-2">{project.description}</p>
                        <div className="text-sm text-gray-500">
                            <p>Topic: <span className="font-medium">{project.topic}</span></p>
                            <p>Spots Available: <span className="font-medium">{project.spotsAvailable}</span></p>
                            <p>Status: <span className={`font-medium ${
                                project.isApproved ? 'text-green-600' : 'text-yellow-600'
                            }`}>{project.isApproved ? 'Approved' : 'Pending'}</span></p>
                            {project.githubLink && (
                                <a 
                                    href={project.githubLink} 
                                    target="_blank" 
                                    rel="noopener noreferrer"
                                    className="text-blue-600 hover:text-blue-800 mt-2 inline-block"
                                >
                                    View on GitHub →
                                </a>
                            )}
                        </div>
                    </div>
                ))}
            </div>
            
            <div className="mt-8">
                <h3 className="text-lg font-semibold mb-2">Raw API Response:</h3>
                <pre className="bg-gray-100 p-4 rounded-lg overflow-x-auto">
                    <code>{rawJson}</code>
                </pre>
            </div>
        </div>
    );
} 