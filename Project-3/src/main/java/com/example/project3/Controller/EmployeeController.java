package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/get")
    public ResponseEntity getAllEmployees() {
        return ResponseEntity.status(200).body(employeeService.getAllEmployees());
    }

    @GetMapping("/one")
    public ResponseEntity getOneEmployee(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(employeeService.getEmployeeById(user.getId()));
    }

    @PutMapping("/update")
    public ResponseEntity updateEmployee(@AuthenticationPrincipal User user, @Valid @RequestBody Employee employee) {
        employeeService.updateEmployee(user.getId(), employee);
        return ResponseEntity.status(200).body(new ApiResponse("Employee updated successfully"));
    }
}
