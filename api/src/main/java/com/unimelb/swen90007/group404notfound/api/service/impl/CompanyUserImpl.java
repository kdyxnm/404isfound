package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.CompanyUser;
import com.unimelb.swen90007.group404notfound.api.domain.User;
import com.unimelb.swen90007.group404notfound.api.mapper.CompanyUserMapper;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyUserService;

public class CompanyUserImpl implements ICompanyUserService{
//    private CompanyMapper companyMapper = new CompanyMapper();
    private CompanyUserMapper companyUserMapper = new CompanyUserMapper();
//
//    public boolean addUser(CompanyUser companyUser, Company company) {
//        UnitOfWork.newCurrent();
//        boolean result = false;
//        if (companyMapper.findCompanyByName(company.getCompanyName())==null){
//            UnitOfWork.getCurrent().registerNew(company);
//        }
//        UnitOfWork.getCurrent().registerNew(companyUser);
//        return UnitOfWork.getCurrent().commit();
//    }
}
