package com.mlatsjsu.repository;

import com.mlatsjsu.model.Project;
import com.mlatsjsu.model.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcProjectRepository implements ProjectRepository {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        private final RowMapper<Project> projectRowMapper = new RowMapper<Project>() {
                @Override
                public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Project project = new Project();
                        project.setProjectId(rs.getLong("project_id"));
                        project.setTitle(rs.getString("title"));
                        project.setDescription(rs.getString("description"));
                        project.setTopic(rs.getString("topic"));
                        project.setSemesterId(rs.getLong("semester_id"));
                        project.setGithubLink(rs.getString("github_link"));
                        project.setSpotsAvailable(rs.getInt("spots_available"));
                        project.setApproved(rs.getBoolean("is_approved"));
                        return project;
                }
        };

        @Override
        public List<Project> findAll() {
                return jdbcTemplate.query(
                                "SELECT p.*, s.term, s.year FROM projects p " +
                                                "JOIN semesters s ON p.semester_id = s.semester_id",
                                projectRowMapper);
        }

        @Override
        public List<Project> findBySemesterId(Long semesterId) {
                return jdbcTemplate.query(
                                "SELECT p.*, s.term, s.year FROM projects p " +
                                                "JOIN semesters s ON p.semester_id = s.semester_id " +
                                                "WHERE p.semester_id = ?",
                                projectRowMapper, semesterId);
        }

        @Override
        public List<Project> findByActive(boolean active) {
                return jdbcTemplate.query(
                                "SELECT p.*, s.term, s.year FROM projects p " +
                                                "JOIN semesters s ON p.semester_id = s.semester_id " +
                                                "WHERE p.is_approved = ?",
                                projectRowMapper, active);
        }

        @Override
        public Optional<Project> findById(Long id) {
                try {
                        Project project = jdbcTemplate.queryForObject(
                                        "SELECT p.*, s.term, s.year FROM projects p " +
                                                        "JOIN semesters s ON p.semester_id = s.semester_id " +
                                                        "WHERE p.project_id = ?",
                                        projectRowMapper, id);
                        return Optional.ofNullable(project);
                } catch (IncorrectResultSizeDataAccessException e) {
                        return Optional.empty();
                }
        }

        @Override
        public Project save(Project project) {
                jdbcTemplate.update(
                                "INSERT INTO projects (title, description, topic, semester_id, github_link, spots_available, is_approved) "
                                                +
                                                "VALUES(?,?,?,?,?,?,?)",
                                project.getTitle(), project.getDescription(), project.getTopic(),
                                project.getSemesterId(), project.getGithubLink(),
                                project.getSpotsAvailable(), project.isApproved());

                return jdbcTemplate.queryForObject(
                                "SELECT p.*, s.term, s.year FROM projects p " +
                                                "JOIN semesters s ON p.semester_id = s.semester_id " +
                                                "WHERE p.title = ?",
                                projectRowMapper, project.getTitle());
        }

        @Override
        public int update(Project project) {
                return jdbcTemplate.update(
                                "UPDATE projects SET title=?, description=?, topic=?, semester_id=?, " +
                                                "github_link=?, spots_available=?, is_approved=? " +
                                                "WHERE project_id=?",
                                project.getTitle(), project.getDescription(), project.getTopic(),
                                project.getSemesterId(), project.getGithubLink(),
                                project.getSpotsAvailable(), project.isApproved(), project.getProjectId());
        }

        @Override
        public int deleteById(Long id) {
                return jdbcTemplate.update("DELETE FROM projects WHERE project_id=?", id);
        }
}