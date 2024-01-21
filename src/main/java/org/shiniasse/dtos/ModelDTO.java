package org.shiniasse.dtos;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.shiniasse.entities.enums.Category;

public class ModelDTO extends BaseDTO {
    private String name;
    private Category category;
    private String imageUrl;
    private int startYear;
    private int endYear;
    private BrandDTO brand;

    public ModelDTO() {
    }

    @NotNull(message = "Name can't be empty")
    @Length(min = 2, message = "Name length must be more than two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

//    @NotNull
//    @NotEmpty
//    @Length(min = 2, message = "Brand name length must be more than two characters")
    public BrandDTO getBrand() {
        return brand;
    }

    public void setBrand(BrandDTO brand) {
        this.brand = brand;
    }
}
