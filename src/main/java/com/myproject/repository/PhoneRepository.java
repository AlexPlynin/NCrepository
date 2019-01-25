package com.myproject.repository;

import com.myproject.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface PhoneRepository extends JpaRepository<Phone,Long> {


    Phone findById(Integer id);

    void deleteById(Integer id);
}
