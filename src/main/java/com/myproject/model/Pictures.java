package com.myproject.model;



import javax.persistence.*;
import java.util.Base64;
import java.util.Set;

@Entity
public class Pictures {
    public Pictures() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "model_id")
    private Integer modelId;

    @OneToMany(mappedBy = "pictures",fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    private Set<Phone> phone;

    //@OneToOne(mappedBy = "color")
    private String color;

    public Pictures(String color, byte[] picture) {
        this.color = color;
        this.picture = picture;
    }

    public Integer getModelId() {
        return modelId;
    }

    public void setModelId(Integer modelId) {
        this.modelId = modelId;
    }

    public Set<Phone> getPhone() {
        return phone;
    }

    public void setPhone(Set<Phone> phone) {
        this.phone = phone;
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

    public String getEncodePictures(){


        return Base64.getEncoder().encodeToString(getPicture());
    }

    public String getStringView(){
        return new String(getPicture());
    }
    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @Lob
    @Column(columnDefinition = "BLOB")
    private byte[] picture;

}
