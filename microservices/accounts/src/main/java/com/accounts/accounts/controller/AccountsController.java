package com.accounts.accounts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.micrometer.observation.autoconfigure.ObservationProperties.Http;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.accounts.accounts.constants.AccountsConstants;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.dto.ResponseDto;
import com.accounts.accounts.service.IAccountService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Validated
public class AccountsController {
    // @Autowired
    private IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<?> createAccount(@Valid@RequestBody CustomerDto customerDto) {
        // try {
        // System.out.println(customerDto);
        iAccountService.createAccount(customerDto);
        return ResponseEntity.status(HttpStatus.OK).body(AccountsConstants.message_201);
        // } catch (Exception e) {
        // System.out.println(e.getMessage());
        // return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        // }
    }

    @GetMapping("/fetch")
    public ResponseEntity<?> fetchAccountDetails(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number must be 10 number and contains numbers only")
        String mobileNumber) {
        try {

            return ResponseEntity.status(HttpStatus.OK).body(iAccountService.fetchAccount(mobileNumber));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> methodName(@Valid@RequestBody CustomerDto customerDto) {

        boolean isUpdated = iAccountService.updateAccount(customerDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(AccountsConstants.status_200, AccountsConstants.message_200));
        } else {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(AccountsConstants.status_417, AccountsConstants.message_417_update));
        }
        
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> methodName(@RequestParam @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number must be 10 number and contains numbers only")
     String mobileNumber ) {
        System.out.println(mobileNumber);
        boolean isDeleted=iAccountService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK)
            .body(new ResponseDto(AccountsConstants.status_200, AccountsConstants.message_200));
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
            .body(new ResponseDto(AccountsConstants.status_417, AccountsConstants.message_417_update));
        }
      
    }

    @GetMapping("")
    public ResponseEntity<?> getHello() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body("Hello");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("null");
        }
    }
}
