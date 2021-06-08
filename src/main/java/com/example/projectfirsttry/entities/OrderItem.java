package com.example.projectfirsttry.entities;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@NoArgsConstructor
@Entity
@Setter
@Getter
@Data
@Table(name = "order_item")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(max = 20)
    @Column(name = "item_name")
    private String name;

    @NotNull
    private int quantity;

    @Column(name = "order_id")
    private int order;
//    @ManyToOne()
//    @JoinColumn(name = "order_id")
//    private Orders orders;


}
