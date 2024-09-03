package com.example.project3.Service;

import com.example.project3.Model.Customer;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer getCustomer(Integer id) {
        return customerRepository.findCustomerById(id);
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public void updateCustomer(Integer id, Customer customer) {
        Customer oldCustomer = customerRepository.findCustomerById(id);
        oldCustomer.setPhoneNumber(customer.getPhoneNumber());
        customerRepository.save(oldCustomer);
    }
}
