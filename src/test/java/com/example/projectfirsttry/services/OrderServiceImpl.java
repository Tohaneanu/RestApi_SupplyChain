package com.example.projectfirsttry.services;

import com.example.projectfirsttry.entities.Address;
import com.example.projectfirsttry.entities.OrderItem;
import com.example.projectfirsttry.entities.Orders;
import com.example.projectfirsttry.repository.OrderRepository;
import com.example.projectfirsttry.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImpl {
    private OrderRepository orderRepository;
    private OrderService orderService;

    @BeforeEach
    void setUserRepository() {
        orderRepository = Mockito.mock(OrderRepository.class);
        orderService = Mockito.mock(OrderService.class);
    }

    @AfterEach
    void tearDown() {
        orderRepository = null;
        orderService = null;
    }

    @Test
    @DisplayName("test if add order method works")
    void shouldAddOrderInDB() {
        OrderItem orderItem = new OrderItem();
        orderItem.setName("lollipops");
        orderItem.setQuantity(10);
        Address address = new Address();
        address.setCity("Cluj");
        address.setCountry("Romania");
        address.setStreet("s");
        address.setPhoneNumber(222222222);
        Orders orders = new Orders();
        orders.setCostumer("Client1");
        List<OrderItem> items = List.of(orderItem);
        orders.setOrderItems(items);
        orders.setAddress(address);
        orders.setStatus("New");


        orderService.addOrder(orders);

        ArgumentCaptor<Orders> ordersArgumentCaptor = ArgumentCaptor.forClass(Orders.class);
        verify(orderService, times(1)).addOrder(ordersArgumentCaptor.capture());

        Orders result = ordersArgumentCaptor.getValue();
        assertNotNull(result);
       assertEquals("Client1",result.getCostumer());
       assertEquals("Cluj",result.getAddress().getCity());
    }



}
