package com.springlearning.springlearning.repository;

import com.springlearning.springlearning.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String value);

    Optional<List<User>> findByOccupation(String value);

}
