package com.sergio.auth.backend.auth.repositories;

import com.sergio.auth.backend.auth.entities.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<AuthUser, Long> {

    Optional<AuthUser> findByLogin(String login);

    @Query("select u.authority from AuthUser u where u.login=:username")
    String findRoleByUsername(@Param("username") String username);
}
