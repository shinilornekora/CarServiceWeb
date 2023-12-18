package org.shiniasse.services;

import org.shiniasse.dtos.BrandDTO;

import java.util.List;

public interface BrandService<ID> {
    List<BrandDTO> getAllBrands();
    void saveBrand(BrandDTO brandDTO);
    BrandDTO save(BrandDTO brandDTO);
    BrandDTO getBrand(ID id);
    void updateBrand(BrandDTO brandDTO);
    void deleteBrand(ID id);
}
