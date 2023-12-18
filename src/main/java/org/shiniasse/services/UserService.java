package org.shiniasse.services;

import org.shiniasse.dtos.UserDTO;

import java.util.List;

public interface UserService<ID> {
    List<UserDTO> getAllUsers();
    void saveUser(UserDTO userDTO);
    UserDTO save(UserDTO userDTO);
    UserDTO getUser(ID id);
    void updateUser(UserDTO userDTO);
    void deleteUser(ID id);
}
