package com.accounts.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountsDto {
    @Pattern(regexp = "(^$|[0-9]{10})",message = "mobile number must be 10 number")
    private Long accountNumber;
    @NotEmpty(message = "AccountType can not be null or empty")
    private String accountType;
    @NotEmpty(message = "BranchAddress can not be null or empty")
    private String branchAddress; 
}
