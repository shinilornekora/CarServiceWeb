package org.shiniasse.dtos;

import org.shiniasse.entities.enums.Role;


public class UserRoleDTO extends BaseDTO {
    private Role role;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
