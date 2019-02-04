package com.myproject.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Model_characteristics")

public class ModelCharacteristics {



    public ModelCharacteristics() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer modelId;

    @OneToMany(mappedBy = "modelCharacteristics", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<Phone> phones;



    private Double diagonal;

    private String size;

    private String description;

    public ModelCharacteristics(Set<Phone> phones, Double diagonal, String size, String description) {
        this.phones = phones;
        this.diagonal = diagonal;
        this.size = size;
        this.description = description;
    }

    public ModelCharacteristics(Double diagonal, String size, String description) {
        this.diagonal = diagonal;
        this.size = size;
        this.description = description;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Set<Phone> getPhones() {
        return phones;
    }

    public void setPhones(Set<Phone> phones) {
        this.phones = phones;
    }

    public Double getDiagonal() {
        return diagonal;
    }

    public void setDiagonal(Double diagonal) {
        this.diagonal = diagonal;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

