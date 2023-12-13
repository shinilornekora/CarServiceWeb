package org.shiniasse.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import java.time.LocalDateTime;


@MappedSuperclass
public abstract class InheritableDataEntity extends InheritableIdEntity {
    private LocalDateTime modifiedTime;
    private LocalDateTime createdTime;

    @Column(name = "modified_time")
    public LocalDateTime getModifiedTime() {
        return modifiedTime;
    }

    @PreUpdate
    public void fixModifiedTime() {
        this.modifiedTime = LocalDateTime.now();
    }
    @Column(name = "created_time")
    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    @PrePersist
    public void fixCreatedTime() {
        this.createdTime = LocalDateTime.now();
    }

    public void setModifiedTime(LocalDateTime modifiedTime) {
        this.modifiedTime = modifiedTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}
