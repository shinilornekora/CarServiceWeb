package org.shiniasse.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.Set;

@Entity
public class Brands extends InheritableDataEntity {
    private String name;
    private Set<Models> models; // all models in set is unique

    public Brands() {
    }

    @Column(name = "name", nullable = false, unique = true)
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "brand")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE) // cascade from hibernate
    public Set<Models> getModels() {
        return models;
    }
    public void setModels(Set<Models> models) {
        this.models = models;
    }
}
