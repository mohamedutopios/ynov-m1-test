package org.example.userunittestmockitointegration.repository;


import org.example.userunittestmockitointegration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
