package org.shiniasse.services;

import org.shiniasse.dtos.UserRoleDTO;

public interface UserRoleService<ID> {
    void saveUserRole(UserRoleDTO userRoleDTO);
    UserRoleDTO getUserRole(ID id);
    void updateUserRole(UserRoleDTO userRoleDTO);
    void deleteUserRole(ID id);
}
