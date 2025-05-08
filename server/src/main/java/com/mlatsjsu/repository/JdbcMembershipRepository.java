package com.mlatsjsu.repository;

import com.mlatsjsu.model.Membership;
import com.mlatsjsu.model.User;
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
public class JdbcMembershipRepository implements MembershipRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<Membership> membershipRowMapper = new RowMapper<Membership>() {
        @Override
        public Membership mapRow(ResultSet rs, int rowNum) throws SQLException {
            Membership membership = new Membership();
            membership.setMembershipId(rs.getLong("membership_id"));
            membership.setUserId(rs.getLong("user_id"));
            membership.setProjectId(rs.getLong("project_id"));
            membership.setRole(rs.getString("role"));
            membership.setStatus(rs.getString("status"));

            User user = new User();
            user.setUserId(rs.getLong("user_id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setManager(rs.getBoolean("is_manager"));
            membership.setUser(user);

            return membership;
        }
    };

    @Override
    public List<Membership> findByProjectId(Long projectId) {
        return jdbcTemplate.query(
                "SELECT pm.*, u.name, u.email, u.is_manager " +
                        "FROM project_memberships pm " +
                        "JOIN users u ON pm.user_id = u.user_id " +
                        "WHERE pm.project_id = ?",
                membershipRowMapper, projectId);
    }

    @Override
    public Optional<Membership> findById(Long membershipId) {
        try {
            Membership membership = jdbcTemplate.queryForObject(
                    "SELECT pm.*, u.name, u.email, u.is_manager " +
                            "FROM project_memberships pm " +
                            "JOIN users u ON pm.user_id = u.user_id " +
                            "WHERE pm.membership_id = ?",
                    membershipRowMapper, membershipId);
            return Optional.ofNullable(membership);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Membership save(Membership membership) {
        jdbcTemplate.update(
                "INSERT INTO project_memberships (user_id, project_id, role, status) VALUES(?,?,?,?)",
                membership.getUserId(), membership.getProjectId(), membership.getRole(), membership.getStatus());

        return jdbcTemplate.queryForObject(
                "SELECT pm.*, u.name, u.email, u.is_manager " +
                        "FROM project_memberships pm " +
                        "JOIN users u ON pm.user_id = u.user_id " +
                        "WHERE pm.user_id = ? AND pm.project_id = ?",
                membershipRowMapper, membership.getUserId(), membership.getProjectId());
    }

    @Override
    public int update(Membership membership) {
        return jdbcTemplate.update(
                "UPDATE project_memberships SET role=?, status=? WHERE user_id=? AND project_id=?",
                membership.getRole(), membership.getStatus(), membership.getUserId(), membership.getProjectId());
    }

    @Override
    public int deleteById(Long membershipId) {
        return jdbcTemplate.update(
                "DELETE FROM project_memberships WHERE membership_id=?", membershipId);
    }
}