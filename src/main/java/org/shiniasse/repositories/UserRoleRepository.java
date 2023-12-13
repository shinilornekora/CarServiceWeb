package org.shiniasse.repositories;

import org.shiniasse.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, String > {
}
