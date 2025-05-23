package com.mlatsjsu.repository;

import com.mlatsjsu.model.User;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> findAll();

    Optional<User> findById(Long id);

    Optional<User> findByEmail(String email);

    User save(User user);

    int update(User user);

    int deleteById(Long id);
}