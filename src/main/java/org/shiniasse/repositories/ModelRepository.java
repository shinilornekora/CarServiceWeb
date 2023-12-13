package org.shiniasse.repositories;

import org.shiniasse.entities.Models;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ModelRepository extends JpaRepository<Models, String > {
    List<Models> getModelsByBrandId(String brandId);
}
