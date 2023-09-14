package com.unimelb.swen90007.group404notfound.api.domain;

import com.unimelb.swen90007.group404notfound.api.mapper.CustomerMapper;

import java.math.BigDecimal;

public class Customer extends User{
    private BigDecimal balance;


    public Customer(Long userId, String username, String password, String lastname, String firstname) {
        super(userId, username, password, lastname, firstname);
    }

    public Customer(Long userId, String username, String password, String lastname, String firstname, BigDecimal balance) {
        super(userId, username, password, lastname, firstname);
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        if (balance == null) {
            load();
        }
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    void load() {
        super.load();
        CustomerMapper customerMapper = new CustomerMapper();
        Customer customerRecord;
        if (this.getUserId() == null && this.getUsername() != null) {
            customerRecord = customerMapper.findCustomerByUsername(this.getUsername());
        } else {
            customerRecord = customerMapper.findCustomerById(this.getUserId());
        }
        if (this.balance == null) {
            this.balance = customerRecord.getBalance();
        }
    }
}
