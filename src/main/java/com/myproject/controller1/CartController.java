package com.myproject.controller1;


import com.myproject.model.Phone;
import com.myproject.model.User;
import com.myproject.service.orderService.OrderService;
import com.myproject.service.phoneService.PhoneService;
import com.myproject.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class CartController {

    private UserService userService;
    private PhoneService phoneService;
    private OrderService orderService;

    private static List<Phone> phonesList = new ArrayList<>();


   @Autowired
   public CartController(UserService userService, PhoneService phoneService, OrderService orderService) {
        this.userService = userService;
        this.phoneService = phoneService;
       this.orderService = orderService;
   }

    @GetMapping("/cart/{user}")
    public String getCart(@PathVariable User user,Model model) {

       //в случае отсутствия заказа выводим сообщение
       if (orderService.getOrder()==null || orderService.getOrder().getPhones().isEmpty()){
           model.addAttribute("message","Your cart is empty");
           return "cart";
       }
       model.addAttribute("phones",orderService.getOrder().getPhones());

       return "cart";
    }

    //@ModelAttribute("phoneCount")
    @PostMapping("/addPhoneToCart")
    public String addPhoneToCart(@RequestParam String id, Model model, ModelAndView modelAndView) {

        orderService.addOrder(id);
        model.addAttribute("phoneCount",orderService.getProductCountInOrder());

        return "redirect:/main";
    }

    @PostMapping("/inlineDeleteFromCart")
    public String deletePhone(@RequestParam String id, Model model, ModelAndView modelAndView) {

        orderService.deleteProductFromOrder(id);
        //model.addAttribute("user",userService.getCurrentUserId());
        return "redirect:/cart/"+userService.getCurrentUser().getId();
    }


}
