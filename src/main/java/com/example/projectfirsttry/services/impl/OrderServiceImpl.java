package com.example.projectfirsttry.services.impl;

import com.example.projectfirsttry.entities.OrderItem;
import com.example.projectfirsttry.entities.Orders;
import com.example.projectfirsttry.repository.OrderRepository;
import com.example.projectfirsttry.services.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final JdbcTemplate jdbcTemplate;

    @Transactional
    @Override
    public void addOrder(Orders order) {
        for (OrderItem item: order.getOrderItems())
        jdbcTemplate.update("INSERT INTO order_item(item_name, quantity, order_id) VALUES(?,?,?)",item.getName(),item.getQuantity(),item.getOrder());
        jdbcTemplate.update("INSERT INTO project_test.delivery_address(project_test.delivery_address.street, project_test.delivery_address.city, project_test.delivery_address.phone_number, project_test.delivery_address.country) VALUES (?,?,?,?)",order.getAddress().getStreet(),order.getAddress().getCity(),order.getAddress().getPhoneNumber(),order.getAddress().getCountry());
        jdbcTemplate.update("INSERT into orders(costumer_name, delivery_id, manufacturer_name, order_status) VALUES (?,?,?,?)",order.getCostumer(),order.getAddress(),order.getManufacturer(),order.getStatus());
    }

    @Override
    public void updateOrderQuantity(Orders order) {
        jdbcTemplate.update("UPDATE order_item set quantity=? where order_id=?",order.getOrderItems().get(0).getQuantity(),order.getId());
    }
}

