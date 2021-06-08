package com.example.projectfirsttry.repository;

import com.example.projectfirsttry.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Orders,Integer> {

    List<Orders> findAllByCostumer(String name);
    List<Orders> findAllByManufacturer(String name);
}
