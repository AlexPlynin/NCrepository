package com.myproject.init;

import com.myproject.forms.PhoneForm;
import com.myproject.model.*;
import com.myproject.repository.ModelCharacteristicsRepository;
import com.myproject.repository.PhoneRepository;
import com.myproject.repository.PicturesRepository;
import com.myproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.myproject.utils.ImageUtil.loadImage;


@Component
@ComponentScan(basePackages = "com.myproject.repository")
public class LoadToBase implements ApplicationRunner {


    private ModelCharacteristicsRepository modelCharacteristicsRepository;

    private PhoneRepository phoneRepository;

    private PicturesRepository picturesRepository;

    private UserRepository userRepository;
    //private PictureServiceImpl pictureService;

    @Autowired
    EntityManager entityManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public LoadToBase() {
    }

    @Autowired
    public LoadToBase(ModelCharacteristicsRepository modelCharacteristicsRepository,
                      PhoneRepository phoneRepository,
                      PicturesRepository picturesRepository,
                      UserRepository userRepository) {
        this.modelCharacteristicsRepository = modelCharacteristicsRepository;
        this.phoneRepository = phoneRepository;
        this.picturesRepository = picturesRepository;
        this.userRepository = userRepository;

    }


    public void setPhones() {
        List<ModelCharacteristics> modelCharacteristicsList = new ArrayList<>();
        List<Phone> phoneList = new ArrayList<>();
        List<Pictures> picturesList = new ArrayList<>();


        ModelCharacteristics model1 = new ModelCharacteristics(5.6, "Big", "Iphone XR 64 GB");
        ModelCharacteristics model2 = new ModelCharacteristics(6.6, "Big", "Samsung Galaxy S9 64 GB");
        ModelCharacteristics model3 = new ModelCharacteristics(6.7, "Big", "Huawei Mate 20 Pro 64 GB");
        //System.out.println(model1);

        Collections.addAll(modelCharacteristicsList, model1, model2, model3);
       // modelCharacteristicsList.forEach(System.out::println);
        modelCharacteristicsList.forEach((model) -> modelCharacteristicsRepository.saveAndFlush(model));

        System.out.println(modelCharacteristicsRepository);
//        for (ModelCharacteristics model:modelCharacteristicsList) {
//            modelCharacteristicsRepository.save(model);
//
//        }

        Pictures picture3 = new Pictures("green", loadImage("/pictures/huaweimate20pro.jpg"));
        Pictures picture1 = new Pictures("black", loadImage("/pictures/iphoneXR.jpg"));
        Pictures picture2 = new Pictures("red", loadImage("/pictures/samsungGS9.jpg"));

        Collections.addAll(picturesList, picture1, picture2, picture3);
        picturesList.forEach((model) -> picturesRepository.saveAndFlush(model));

        Phone phone1 = new Phone(model1, picture1, "yellow", 76000.99);
        Phone phone2 = new Phone(model2, picture2, "black", 45000.99);
        Phone phone3 = new Phone(model3, picture3, "blue", 47000.99);

        Collections.addAll(phoneList, phone1, phone2, phone3);
        phoneList.forEach((model) -> phoneRepository.saveAndFlush(model));


    }

    public void setAdmin(){
        User inMemoryUser = new User("admin",bCryptPasswordEncoder.encode("1"),0.0,true, Collections.singleton(Role.ADMIN));
        userRepository.save(inMemoryUser);
    }

    public void setUser(){
        User inMemoryUser = new User("alex",bCryptPasswordEncoder.encode("1"),0.0,true, Collections.singleton(Role.USER));
        userRepository.save(inMemoryUser);
    }

    public void setPhoneFromForm(PhoneForm phoneForm) {
        ModelCharacteristics modelCharacteristics = new ModelCharacteristics(phoneForm.getDiagonal(), phoneForm.getSize(), phoneForm.getDescription());
        modelCharacteristicsRepository.saveAndFlush(modelCharacteristics);

        Pictures pictures = new Pictures(phoneForm.getColor(), phoneForm.getPicture());
        picturesRepository.saveAndFlush(pictures);

        //System.out.println(    "!!!!!!!!!!!!!!!!!!!"   + new String(phoneForm.getPicture()));

        Phone phone = new Phone(modelCharacteristics, pictures, phoneForm.getColor(), phoneForm.getPrice());
        phoneRepository.saveAndFlush(phone);
        //entityManager.refresh(phone);

    }



    public void changePhone(PhoneForm phoneForm, Phone phone) {

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
        setAdmin();
        setUser();
    }
}
