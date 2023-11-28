package com.restapi.repository;

import com.restapi.model.Account;
import com.restapi.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {


    @Query("SELECT a FROM Account a WHERE a.appUser.id = ?1")
    Optional<Account> findByUserId(Long userId);
}
