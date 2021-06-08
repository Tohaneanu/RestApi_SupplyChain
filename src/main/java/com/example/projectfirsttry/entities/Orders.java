package com.example.projectfirsttry.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.LifecycleState;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(name = "order_status")
    private String status;

    @NotNull
    @Size( max = 20)
    @Column(name = "costumer_name")
    private String costumer;


    @Column(name = "manufacturer_name")
    private String manufacturer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Address address;

//    @OneToMany(mappedBy = "orders")
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> orderItems;


}
