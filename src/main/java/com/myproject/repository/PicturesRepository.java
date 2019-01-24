package com.myproject.repository;

import com.myproject.model.Pictures;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PicturesRepository extends JpaRepository<Pictures,Long> {

    //Pictures findByModelId();

    Pictures findByModelId(Integer id);
}
