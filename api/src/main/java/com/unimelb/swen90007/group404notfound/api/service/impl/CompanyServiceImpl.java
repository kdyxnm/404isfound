package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.mapper.CompanyMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.ShareMapper;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyService;
import com.unimelb.swen90007.group404notfound.api.util.UnitOfWork;

import java.math.BigDecimal;
import java.util.List;

public class CompanyServiceImpl implements ICompanyService {
    private CompanyMapper companyMapper = new CompanyMapper();
    private ShareMapper shareMapper = new ShareMapper();

    @Override
    public Company addCompany(String companyName, String category) {
        Company company = new Company(null, companyName, category, null);
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerNew(company);
        boolean result = UnitOfWork.getCurrent().commit();
        if (result){
            return company;
        }else{
            return null;
        }
    }

    @Override
    public boolean checkCompanyExist(String username) {
        if (companyMapper.findCompanyByName(username)!=null){
            return true;
        }
        return false;
    }

    @Override
    public boolean earn(Long cid, Long sid, Integer amount) {
        UnitOfWork.newCurrent();
        Company company = companyMapper.findCompanyById(cid);
        Share share = shareMapper.findShareById(sid);
        BigDecimal totalEarn = share.getPrice().multiply(new BigDecimal(amount));
        company.setBalance(company.getBalance().add(totalEarn));
        UnitOfWork.getCurrent().registerDirty(company);
        UnitOfWork.getCurrent().commit();
        return true;
    }

    @Override
    public List<Company> findAllCompany() {
        return companyMapper.findAllCompany();
    }

    @Override
    public boolean addBalance(Long cid, BigDecimal totalPrice) {
        Company company = companyMapper.findCompanyById(cid);
        company.setBalance(company.getBalance().subtract(totalPrice));
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerDirty(company);
        return UnitOfWork.getCurrent().commit();
    }

    public Company getCompanyById(Long companyId) {
        return companyMapper.findCompanyById(companyId);
    }
}
