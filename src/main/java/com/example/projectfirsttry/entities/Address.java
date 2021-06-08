package com.example.projectfirsttry.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Setter
@Getter
@Data
@Entity
@Table(name = "delivery_address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max=20)
    private String street;

    @NotNull
    @Size(min = 3,max = 20)
    private String city;

    @Column(name="phone_number")
    private int phoneNumber;

    @NotNull
    @Size(max = 20)
    private String country;


}
