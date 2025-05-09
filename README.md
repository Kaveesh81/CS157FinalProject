# ML Servlet Project

This is a full-stack web application with a React frontend and Java Servlet backend.

## Prerequisites

- Java JDK 17 or higher
- Maven 3.6 or higher
- Node.js 16 or higher
- npm 8 or higher
- MySQL 8.0 or higher

## Project Structure

```
mlservlet/
├── backend/           # Java Servlet backend
│   ├── src/          # Source code
│   └── pom.xml       # Maven configuration
└── client/           # React frontend
    ├── src/          # Source code
    ├── vite.config.js # Vite configuration with proxy
    └── package.json  # npm configuration
```

## Database Setup

1. Create a MySQL database named `mlservlet`
2. Update the database configuration in `backend/src/main/resources/database.properties`:
```properties
db.url=jdbc:mysql://localhost:3306/mlservlet
db.username=your_username
db.password=your_password
```

## Backend Setup

1. Navigate to the backend directory:
```bash
cd mlservlet/backend
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn tomcat7:run
```

The backend server will start at http://localhost:8081

## Frontend Setup

1. Navigate to the client directory:
```bash
cd mlservlet/client
```

2. Install dependencies:
```bash
npm install
```

3. Start the development server:
```bash
npm run dev
```

The frontend will be available at http://localhost:5174

## Development Configuration

### Vite Proxy Configuration
The frontend uses Vite's development server proxy to handle CORS during development. This is configured in `vite.config.js`:

```javascript
export default defineConfig({
  server: {
    proxy: {
      '/api': {
        target: 'http://localhost:8081/mlservlet',
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '')
      }
    }
  }
})
```

This configuration:
- Proxies all requests from `/api/*` to the backend server
- Handles CORS automatically during development
- Rewrites the paths to match the backend API structure

## Testing the Application

1. Open http://localhost:5174/test-crud in your browser
2. Use the test interface to verify CRUD operations:
   - Get Projects/Members (READ)
   - Create Project/Member (CREATE)
   - Update Project/Member (UPDATE)
   - Delete Project/Member (DELETE)

## Troubleshooting

### CORS Issues
If you encounter CORS errors:
1. Make sure both frontend and backend are running
2. Check that the backend is running on port 8081
3. Check that the frontend is running on port 5174
4. Verify that the Vite proxy is properly configured in vite.config.js

### Database Connection Issues
If you have database connection problems:
1. Verify MySQL is running
2. Check database credentials in database.properties
3. Ensure the database and tables exist

### Build Issues
If you encounter build problems:
1. Clean and rebuild the backend:
```bash
cd mlservlet/backend
mvn clean install
```

2. Reinstall frontend dependencies:
```bash
cd mlservlet/client
rm -rf node_modules
npm install
```

## Development
### Building and Running the Application

1. Start the backend server:
```bash
  cd mlservlet/backend
  mvn clean install
  mvn tomcat7:run
  ```
New Terminal
```bash
  cd mlservlet/client
npm run dev
  ```



- Backend API endpoints:
  - GET /mlservlet/api/projects
  - POST /mlservlet/api/projects
  - PUT /mlservlet/api/projects/{id}
  - DELETE /mlservlet/api/projects/{id}
  - GET /mlservlet/api/members
  - POST /mlservlet/api/members
  - PUT /mlservlet/api/members/{id}
  - DELETE /mlservlet/api/members/{id}

- Frontend development:
  - Uses React with Material-UI
  - Hot reloading enabled
  - ESLint for code quality
  - Vite proxy for CORS handling

## License

This project is licensed under the MIT License. 