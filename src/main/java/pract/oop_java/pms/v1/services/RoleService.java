package pract.oop_java.pms.v1.services;

import pract.oop_java.pms.v1.models.Role;

import java.util.List;
import java.util.UUID;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(UUID id);
    Role createRole(String roleName);
    Role updateRole(UUID id, String roleName);
    void deleteRole(UUID id);
}
