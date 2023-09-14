package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.Customer;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.domain.User;
import com.unimelb.swen90007.group404notfound.api.mapper.CustomerMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.ShareMapper;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyListingService;
import com.unimelb.swen90007.group404notfound.api.service.ICustomerService;
import com.unimelb.swen90007.group404notfound.api.service.IListingService;
import com.unimelb.swen90007.group404notfound.api.util.UnitOfWork;

import java.math.BigDecimal;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    private CustomerMapper customerMapper = new CustomerMapper();
    private ShareMapper shareMapper = new ShareMapper();
    public Boolean buy(Long uid, Long lid, Integer amount){
        CustomerMapper customerMapper = new CustomerMapper();
        UnitOfWork.newCurrent();
        Customer customer = customerMapper.findCustomerById(uid);
        ICompanyListingService companyListingService = new CompanyListingServiceImpl();
        BigDecimal totalPrice = companyListingService.totalPrice(lid, amount);
        BigDecimal balance = customer.getBalance();
        if(balance.compareTo(totalPrice)<0){
            return false;
        }else{
            customer.setBalance(balance.subtract(totalPrice));
            UnitOfWork.getCurrent().registerDirty(customer);
            boolean result = UnitOfWork.getCurrent().commit();
            return result;

        }
    }

    @Override
    public List<Customer> viewAllCustomer() {
        List<Customer> customers = customerMapper.getAllCustomer();
        return customers;
    }

    @Override
    public boolean addBalance(Long uid, BigDecimal totalPrice) {
        Customer customer = customerMapper.findCustomerById(uid);
        customer.setBalance(customer.getBalance().add(totalPrice));
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerDirty(customer);
        return UnitOfWork.getCurrent().commit();
    }
}
