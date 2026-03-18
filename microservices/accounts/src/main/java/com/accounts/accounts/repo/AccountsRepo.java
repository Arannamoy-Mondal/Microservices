package com.accounts.accounts.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.accounts.accounts.entity.Accounts;

public interface AccountsRepo extends JpaRepository<Accounts,Long>{

}
