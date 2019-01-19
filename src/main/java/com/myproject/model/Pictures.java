package com.myproject.model;

import javax.persistence.*;

@Entity
public class Pictures {
    public Pictures() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "model_id")
    private Integer modelId;

    @OneToOne(mappedBy = "pictures")
    private Phone phone;

    private String color;
    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] picture;

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
