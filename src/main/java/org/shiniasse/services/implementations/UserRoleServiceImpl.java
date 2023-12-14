package org.shiniasse.services.implementations;

import org.modelmapper.ModelMapper;
import org.shiniasse.dtos.UserRoleDTO;
import org.shiniasse.entities.UserRole;
import org.shiniasse.repositories.UserRoleRepository;
import org.shiniasse.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@EnableCaching
@Service
public class UserRoleServiceImpl implements UserRoleService<String> {
    private final ModelMapper modelMapper;
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(ModelMapper modelMapper, UserRoleRepository userRoleRepository) {
        this.modelMapper = modelMapper;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public void saveUserRole(UserRoleDTO userRoleDTO) {
        try {
            userRoleRepository.saveAndFlush(modelMapper.map(userRoleDTO, UserRole.class));
        } catch (DataAccessException e) {
            System.out.println("Failed to save " + e.getMessage());
        }
    }

    @Cacheable("userRole")
    @Override
    public UserRoleDTO getUserRole(String uuid) {
        try {
            return modelMapper.map(userRoleRepository.findById(uuid), UserRoleDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object with id " + uuid + " could not be found");
        }
    }

    @CacheEvict(cacheNames = "userRole", allEntries = true)
    @Override
    public void updateUserRole(UserRoleDTO userRoleDTO) {
        saveUserRole(userRoleDTO);
    }

    @CacheEvict(cacheNames = "userRole", allEntries = true)
    @Override
    public void deleteUserRole(String uuid) {
        userRoleRepository.deleteById(uuid);
    }
}
