import React, { useState, useEffect } from 'react';
import '../styles/Users.css';

const Users = () => {
  const [users, setUsers] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = await fetch('http://localhost:8080/api/v1/users');
        if (!response.ok) {
          throw new Error('Failed to fetch users');
        }
        const data = await response.json();
        setUsers(data);
        setLoading(false);
      } catch (err) {
        setError(err.message);
        setLoading(false);
      }
    };

    fetchUsers();
  }, []);

  if (loading) return <div className="loading">Loading users...</div>;
  if (error) return <div className="error">Error: {error}</div>;

  return (
    <div className="users-container">
      <h2>ML@SJSU Members</h2>
      <div className="users-grid">
        {users.map((user) => (
          <div key={user.userId} className="user-card">
            <h3>{user.name}</h3>
            <p><strong>Role:</strong> {user.role}</p>
            <p><strong>Graduation:</strong> {user.gradDate}</p>
            <p><strong>Email:</strong> {user.email}</p>
            {user.linkedin && (
              <a href={user.linkedin} target="_blank" rel="noopener noreferrer" className="linkedin-link">
                LinkedIn Profile
              </a>
            )}
          </div>
        ))}
      </div>
    </div>
  );
};

export default Users;

