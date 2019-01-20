package com.myproject.model;


import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Model_characteristics")
@Data
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


}

