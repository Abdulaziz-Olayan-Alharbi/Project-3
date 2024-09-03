package com.example.project3.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty
    @Pattern(regexp = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")
    @Column(columnDefinition = "varchar(30) not null unique")
    private String accountNumber;
    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "double not null")
    private double balance;
    @Column(columnDefinition = "boolean")
    private boolean isActive = false;
    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
