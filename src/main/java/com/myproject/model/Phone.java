package com.myproject.model;

import javax.persistence.*;
import javax.sql.DataSource;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Phones")

public class Phone {

    public Phone() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "phone_id")
    private Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY) //правильно
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private ModelCharacteristics modelCharacteristics;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY) //У множества телефонов одна характеризующая картинка
    @JoinColumn(name = "PICTURE_ID")
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

    //@Column(name = "cost")
    private Double price;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_data")
    private Date date;

    }
