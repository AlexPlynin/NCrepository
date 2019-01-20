package com.myproject.repository;

import com.myproject.model.ModelCharacteristics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelCharacteristicsRepository extends JpaRepository<ModelCharacteristics,Long> {
    //void addModel();

}
