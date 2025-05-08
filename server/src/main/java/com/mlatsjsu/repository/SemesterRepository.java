package com.mlatsjsu.repository;

import com.mlatsjsu.model.Semester;
import java.util.List;
import java.util.Optional;

public interface SemesterRepository {
    List<Semester> findAll();

    Optional<Semester> findById(Long id);

    Semester save(Semester semester);

    int update(Semester semester);

    int deleteById(Long id);
}