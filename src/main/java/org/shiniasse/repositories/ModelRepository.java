package org.shiniasse.repositories;

import org.shiniasse.entities.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Models, String > {
    List<Models> getModelsByBrandId(String brandId);
}
