package com.example.project3.Service;

import com.example.project3.Model.Employee;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Integer id) {
        return employeeRepository.findEmployeeById(id);
    }

    public void updateEmployee(Integer id,Employee employee) {
        Employee oldEmployee = employeeRepository.findEmployeeById(id);
        oldEmployee.setPosition(employee.getPosition());
        oldEmployee.setSalary(employee.getSalary());
        employeeRepository.save(oldEmployee);
    }
}
