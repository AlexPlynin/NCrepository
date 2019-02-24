package com.myproject.controller1;

import com.myproject.forms.PhoneForm;
import com.myproject.init.LoadToBase;
import com.myproject.model.Phone;
import com.myproject.repository.PhoneRepository;
import com.myproject.repository.PicturesRepository;
import com.myproject.service.phoneService.PhoneService;
import com.myproject.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Controller
public class MainController {


    @Autowired
    public MainController(PhoneRepository phoneRepository,
                          PicturesRepository picturesRepository,
                          LoadToBase loadToBase,
                          EntityManager entityManager,
                          PhoneService phoneService,
                          UserService userService) {
        this.phoneRepository = phoneRepository;
        this.picturesRepository = picturesRepository;
        this.loadToBase = loadToBase;
        this.entityManager = entityManager;
        this.phoneService = phoneService;
        this.userService = userService;
    }

    private PhoneRepository phoneRepository;
    private PicturesRepository picturesRepository;
    private LoadToBase loadToBase;
    private EntityManager entityManager;
    private PhoneService phoneService;
    private UserService userService;




    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(Model model) {

        return "greetings";
    }


    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String phones(Model model) {

        Iterable<Phone> phones = phoneRepository.findAll();
        //System.out.println(picturesRepository.findAll().get(0) + "!!!!!!!!");


        phoneRepository.findAll()//проходим по каждому телефону
                //.forEach(phone -> System.out.println(phone.getId()));

                .forEach(phone -> model.addAttribute(
                        Base64.getEncoder()
                                .encodeToString(picturesRepository.findByModelId(phone.getId())
                                        .getPicture())));


//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User user = (User) authentication.getPrincipal();

        model.addAttribute("user",userService.getCurrentUser());
        model.addAttribute("phones", phones);
        return "main";
    }

    @RequestMapping(value = "/addPhone", method = RequestMethod.GET)
    public String addPhonePage(Model model) {
        model.addAttribute("phone", new PhoneForm());                //проброз аттрибута в представление

        return "addPhone";
    }


    @RequestMapping(value = "/addPhone", method = RequestMethod.POST)
    //@PostMapping("/addPhone")
    //атрибут модели
    @Transactional
    public String addPhone(@RequestParam("file") MultipartFile file,
                           @ModelAttribute PhoneForm phoneForm,
                           RedirectAttributes redirectAttributes) throws IOException {
        //Работает так: получаем файл как параметр берем все байты и сразу сетим в модель-> получаем картинку
        try {
            phoneForm.setPicture(file.getBytes());//костыль
            loadToBase.setPhoneFromForm(phoneForm);

            redirectAttributes.addAttribute("message", "Successfully upload image" + file.getOriginalFilename());
        } catch (IOException e) {
            redirectAttributes.addAttribute("message", "Failed to upload" + file.getOriginalFilename() + e.getMessage());
        }


        return "redirect:/main";
    }

    //при удалении телефона проверить на пустоту директорию
    @RequestMapping(value = "/deletePhone", method = RequestMethod.GET)
    public String deletePhonePage(Model model) {
        //в будущем заменить редирект на что-то более интересное
        return phoneRepository.findAll().isEmpty() ? "redirect:/main" : "deletePhone";


    }

    @Transactional
    @RequestMapping(value = "/deletePhone", method = RequestMethod.POST)
    public String deletePhone(@RequestParam String id, Model model) {
        //как слать предупреждение на клиент возможно через тимлиф иф
        if (!id.isEmpty()) {
            phoneRepository.deleteById(Integer.valueOf(id));
            phoneRepository.flush();
        }


        return "redirect:/main";
    }


    @RequestMapping(value = "/inlineDeletePhone", method = RequestMethod.POST)
    public String inlineDeleteForm(@RequestParam String id) {


        phoneService.deleteById(Integer.valueOf(id));


        return "redirect:/main";

    }

    @RequestMapping(value = "/deleteCheck", method = RequestMethod.POST)
    public String deleteCheck(@RequestParam(value = "idChecked",required = false) List<Integer> idrates) {


        if (idrates != null) {
            phoneService.deleteByIdIn(idrates);
            System.out.println(idrates);
        }


        return "redirect:/main";

    }

    @RequestMapping(value = "/deleteCheckGet", method = RequestMethod.GET)
    public String deleteCheckGet(@RequestParam(value = "idList",required = false) List<Integer> idrates) {


        if (idrates != null) {
            phoneService.deleteByIdIn(idrates);
            System.out.println(idrates);
        }

        System.out.println(idrates);
        return "redirect:/main";

    }


    @RequestMapping(value = "/changePhone", method = RequestMethod.GET)
    public String changePhonePage(Model model) {
        model.addAttribute("phone", new PhoneForm());                //проброз аттрибута в представление

        return phoneRepository.findAll().isEmpty() ? "redirect:/main" : "changePhone";
    }

    @RequestMapping(value = "/changePhone", method = RequestMethod.POST)
    //@PostMapping("/addPhone")
    @Transactional
    public String changePhone(
            @RequestParam("file") MultipartFile file,
            @RequestParam String id,
            @ModelAttribute PhoneForm phoneForm,
            RedirectAttributes redirectAttributes) throws IOException {

        try {

            phoneForm.setPicture(file.getBytes());//костыль//Работает так: получаем файл как параметр берем все байты и сразу сетим в модель-> получаем картинку
            Phone phone = phoneRepository.findById(Integer.valueOf(id));
            loadToBase.changePhone(phoneForm, phone);
            redirectAttributes.addAttribute("message", "Successfully upload image" + file.getOriginalFilename());
        } catch (IOException e) {
            redirectAttributes.addAttribute("message", "Failed to upload" + file.getOriginalFilename() + e.getMessage());
        }


        return "redirect:/main";
    }
}


