package com.mlatsjsu.service.impl;

import com.mlatsjsu.model.Semester;
import com.mlatsjsu.repository.SemesterRepository;
import com.mlatsjsu.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterServiceImpl implements SemesterService {

    @Autowired
    private SemesterRepository semesterRepository;

    @Override
    public List<Semester> findAll() {
        return semesterRepository.findAll();
    }

    @Override
    public Optional<Semester> findById(Long id) {
        return semesterRepository.findById(id);
    }

    @Override
    @Transactional
    public Semester create(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    @Transactional
    public Semester update(Long id, Semester semester) {
        semester.setSemesterId(id);
        semesterRepository.update(semester);
        return semesterRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        semesterRepository.deleteById(id);
    }
}