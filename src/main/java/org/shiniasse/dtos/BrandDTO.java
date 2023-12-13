package org.shiniasse.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

public class BrandDTO extends BaseDTO {
    private String name;

    public BrandDTO() {
    }

    @NotNull
    @NotEmpty(message = "Name can't be empty")
    @Size(min = 2, message = "Name length must be more than two characters")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "BrandDTO{" +
                "id='" + this.getId() + '\'' +
                "name='" + name + '\'' +
                '}';
    }
}
