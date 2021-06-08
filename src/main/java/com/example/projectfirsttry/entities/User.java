package com.example.projectfirsttry.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Table(name = "user")
@Entity
@Data


public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min=3, max = 30)
    private String name;

    @NotNull
    @Size(min=3, max = 100)
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Roles> roles;


    
}
