package com.example.projectfirsttry.controllers;

import com.example.projectfirsttry.entities.Orders;
import com.example.projectfirsttry.exceptions.NotFoundException;
import com.example.projectfirsttry.repository.OrderRepository;
import com.example.projectfirsttry.services.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
@AllArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final OrderService orderService;

    @GetMapping(path = "/admin/orders",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> getOrders(){
        return this.orderRepository.findAll();
    }

    @GetMapping(path = "/admin/orders/c/{clientName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> seeOrdersByClientName(@PathVariable String clientName){
        List<Orders> allByCostumer = this.orderRepository.findAllByCostumer(clientName);
        if (allByCostumer.isEmpty()) throw new NotFoundException("Dont find orders for this costumers");
        return allByCostumer;
    }
    @GetMapping(path = "/admin/orders/m/{manufacturerName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Orders> getOrdersByManufacturerName(@PathVariable String manufacturerName){
        List<Orders> allByCostumer = this.orderRepository.findAllByManufacturer(manufacturerName);
        if (allByCostumer.isEmpty()) throw new NotFoundException("Dont find orders for this manufacturer");
        return allByCostumer;
    }
    @GetMapping(path = "/orders/{client}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Orders>> getOrdersByClientName(@PathVariable String client){
        List<Orders> allFromClient=this.orderRepository.findAllByCostumer(client);
        return new ResponseEntity<>(allFromClient, HttpStatus.OK);

    }
    @PostMapping(path = "/command/{clientName}")
    public ResponseEntity<Void> addOrder(@PathVariable String clientName,@Valid @RequestBody Orders order)  {
        order.setCostumer(clientName);
        this.orderService.addOrder(order);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping(path = "/order/edit/{id}",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public void editOrder(@PathVariable Integer id,@RequestBody Orders order){
        order.setId(id);
        orderService.updateOrderQuantity(order);
    }
    @DeleteMapping (path = "/order/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteOrder(@PathVariable Integer id){
        orderRepository.delete(orderRepository.getById(id));
    }

}
