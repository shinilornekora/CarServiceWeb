package org.shiniasse.repositories;

import org.shiniasse.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, String > {
    Optional<User> findUserByUsername(String username);
}
