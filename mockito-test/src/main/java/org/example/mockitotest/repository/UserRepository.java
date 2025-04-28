package org.example.mockitotest.repository;

import org.example.mockitotest.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
