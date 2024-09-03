package com.example.project3.DTO;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class EmployeeDTO {
    @NotEmpty
    @Size(min = 4, max = 10)
    private String username;
    @NotEmpty
    @Size(min = 6)
    private String password;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp = "^(CUSTOMER|EMPLOYEE|ADMIN)$")
    private String role;
    @NotEmpty
    private String position;
    @NotNull
    @PositiveOrZero
    private double salary;
}
