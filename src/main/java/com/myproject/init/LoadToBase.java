package com.myproject.init;

import com.myproject.forms.PhoneForm;
import com.myproject.model.ModelCharacteristics;
import com.myproject.model.Phone;
import com.myproject.model.Pictures;
import com.myproject.repository.ModelCharacteristicsRepository;
import com.myproject.repository.PhoneRepository;
import com.myproject.repository.PicturesRepository;
import com.myproject.service.PictureService.PictureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import static com.myproject.utils.ImageUtil.loadImage;


@Component
@ComponentScan(basePackages = "com.myproject.repository")
public class LoadToBase implements ApplicationRunner {


    private  ModelCharacteristicsRepository modelCharacteristicsRepository;

    private  PhoneRepository phoneRepository;

    private  PicturesRepository picturesRepository;
    //private PictureServiceImpl pictureService;



    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public LoadToBase() {
    }

        @Autowired
    public LoadToBase(ModelCharacteristicsRepository modelCharacteristicsRepository, PhoneRepository phoneRepository, PicturesRepository picturesRepository) {
        this.modelCharacteristicsRepository = modelCharacteristicsRepository;
        this.phoneRepository = phoneRepository;
        this.picturesRepository = picturesRepository;
        //this.pictureService = pictureService;
    }


    public void setPhones(){
        List<ModelCharacteristics> modelCharacteristicsList = new ArrayList<>();
        List<Phone> phoneList = new ArrayList<>();
        List<Pictures> picturesList = new ArrayList<>();


        ModelCharacteristics model1 = new ModelCharacteristics(5.6,"Big","Iphone XR 64 GB");
        ModelCharacteristics model2 = new ModelCharacteristics(6.6,"Big","Samsung Galaxy S9 64 GB");
        ModelCharacteristics model3 = new ModelCharacteristics(6.7,"Big","Huawei Mate 20 Pro 64 GB");
        //System.out.println(model1);

        Collections.addAll(modelCharacteristicsList,model1,model2,model3);
        modelCharacteristicsList.forEach(System.out::println);
        modelCharacteristicsList.forEach((model)-> modelCharacteristicsRepository.saveAndFlush(model));

        System.out.println(modelCharacteristicsRepository);
//        for (ModelCharacteristics model:modelCharacteristicsList) {
//            modelCharacteristicsRepository.save(model);
//
//        }

        Pictures picture2 = new Pictures("green",loadImage("/pictures/huaweimate20pro.jpg"));
        Pictures picture1 = new Pictures("black",loadImage("/pictures/iphoneXR.jpg"));
        Pictures picture3= new Pictures("red",loadImage("/pictures/samsungGS9.jpg"));

        Collections.addAll(picturesList,picture1,picture2,picture3);
        picturesList.forEach((model)->picturesRepository.saveAndFlush(model));

        Phone phone1 = new Phone(model1,picture1,"yellow",76000.99);
        Phone phone2 = new Phone(model2,picture3,"black",45000.99);
        Phone phone3 = new Phone(model3,picture2,"blue",47000.99);

        Collections.addAll(phoneList,phone1,phone2,phone3);
        phoneList.forEach((model)->phoneRepository.saveAndFlush(model));



    }
    public void setPhoneFromForm(PhoneForm phoneForm){
        ModelCharacteristics modelCharacteristics = new ModelCharacteristics(phoneForm.getDiagonal(),phoneForm.getSize(),phoneForm.getDescription());
        modelCharacteristicsRepository.saveAndFlush(modelCharacteristics);

        Pictures pictures = new Pictures(phoneForm.getColor(),phoneForm.getPicture());
        picturesRepository.saveAndFlush(pictures);

        //System.out.println(    "!!!!!!!!!!!!!!!!!!!"   + new String(phoneForm.getPicture()));

        Phone phone = new Phone(modelCharacteristics,pictures,phoneForm.getColor(),phoneForm.getPrice());
        phoneRepository.saveAndFlush(phone);

    }

    public void changePhone(PhoneForm phoneForm,Phone phone){

        phone.setColor(phoneForm.getColor());
        phone.getModelCharacteristics().setDescription(phoneForm.getDescription());
        phone.getModelCharacteristics().setDiagonal(phoneForm.getDiagonal());
        phone.getModelCharacteristics().setSize(phoneForm.getSize());
        phone.setPrice(phoneForm.getPrice());
        phone.getPictures().setPicture(phoneForm.getPicture());



    }




    @Override
    public void run(ApplicationArguments args) throws Exception {
        setPhones();
    }
}
