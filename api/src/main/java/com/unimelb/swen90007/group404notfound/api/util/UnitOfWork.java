package com.unimelb.swen90007.group404notfound.api.util;

import com.unimelb.swen90007.group404notfound.api.domain.*;
import com.unimelb.swen90007.group404notfound.api.mapper.*;

import java.util.ArrayList;
import java.util.List;

public class UnitOfWork {
    private static UnitOfWork current;
    private List<Object> newObjects = new ArrayList<Object>();
    private List<Object> dirtyObjects = new ArrayList<Object>();
    private List<Object> deletedObjects = new ArrayList<Object>();
    private static CustomerMapper customerMapper = new CustomerMapper();
    private static CompanyUserMapper companyUserMapper = new CompanyUserMapper();
    private static InvestListingMapper listingMapper = new InvestListingMapper();
    private static CompanyListingMapper companyListingMapper = new CompanyListingMapper();
    private static PortfolioMapper portfolioMapper = new PortfolioMapper();
    private static InvestListingMapper investListingMapper = new InvestListingMapper();

    private static CompanyMapper companyMapper = new CompanyMapper();
    private static ShareMapper shareMapper = new ShareMapper();

    public static void newCurrent(){
        current = new UnitOfWork();
    }

    public static UnitOfWork getCurrent() {
        return current;
    }

    public static void setCurrent(UnitOfWork current) {
        UnitOfWork.current = current;
    }

    public void registerNew(Object obj){
        newObjects.add(obj);
    }

    public void registerDirty(Object obj){
        dirtyObjects.add(obj);
    }

    public void registerDeleted(Object obj){
        if (newObjects.contains(obj)){
            newObjects.remove(obj);
            return;
        }
        dirtyObjects.remove(obj);
        if (!deletedObjects.contains(obj)){
            deletedObjects.add(obj);
        }
    }

    public boolean commit(){
        boolean result = false;
        for (Object obj : newObjects){
            if (obj instanceof Customer){
//                CustomerMapper customerMapper = new CustomerMapper();
                result = customerMapper.addCustomer((Customer) obj);
            } else if (obj instanceof CompanyUser) {
//                CompanyUserMapper companyUserMapper = new CompanyUserMapper();
                result = companyUserMapper.addCompanyUser((CompanyUser) obj);
            } else if(obj instanceof InvestListing){
//                InvestListingMapper listingMapper = new InvestListingMapper();
                result = listingMapper.addInvestListing((InvestListing) obj);
            } else if (obj instanceof CompanyListing) {
//                CompanyListingMapper companyListingMapper = new CompanyListingMapper();
                result = companyListingMapper.addCompanyListing((CompanyListing) obj);
            } else if(obj instanceof Portfolio){
//                PortfolioMapper portfolioMapper = new PortfolioMapper();
                result = portfolioMapper.addPortfolio((Portfolio) obj);
            }else if(obj instanceof Company){
//                CompanyMapper companyMapper = new CompanyMapper();
                result = companyMapper.addCompany((Company) obj);
            }else if(obj instanceof Share){
//                ShareMapper shareMapper = new ShareMapper();
                result = shareMapper.addShare((Share) obj);
            }
        }
        newObjects.clear();
        for (Object obj : dirtyObjects){
            if(obj instanceof Customer){
//                CustomerMapper userMapper = new CustomerMapper();
                result = customerMapper.setCustomer((Customer) obj);
            } else if (obj instanceof CompanyUser) {
//                CompanyUserMapper companyUserMapper = new CompanyUserMapper();
                result = companyUserMapper.setCompanyUser((CompanyUser) obj);
            } else if(obj instanceof InvestListing){
//                InvestListingMapper listingMapper = new InvestListingMapper();
                result = listingMapper.setInvestListing((InvestListing) obj);
            } else if (obj instanceof CompanyListing) {
//                CompanyListingMapper companyListingMapper = new CompanyListingMapper();
                result = companyListingMapper.setCompanyListing((CompanyListing) obj);
            } else if(obj instanceof Company){
//                CompanyMapper companyMapper = new CompanyMapper();
                result = companyMapper.setCompany((Company) obj);
            }else if(obj instanceof Share){
//                ShareMapper shareMapper = new ShareMapper();
                result = shareMapper.setShare((Share) obj);
            }
        }
        dirtyObjects.clear();

        for(Object obj: deletedObjects){
            if(obj instanceof InvestListing){
//                InvestListingMapper investListingMapper = new InvestListingMapper();
                result = investListingMapper.deleteInvestListing((InvestListing) obj);
            }else if(obj instanceof Portfolio){
//                PortfolioMapper portfolioMapper = new PortfolioMapper();
                result = portfolioMapper.deletePortfolio((Portfolio) obj);
            }else if(obj instanceof CompanyListing){
//                CompanyListingMapper companyListingMapper = new CompanyListingMapper();
                result = companyListingMapper.deleteCompanyListingById((CompanyListing) obj);
            }
        }

        return result;
    }

}
