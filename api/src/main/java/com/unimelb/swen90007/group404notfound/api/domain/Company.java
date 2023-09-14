package com.unimelb.swen90007.group404notfound.api.domain;

import java.math.BigDecimal;

public class Company {
    private Long companyId;
    private String companyName;
    private String category;
    private BigDecimal balance;

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }



    public BigDecimal getBalance() {
        return balance;
    }



    public  Company(Long companyId, String companyName, String category, BigDecimal balance){
        this.companyId = companyId;
        this.companyName = companyName;
        this.category = category;
        this.balance = balance;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        if (companyName==null){
            load();
        }
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCategory() {
        if (category==null){
            load();
        }
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    private void load(){

    }
}
