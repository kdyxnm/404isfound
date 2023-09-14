package com.unimelb.swen90007.group404notfound.api.service;

import com.unimelb.swen90007.group404notfound.api.domain.Customer;

import java.math.BigDecimal;
import java.util.List;

public interface ICustomerService{
    Boolean buy(Long uid, Long lid, Integer amount);
    List<Customer> viewAllCustomer();
    boolean addBalance(Long uid, BigDecimal totalPrice);
}
