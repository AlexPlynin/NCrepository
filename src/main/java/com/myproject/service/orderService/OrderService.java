package com.myproject.service.orderService;


import com.myproject.model.Order;
import com.myproject.model.Phone;
import com.myproject.model.Product;
import com.myproject.model.User;
import com.myproject.repository.OrderRepository;
import com.myproject.service.phoneService.PhoneService;
import com.myproject.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private PhoneService phoneService;

    private static Map<String,Order> orderMap = new HashMap<>();

    public void addOrder(String phoneId){

        String userId = userService.getCurrentUserId();

        User user = userService.getCurrentUser();
        if (user.getOrder()==null){
            user.setOrder(new Order());
        }

        Phone phone = phoneService.findById(phoneId);

        Order currentOrder = user.getOrder();
        currentOrder.setPhone(phone);

        orderRepository.save(currentOrder);
        //orderMap.put(userId,currentOrder);




    }

    public Order getOrder(){

        return userService.getCurrentUser().getOrder();
    }

    public Integer getProductCountInOrder(){

        return userService.getCurrentUser().getOrder().getPhones().size();
    }

    @Transactional
    public void deleteProductFromOrder(String id){


        Phone phone = phoneService.findById(id);

        Order order = getOrder();
        List<Phone> phones = order.getPhones();
        phones.removeIf(phone1 -> phone1.getId().equals(Integer.valueOf(id)));
        userService.getCurrentUser().setOrder(order);

    }


    public void deletePhoneFromOrder(){

    }





}
