package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.User;
import com.example.project3.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers() {
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

    @PostMapping("/register/customer")
    public ResponseEntity registerCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
        authService.registerCustomer(customerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Customer registered successfully"));
    }

    @PostMapping("/register/employee")
    public ResponseEntity registerEmployee(@Valid @RequestBody EmployeeDTO employeeDTO) {
        authService.registerEmployee(employeeDTO);
        return ResponseEntity.status(200).body(new ApiResponse("Employee registered successfully"));
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user , @Valid @RequestBody User newUser) {
        authService.updateUser(user.getId(), newUser);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteUser(@PathVariable Integer userId) {
        authService.deleteUser(userId);
        return ResponseEntity.status(200).body(new ApiResponse("Customer deleted successfully"));
    }


}
