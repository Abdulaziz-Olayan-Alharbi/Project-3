package com.example.project3.Controller;

import com.example.project3.Api.ApiResponse;
import com.example.project3.Model.Account;
import com.example.project3.Model.User;
import com.example.project3.Service.AccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/get-all")
    public ResponseEntity getAllAccounts() {
        return ResponseEntity.status(200).body(accountService.getAllAccounts());
    }

    @GetMapping("/get-one/{accountId}")
    public ResponseEntity getOneAccount(@AuthenticationPrincipal User user , @PathVariable Integer accountId) {
        return ResponseEntity.status(200).body(accountService.getAccountById(user.getId(), accountId));
    }

    @GetMapping("/get-mine")
    public ResponseEntity getMineAccount(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(accountService.getMyAccounts(user.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity addAccount(@AuthenticationPrincipal User user , @Valid @RequestBody Account account) {
        accountService.addAccount(user.getId(), account);
        return ResponseEntity.status(200).body(new ApiResponse("Account added successfully"));
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity updateAccount(@AuthenticationPrincipal User user , @PathVariable Integer accountId, @Valid @RequestBody Account account) {
        accountService.updateAccount(user.getId(), accountId, account);
        return ResponseEntity.status(200).body(new ApiResponse("Account updated successfully"));
    }

    @DeleteMapping("/delete/{accountId}")
    public ResponseEntity deleteAccount(@AuthenticationPrincipal User user , @PathVariable Integer accountId) {
        accountService.deleteAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account deleted successfully"));
    }

    @PutMapping("/activate/{accountId}")
    public ResponseEntity activateAccount(@AuthenticationPrincipal User user , @PathVariable Integer accountId) {
        accountService.activeAccount(user.getId(), accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account activated successfully"));
    }

    @PutMapping("/deposit/{accountId}/{amount}")
    public ResponseEntity depositMoney(@AuthenticationPrincipal User user , @PathVariable Integer accountId, @PathVariable Integer amount) {
        accountService.depositMoney(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Money deposited successfully"));
    }

    @PutMapping("/withdraw/{accountId}/{amount}")
    public ResponseEntity withdrawMoney(@AuthenticationPrincipal User user , @PathVariable Integer accountId, @PathVariable Integer amount) {
        accountService.withdrawMoney(user.getId(), accountId, amount);
        return ResponseEntity.status(200).body(new ApiResponse("Money withdrawn successfully"));
    }

    @PutMapping("/transfer/{accountId}/{toAccountNumber}/{amount}")
    public ResponseEntity transferMoney(@AuthenticationPrincipal User user , @PathVariable Integer accountId, @PathVariable String toAccountNumber, @PathVariable Integer amount) {
        accountService.transferMoney(user.getId(),accountId,toAccountNumber,amount);
        return ResponseEntity.status(200).body(new ApiResponse("Money transferred successfully"));
    }

    @PutMapping("/block/{accountId}")
    public ResponseEntity blockAccount(@PathVariable Integer accountId){
        accountService.blockAccount(accountId);
        return ResponseEntity.status(200).body(new ApiResponse("Account blocked successfully"));
    }


}
