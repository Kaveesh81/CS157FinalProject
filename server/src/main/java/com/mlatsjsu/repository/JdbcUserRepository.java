package com.mlatsjsu.repository;

import com.mlatsjsu.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users",
                BeanPropertyRowMapper.newInstance(User.class));
    }

    @Override
    public Optional<User> findById(Long id) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id=?",
                    BeanPropertyRowMapper.newInstance(User.class), id);
            return Optional.ofNullable(user);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE email=?",
                    BeanPropertyRowMapper.newInstance(User.class), email);
            return Optional.ofNullable(user);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public User save(User user) {
        jdbcTemplate.update(
                "INSERT INTO users (name, email, linkedin, grad_date, start_date, is_manager) VALUES(?,?,?,?,?,?)",
                user.getName(), user.getEmail(), user.getLinkedin(), user.getGradDate(),
                user.getStartDate(), user.isManager());

        return findByEmail(user.getEmail()).orElseThrow();
    }

    @Override
    public int update(User user) {
        return jdbcTemplate.update(
                "UPDATE users SET name=?, email=?, linkedin=?, grad_date=?, start_date=?, is_manager=? WHERE user_id=?",
                user.getName(), user.getEmail(), user.getLinkedin(), user.getGradDate(),
                user.getStartDate(), user.isManager(), user.getUserId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE user_id=?", id);
    }
}