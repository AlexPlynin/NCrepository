package com.myproject.repository;

import com.myproject.model.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface PhoneRepository extends JpaRepository<Phone,Long> {




}
