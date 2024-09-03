package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @Pattern(regexp = "^05\\d{8}$")
    @Column(columnDefinition = "varchar(10) not null unique")
    private String phoneNumber;
    @OneToOne
    @MapsId
    @JsonIgnore
    private User user;
    @OneToMany(cascade = CascadeType.ALL , mappedBy = "customer")
    private Set<Account> accounts;

}
