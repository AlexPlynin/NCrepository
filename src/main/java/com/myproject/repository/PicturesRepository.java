package com.myproject.repository;

import com.myproject.model.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PicturesRepository extends JpaRepository<Pictures,Long> {
}
