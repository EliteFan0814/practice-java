package org.example;

import org.springframework.beans.factory.annotation.Autowired;

public class OrderService {
    @Autowired
    private OrderDao orderDao;
    public void doSomeThing(){
        orderDao.select();
    }
}
