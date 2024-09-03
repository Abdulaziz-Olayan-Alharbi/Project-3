package com.example.project3.Service;

import com.example.project3.DTO.CustomerDTO;
import com.example.project3.DTO.EmployeeDTO;
import com.example.project3.Model.Customer;
import com.example.project3.Model.Employee;
import com.example.project3.Model.User;
import com.example.project3.Repository.AuthRepository;
import com.example.project3.Repository.CustomerRepository;
import com.example.project3.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    public List<User> getAllUsers() {
        return authRepository.findAll();
    }

    public void registerCustomer(CustomerDTO customerDTO) {
        String hash = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        User user = new User(null,customerDTO.getUsername(), hash, customerDTO.getName(), customerDTO.getEmail(), "CUSTOMER",null,null);
        authRepository.save(user);
        Customer customer = new Customer(null, customerDTO.getPhoneNumber(), user,null);
        customerRepository.save(customer);
    }

    public void registerEmployee(EmployeeDTO employeeDTO) {
        String hash = new BCryptPasswordEncoder().encode(employeeDTO.getPassword());
        User user = new User(null, employeeDTO.getUsername(), hash, employeeDTO.getName(), employeeDTO.getEmail(), "EMPLOYEE",null,null);
        authRepository.save(user);
        Employee employee = new Employee(null, employeeDTO.getPosition(), employeeDTO.getSalary(),user);
        employeeRepository.save(employee);
    }

    public void updateUser(Integer id,User user) {
        User oldUser = authRepository.findUserById(id);
        oldUser.setUsername(user.getUsername());
        String hash = new BCryptPasswordEncoder().encode(user.getPassword());
        oldUser.setPassword(hash);
        authRepository.save(oldUser);
    }

    public void deleteUser(Integer id) {
        User user = authRepository.findUserById(id);
        authRepository.delete(user);
    }
}
