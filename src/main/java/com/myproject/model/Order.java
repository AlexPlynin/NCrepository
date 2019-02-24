package com.myproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Cart_order")
public class Order {

    public Order() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Phone> phones = new ArrayList<>();

    @OneToOne(mappedBy = "order")
    private User user;

    public void setPhone(Phone phone) {
        this.phones.add(phone);
    }

}
