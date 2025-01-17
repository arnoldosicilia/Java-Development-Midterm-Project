package com.ironhack.midtermProject.repository;

import com.ironhack.midtermProject.model.AccountHolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    public AccountHolder findByUsername(String username);
    public AccountHolder findByName(String name);
}
