package com.myproject.controller1;

import com.myproject.model.User;
import com.myproject.repository.UserRepository;
import com.myproject.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("user",new User());
        return "registration";
    }

    //@Transactional
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@Valid User user, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {


        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {

            model.addAttribute("passwordError", "Password are different");
            return "registration";
        } else  if (user.getPassword() != null){
            Pattern p = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$");
            Matcher m = p.matcher(user.getPassword());

            if (m.matches()) return "login";
            else {

                model.addAttribute("passwordError", "Password should contain Up,Lo case letter and digit, no space ");
                return "registration";
            }


            //(?=.*[0-9])       # a digit must occur at least once
            //(?=.*[a-z])       # a lower case letter must occur at least once
            //(?=.*[A-Z])       # an upper case letter must occur at least once
            //(?=.*[@#$%^&+=])  # a special character must occur at least once
            //(?=\S+$)          # no whitespace allowed in the entire string
            //.{8,}             # anything, at least eight places though

        }


        if (bindingResult.hasErrors()) {
//            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
//            model.mergeAttributes(errorsMap);
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.addAttribute("message", "User exist");
            return "registration";
        }

        userService.addUser(user);
        return "redirect:/login";
    }


}
