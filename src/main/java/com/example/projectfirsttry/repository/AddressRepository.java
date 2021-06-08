package com.example.projectfirsttry.repository;

import com.example.projectfirsttry.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
