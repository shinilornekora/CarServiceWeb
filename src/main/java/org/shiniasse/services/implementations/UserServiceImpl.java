package org.shiniasse.services.implementations;

import org.modelmapper.ModelMapper;
import org.shiniasse.dtos.UserDTO;
import org.shiniasse.entities.Offer;
import org.shiniasse.entities.User;
import org.shiniasse.repositories.UserRepository;
import org.shiniasse.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService<String > {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }
    @Override
    public List<UserDTO> getAllUsers() {
        try {
            return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).collect(Collectors.toList());
        } catch (DataAccessException e) {
            System.out.println("Failed to get " + e.getMessage());
        }
        return null;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        try {
            userRepository.saveAndFlush(modelMapper.map(userDTO, User.class));
        } catch (DataAccessException e) {
            System.out.println("Failed to save " + e.getMessage());
        }
    }

    @Override
    public UserDTO saveAndGetUser(UserDTO userDTO) {
        try {
            return modelMapper.map(
                    userRepository.saveAndFlush(modelMapper.map(userDTO, User.class)), UserDTO.class);
        } catch (DataAccessException e) {
            System.out.println("Failed to save " + e.getMessage());
        }
        return null;
    }

    @Override
    public UserDTO getUser(String uuid) {
        try {
            return modelMapper.map(userRepository.findById(uuid), UserDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object with id " + uuid + " could not be found");
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        saveUser(userDTO);
    }

    @Override
    public void deleteUser(String uuid) {
        userRepository.deleteById(uuid);
    }


}
