package com.unimelb.swen90007.group404notfound.api.service;

import com.unimelb.swen90007.group404notfound.api.domain.Company;

import java.math.BigDecimal;
import java.util.List;

public interface ICompanyService {
    Company addCompany(String companyName, String category);
    boolean checkCompanyExist(String username);
    boolean earn(Long cid, Long sid, Integer amount);
    List<Company> findAllCompany();
    boolean addBalance(Long cid, BigDecimal totalPrice);
}
