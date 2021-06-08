package com.example.projectfirsttry.services;

import com.example.projectfirsttry.entities.Orders;

public interface OrderService {
    void addOrder(Orders order);
    void updateOrderQuantity(Orders order);
}
