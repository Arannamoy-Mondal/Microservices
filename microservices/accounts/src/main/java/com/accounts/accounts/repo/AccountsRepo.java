package com.accounts.accounts.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import com.accounts.accounts.entity.Accounts;

import jakarta.transaction.Transactional;



public interface AccountsRepo extends JpaRepository<Accounts,Long>{
    Optional<Accounts> findByCustomerId(Long customerId);
    @Transactional
    @Modifying
    void deleteByCustomerId(Long customerId);
}
