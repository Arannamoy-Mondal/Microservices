package com.accounts.accounts.service.impl;

import java.util.Random;

import com.accounts.accounts.constants.AccountsConstants;
import com.accounts.accounts.dto.CustomerDto;
import com.accounts.accounts.entity.Accounts;
import com.accounts.accounts.entity.Customer;
import com.accounts.accounts.mapper.CustomerMapper;
import com.accounts.accounts.repo.AccountsRepo;
import com.accounts.accounts.repo.CustomerRepo;
import com.accounts.accounts.service.IAccountService;

public class AccountsServiceImple implements IAccountService{
    private AccountsRepo accountsRepo;
    private CustomerRepo customerRepo;
    @Override
    public void createAccount(CustomerDto customerDto) {
      
        Customer customer=CustomerMapper.mapToCustomer(customerDto, new Customer());
        Customer savedCustomer=customerRepo.save(customer);
        accountsRepo.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fetchAccount'");
    }

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateAccount'");
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAccount'");
    }
    
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount=new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber= 1000000000L+new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(AccountsConstants.Savings);
        newAccount.setBranchAddress(AccountsConstants.Address);
        return newAccount;
    }
}
