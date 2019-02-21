package com.myproject.service.phoneService;


import com.myproject.model.Phone;
import com.myproject.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;


    public Phone findById(String id){
        return phoneRepository.findById(Integer.valueOf(id));
    }

    @Transactional
    public void deleteById(Integer id){

        phoneRepository.deleteById(id);

    }

    @Transactional
    public void deleteByIdIn(List<Integer> ids){
        phoneRepository.deleteByIdIn(ids);
    }


}
