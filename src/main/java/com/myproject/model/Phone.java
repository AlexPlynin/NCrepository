package com.myproject.model;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Date;

@Entity
@Table(name = "Phones")

public class Phone {

    public Phone() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "model_id")       //однонаправленное отношение
    private ModelCharacteristics modelCharacteristics;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "color")
    private Pictures pictures;

    public Integer getId() {
        return id;
    }
    //@Column(name = "cost")
    private Double price;
    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_data")
    private Date date;

    public void setId(Integer id) {
        this.id = id;
    }

    public ModelCharacteristics getModelCharacteristics() {
        return modelCharacteristics;
    }

    public void setModelCharacteristics(ModelCharacteristics modelCharacteristics) {
        this.modelCharacteristics = modelCharacteristics;
    }

    public Pictures getPictures() {
        return pictures;
    }

    public void setPictures(Pictures pictures) {
        this.pictures = pictures;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
