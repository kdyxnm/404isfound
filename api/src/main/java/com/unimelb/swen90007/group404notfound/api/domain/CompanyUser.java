package com.unimelb.swen90007.group404notfound.api.domain;

import com.unimelb.swen90007.group404notfound.api.mapper.CompanyUserMapper;

import java.math.BigDecimal;

public class CompanyUser extends User{
    private BigDecimal balance;
    private Long companyId;
    public CompanyUser(Long userId, String username, String password, String lastname, String firstname) {
        super(userId, username, password, lastname, firstname);


    }

    public CompanyUser(Long userId, String username, String password, String lastname, String firstname, BigDecimal balance, Long companyId) {
        super(userId, username, password, lastname, firstname);
        this.balance = balance;
        this.companyId = companyId;
    }

    public BigDecimal getBalance() {
        if (this.balance == null) {
            load();
        }
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getCompanyId() {
        if (this.companyId == null){
            load();
        }
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    @Override
    void load(){
        super.load();
        CompanyUserMapper companyUserMapper = new CompanyUserMapper();
        CompanyUser companyUserRecord = null;

        if (this.getUserId() != null) {
            companyUserRecord = companyUserMapper.findCompanyUserById(this.getUserId());
        } else if (this.getUsername() != null) {
            companyUserRecord = companyUserMapper.findCompanyUserByUsername(this.getUsername());
        }
        if (companyUserRecord != null) {
            if (this.balance == null) {
                this.balance = companyUserRecord.getBalance();
            }
            if (this.companyId == null) {
                this.companyId = companyUserRecord.getCompanyId();
            }
        }
    }
}
