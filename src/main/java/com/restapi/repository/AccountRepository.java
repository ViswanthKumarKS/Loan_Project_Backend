package com.restapi.repository;

import com.restapi.model.Account;
import com.restapi.response.AccountResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository <Account, Long> {



}
