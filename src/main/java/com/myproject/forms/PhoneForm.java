package com.myproject.forms;

import lombok.Data;

import java.util.Date;

@Data
public class PhoneForm {
    private String color;

    private String description;

    private Double price;

    private Double diagonal;

    private String size;

    private byte[] picture;

}
