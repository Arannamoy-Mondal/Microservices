package com.accounts.accounts.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.accounts.accounts.entity.Customer;

@Repository
public interface CustomerRepo extends JpaRepository<Customer,Long> {
   
    Optional<Customer> findByMobileNumber(String mobileNumber);
}
