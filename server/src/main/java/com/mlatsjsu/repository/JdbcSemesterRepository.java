package com.mlatsjsu.repository;

import com.mlatsjsu.model.Semester;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcSemesterRepository implements SemesterRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Semester> findAll() {
        return jdbcTemplate.query("SELECT * FROM semesters",
                BeanPropertyRowMapper.newInstance(Semester.class));
    }

    @Override
    public Optional<Semester> findById(Long id) {
        try {
            Semester semester = jdbcTemplate.queryForObject("SELECT * FROM semesters WHERE semester_id=?",
                    BeanPropertyRowMapper.newInstance(Semester.class), id);
            return Optional.ofNullable(semester);
        } catch (IncorrectResultSizeDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Semester save(Semester semester) {
        jdbcTemplate.update(
                "INSERT INTO semesters (term, year) VALUES(?,?)",
                semester.getTerm(), semester.getYear());

        return jdbcTemplate.queryForObject(
                "SELECT * FROM semesters WHERE term=? AND year=?",
                BeanPropertyRowMapper.newInstance(Semester.class),
                semester.getTerm(), semester.getYear());
    }

    @Override
    public int update(Semester semester) {
        return jdbcTemplate.update(
                "UPDATE semesters SET term=?, year=? WHERE semester_id=?",
                semester.getTerm(), semester.getYear(), semester.getSemesterId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM semesters WHERE semester_id=?", id);
    }
}