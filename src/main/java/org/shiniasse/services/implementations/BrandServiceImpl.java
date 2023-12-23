package org.shiniasse.services.implementations;

import org.modelmapper.ModelMapper;
import org.shiniasse.dtos.BrandDTO;
import org.shiniasse.entities.Brands;
import org.shiniasse.repositories.BrandRepository;
import org.shiniasse.services.BrandService;
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
public class BrandServiceImpl implements BrandService<String> {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<BrandDTO> getAllBrands() {
        try {
            return brandRepository.findAll().stream().map(brand -> modelMapper.map(brand, BrandDTO.class)).collect(Collectors.toList());
        } catch (DataAccessException e) {
            System.out.println("Failed to get " + e.getMessage());
        }
        return null;
    }
    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public void saveBrand(BrandDTO brandDTO) {
        try {
            brandRepository.saveAndFlush(modelMapper.map(brandDTO, Brands.class));
        } catch (DataAccessException e) {
            System.out.println("Failed to save " + e.getMessage());
        }
    }

    @CacheEvict(cacheNames = "models", allEntries = true)
    @Override
    public BrandDTO save(BrandDTO brandDTO) {
        try {
            return modelMapper.map(
                    brandRepository.saveAndFlush(modelMapper.map(brandDTO, Brands.class)), BrandDTO.class);
        } catch (DataAccessException e) {
            System.out.println("Failed to save " + e.getMessage());
        }
        return null;
    }

    @Cacheable("brands")
    @Override
    public BrandDTO getBrand(String uuid) {
        try {
            return modelMapper.map(brandRepository.findById(uuid), BrandDTO.class);
        } catch (Exception e) {
            throw new IllegalArgumentException("Object with id " + uuid + " could not be found");
        }
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    @Override
    public void updateBrand(BrandDTO brandDTO) {
        saveBrand(brandDTO);
    }

    @CacheEvict(cacheNames = "brands", allEntries = true)
    @Override
    public void deleteBrand(String uuid) {
        brandRepository.deleteById(uuid);
    }
}
