package org.shiniasse.services;

import org.shiniasse.dtos.ModelDTO;

import java.util.List;

public interface ModelService<ID> {
    List<ModelDTO> getAllModels();
    void saveModel(ModelDTO modelDTO);
    ModelDTO saveAndGetModel(ModelDTO modelDTO);
    ModelDTO getModel(ID id);
    void updateModel(ModelDTO modelDTO);
    void deleteModel(ID id);
    List<ModelDTO> getModelsByBrandId(String brandId);

}
