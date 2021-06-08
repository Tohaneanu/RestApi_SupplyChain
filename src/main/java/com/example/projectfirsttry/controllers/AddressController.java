package com.example.projectfirsttry.controllers;

import com.example.projectfirsttry.entities.Address;
import com.example.projectfirsttry.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class AddressController {

    private final AddressRepository addressRepository;

    public AddressController(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @GetMapping(path = "/address",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Address> getAddress(Authentication a){

        return this.addressRepository.findAll();
    }
}
