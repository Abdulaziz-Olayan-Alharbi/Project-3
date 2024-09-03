package com.example.project3.Service;

import com.example.project3.Api.ApiException;
import com.example.project3.Model.Account;
import com.example.project3.Model.Customer;
import com.example.project3.Repository.AccountRepository;
import com.example.project3.Repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Integer customerId, Integer id) {
        Customer customer = customerRepository.findCustomerById(customerId);
        Account account =  accountRepository.findAccountByIdAndCustomer(id, customer);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        return account;
    }

    public List<Account> getMyAccounts(Integer customerId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        return accountRepository.findAccountByCustomer(customer);
    }

    public void addAccount(Integer customerId,Account account) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        account.setActive(false);
        account.setCustomer(customer);
        accountRepository.save(account);
    }

    public void updateAccount(Integer customerId,Integer accountId,Account account) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account oldAccount = accountRepository.findAccountByIdAndCustomer(account.getId(), customer);
        if (oldAccount == null) {
            throw new ApiException("Account not found");
        }
        oldAccount.setAccountNumber(account.getAccountNumber());
        oldAccount.setBalance(account.getBalance());
        accountRepository.save(oldAccount);
    }

    public void deleteAccount(Integer customerId,Integer accountId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountByIdAndCustomer(accountId, customer);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        accountRepository.delete(account);
    }

    public void activeAccount(Integer customerId,Integer accountId) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountByIdAndCustomer(accountId, customer);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        account.setActive(true);
        accountRepository.save(account);
    }

    public void depositMoney(Integer customerId,Integer accountId,Integer amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountByIdAndCustomer(accountId, customer);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.isActive()){
            throw new ApiException("Account is Blocked");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
    }

    public void withdrawMoney(Integer customerId,Integer accountId,Integer amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountByIdAndCustomer(accountId, customer);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.isActive()){
            throw new ApiException("Account is Blocked");
        }
        if (account.getBalance() - amount < 0){
            throw new ApiException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
    }

    public void transferMoney(Integer customerId,Integer accountId,String accountNumber,Integer amount) {
        Customer customer = customerRepository.findCustomerById(customerId);
        if (customer == null) {
            throw new ApiException("Customer not found");
        }
        Account account = accountRepository.findAccountByIdAndCustomer(accountId, customer);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.isActive()){
            throw new ApiException("Account is Blocked");
        }
        if (account.getBalance() - amount < 0){
            throw new ApiException("Insufficient balance");
        }
        Account toAccount = accountRepository.findAccountByAccountNumber(accountNumber);
        if (toAccount == null) {
            throw new ApiException("Account you want to transfer to it not found");
        }
        if (!toAccount.isActive()){
            throw new ApiException("Account you want to transfer to it is Blocked");
        }
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        toAccount.setBalance(toAccount.getBalance() + amount);
        accountRepository.save(toAccount);
    }

    public void blockAccount(Integer accountId) {
        Account account = accountRepository.findAccountById(accountId);
        if (account == null) {
            throw new ApiException("Account not found");
        }
        if (!account.isActive()){
            throw new ApiException("Account is Blocked");
        }
        account.setActive(false);
        accountRepository.save(account);
    }




}
