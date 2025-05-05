package web.servlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lib.model.Project;
import lib.service.ProjectService;
import lib.service.ServiceException;

/**
 * Servlet for handling Project-related HTTP requests
 */
@WebServlet("/projects/*")
public class ProjectServlet extends HttpServlet {
    private ProjectService projectService;
    
    @Override
    public void init() throws ServletException {
        projectService = new ProjectService();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        
        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                // List all projects
                List<Project> projects = projectService.getAllProjects();
                request.setAttribute("projects", projects);
                request.getRequestDispatcher("/WEB-INF/views/projects/list.jsp").forward(request, response);
            } else if (pathInfo.equals("/create")) {
                // Show create project form
                request.getRequestDispatcher("/WEB-INF/views/projects/create.jsp").forward(request, response);
            } else {
                // Get project by ID
                String[] pathParts = pathInfo.split("/");
                if (pathParts.length > 1) {
                    int projectId = Integer.parseInt(pathParts[1]);
                    Project project = projectService.getProjectById(projectId);
                    
                    if (project != null) {
                        request.setAttribute("project", project);
                        request.getRequestDispatcher("/WEB-INF/views/projects/view.jsp").forward(request, response);
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "Project not found");
                    }
                } else {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid project ID");
                }
            }
        } catch (ServiceException e) {
            throw new ServletException("Error processing project request", e);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid project ID format");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("create".equals(action)) {
                // Create new project
                Project project = new Project();
                project.setTitle(request.getParameter("title"));
                project.setDescription(request.getParameter("description"));
                project.setTopic(request.getParameter("topic"));
                project.setSemesterId(Integer.parseInt(request.getParameter("semesterId")));
                project.setProjectLeadId(Integer.parseInt(request.getParameter("projectLeadId")));
                project.setGitHubLink(request.getParameter("gitHubLink"));
                project.setSpotsAvailable(Integer.parseInt(request.getParameter("spotsAvailable")));
                project.setStatus(request.getParameter("status"));
                
                int projectId = projectService.createProject(project);
                response.sendRedirect(request.getContextPath() + "/projects/" + projectId);
            } else if ("update".equals(action)) {
                // Update existing project
                Project project = new Project();
                project.setProjectId(Integer.parseInt(request.getParameter("projectId")));
                project.setTitle(request.getParameter("title"));
                project.setDescription(request.getParameter("description"));
                project.setTopic(request.getParameter("topic"));
                project.setSemesterId(Integer.parseInt(request.getParameter("semesterId")));
                project.setProjectLeadId(Integer.parseInt(request.getParameter("projectLeadId")));
                project.setGitHubLink(request.getParameter("gitHubLink"));
                project.setSpotsAvailable(Integer.parseInt(request.getParameter("spotsAvailable")));
                project.setStatus(request.getParameter("status"));
                
                boolean updated = projectService.updateProject(project);
                if (updated) {
                    response.sendRedirect(request.getContextPath() + "/projects/" + project.getProjectId());
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to update project");
                }
            } else if ("delete".equals(action)) {
                // Delete project
                int projectId = Integer.parseInt(request.getParameter("projectId"));
                boolean deleted = projectService.deleteProject(projectId);
                if (deleted) {
                    response.sendRedirect(request.getContextPath() + "/projects");
                } else {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to delete project");
                }
            } else {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
            }
        } catch (ServiceException e) {
            throw new ServletException("Error processing project request", e);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric parameter");
        }  {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric parameter");
        }
    }
}
