package org.shiniasse.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.shiniasse.entities.enums.Category;

import java.util.List;

@Entity
@Table(name = "models")
public class Models extends InheritableDataEntity {
    private String name;
    private Category category;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private Brands brand;
    private List<Offer> offers;

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    TODO converter for enums - ask again
    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Column(name = "start_year")
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    @Column(name = "end_year")
    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    @ManyToOne
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "brand_id")
    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) {
        this.brand = brand;
    }

    @OneToMany(mappedBy = "model")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }
}
