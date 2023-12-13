package org.shiniasse.dtos;

//  TODO ask about base class with ID for DTOs
public abstract class BaseDTO {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BaseDTO{" +
                "id='" + id + '\'' +
                '}';
    }
}
