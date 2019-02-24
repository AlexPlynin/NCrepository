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

        if (!orderMap.containsKey(userId)){
            orderMap.put(userId, new Order());
        }

        Phone phone = phoneService.findById(phoneId);

        Order currentOrder = orderMap.get(userId);
        currentOrder.setPhone(phone);
        currentOrder.setUser(userService.getCurrentUser());

        orderMap.put(userId,currentOrder);




    }

    public Order getOrder(){

        return  orderMap.get(userService.getCurrentUserId());
    }

    public Integer getProductCountInOrder(){

        return orderMap.get(userService.getCurrentUserId()).getPhones().size();
    }

    @Transactional
    public void deleteProductFromOrder(String id){


        Phone phone = phoneService.findById(id);

        Order order = getOrder();
        orderRepository.deleteOrderById(order.getId());
        List<Phone> phones = order.getPhones();
        phones.remove(phone);
        //order.setPhones(phones);
       // orderRepository.
        //orderRepository.save(order);
       // orderRepository.findAll().
        //orderRepository.deletePhonesFromOrderById(Long.valueOf(id));

    }


    public void deletePhoneFromOrder(){

    }





}
