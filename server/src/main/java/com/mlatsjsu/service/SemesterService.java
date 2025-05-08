package com.mlatsjsu.service;

import com.mlatsjsu.model.Semester;
import java.util.List;
import java.util.Optional;

public interface SemesterService {
    List<Semester> findAll();

    Optional<Semester> findById(Long id);

    Semester create(Semester semester);

    Semester update(Long id, Semester semester);

    void delete(Long id);
}