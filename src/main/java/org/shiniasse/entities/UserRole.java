package org.shiniasse.entities;

import jakarta.persistence.*;
import org.shiniasse.entities.enums.Role;

import java.util.List;

@Entity
public class UserRole extends InheritableIdEntity {
    private Role role;
    private List<User> users; // maybe Set

    public UserRole(Role role) {
        this.role = role;
    }

    public UserRole() {
    }

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    @OneToMany(mappedBy = "role")
    public List<User> getUsers() {
        return users;
    }
    public void setUsers(List<User> users) {
        this.users = users;
    }
}
