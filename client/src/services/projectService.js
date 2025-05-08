import api from "./api";

export const projectService = {
  getAllProjects: async (semesterId = null, active = null) => {
    const params = {};
    if (semesterId) params.semesterId = semesterId;
    if (active !== null) params.active = active;

    const response = await api.get("/projects", { params });
    return response.data;
  },

  getProjectById: async (id) => {
    const response = await api.get(`/projects/${id}`);
    return response.data;
  },

  createProject: async (projectData) => {
    const response = await api.post("/projects", projectData);
    return response.data;
  },

  updateProject: async (id, projectData) => {
    const response = await api.put(`/projects/${id}`, projectData);
    return response.data;
  },

  deleteProject: async (id) => {
    await api.delete(`/projects/${id}`);
  },
};
