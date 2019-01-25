package com.myproject.model;

import lombok.Data;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Phones")
//@Data
public class Phone {

    public Phone() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "phone_id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY) //правильно
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private ModelCharacteristics modelCharacteristics;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    //У множества телефонов одна характеризующая картинка
    @JoinColumn(name = "PICTURE_ID",nullable = false)
    private Pictures pictures;//

    //    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY) //цвет отдие к одному
//    @JoinColumn(name = "color")
//    private Pictures color;
    private String color;

    public Phone(String color, Double price) {
        this.color = color;
        this.price = price;
        this.date = new Date();
    }

    public Phone(ModelCharacteristics modelCharacteristics, Pictures pictures, String color, Double price) {
        this.modelCharacteristics = modelCharacteristics;
        this.pictures = pictures;
        this.color = color;
        this.price = price;
        this.date = new Date();
    }

    //@Column(name = "cost")
    private Double price;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_data")
    private Date date;

    public Integer getId() {
        return id;
    }

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    @Override
    public String toString() {
        return "PhoneForm{" +
                "id=" + id +
                '}';
    }
}
