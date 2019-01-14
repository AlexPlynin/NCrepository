package com.myproject.model;


import javax.persistence.*;

@Entity
@Table(name = "Model_characteristics")
public class ModelCharacteristics {

    public ModelCharacteristics() {
    }

    @Id
    @GeneratedValue
    @Column(name = "model_id")
    private Integer modelID;

    private Double diagonal;

    private String size;

    private String description;

    public Integer getModelID() {
        return modelID;
    }

    public void setModelID(Integer modelID) {
        this.modelID = modelID;
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

