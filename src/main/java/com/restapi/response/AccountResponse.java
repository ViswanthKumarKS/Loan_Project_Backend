package com.restapi.response;

import com.restapi.request.AccountRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class AccountResponse {

  private List<AccountRequest> accountObjects = new ArrayList<>();

  private Long id;
  private Long acc_no;
  private double balance;
  private String name;
  private String address;
  private String state;
  private String city;
  private String username;


}
