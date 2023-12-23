package org.shiniasse.services.implementations;

import org.modelmapper.ModelMapper;
import org.shiniasse.dtos.ModelDTO;
import org.shiniasse.entities.Brands;
import org.shiniasse.entities.Models;
import org.shiniasse.repositories.ModelRepository;
import org.shiniasse.services.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EnableCaching
@Service
public class ModelServiceImpl implements ModelService<String > {
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }
    @Cacheable("models")
    @Override
    public List<ModelDTO> getAllModels() {
        try {
            return modelRepository.findAll().stream().map(model -> modelMapper.map(model, ModelDTO.class)).collect(Collectors.toList());
        } catch (DataAccessException e) {
            System.out.println("Failed to get " + e.getMessage());
        }
        return null;
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void saveModel(ModelDTO modelDTO) {
        try {
            modelRepository.saveAndFlush(modelMapper.map(modelDTO, Models.class));
        } catch (DataAccessException e) {
            System.out.println("Failed to save " + e.getMessage());
        }
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public ModelDTO save(ModelDTO modelDTO) {
        try {
            return modelMapper.map(
                    modelRepository.saveAndFlush(modelMapper.map(modelDTO, Models.class)), ModelDTO.class);
        } catch (DataAccessException e) {
            System.out.println("Failed to save " + e.getMessage());
        }
        return null;
    }

    @Cacheable("models")
    @Override
    public ModelDTO getModel(String uuid) {
        try {
            return modelMapper.map(modelRepository.findById(uuid), ModelDTO.class);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Object with id " + uuid + " could not be found");
        }
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void updateModel(ModelDTO modelDTO) {
        saveModel(modelDTO);
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void deleteModel(String uuid) {
        modelRepository.deleteById(uuid);
    }

    @Cacheable("models")
    @Override
    public List<ModelDTO> getModelsByBrandId(String brandId) {
        List<Models> list = modelRepository.getModelsByBrandId(brandId);
        return list.stream()
                .map(e->modelMapper.map(e, ModelDTO.class))
                .collect(Collectors.toList());
    }

}
