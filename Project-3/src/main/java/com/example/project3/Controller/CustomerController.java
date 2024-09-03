package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.Model.Customer;
import com.example.project3.Model.User;
import com.example.project3.Service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/customer")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/one")
    public ResponseEntity getCustomer(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(customerService.getCustomer(user.getId()));
    }

    @GetMapping("/get")
    public ResponseEntity getAllCustomer() {
        return ResponseEntity.status(200).body(customerService.getAllCustomers());
    }

    @PutMapping("/update")
    public ResponseEntity updateCustomer(@AuthenticationPrincipal User user, @Valid @RequestBody Customer customer) {
        customerService.updateCustomer(user.getId(), customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer updated successfully"));
    }
}
