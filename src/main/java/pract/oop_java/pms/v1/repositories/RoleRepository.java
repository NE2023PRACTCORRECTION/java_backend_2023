package pract.oop_java.pms.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pract.oop_java.pms.v1.models.Role;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
    Optional<Role> findRoleByRoleName(String name);
}
