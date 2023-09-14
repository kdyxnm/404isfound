package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.*;
import com.unimelb.swen90007.group404notfound.api.dto.ListingDetailDTO;
import com.unimelb.swen90007.group404notfound.api.dto.PortfolioDetailDTO;
import com.unimelb.swen90007.group404notfound.api.mapper.InvestListingMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.PortfolioMapper;
import com.unimelb.swen90007.group404notfound.api.service.IPortfolioService;
import com.unimelb.swen90007.group404notfound.api.util.UnitOfWork;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class PortfolioServiceImpl implements IPortfolioService {
    PortfolioMapper portfolioMapper = new PortfolioMapper();
    private ShareServiceImpl shareService = new ShareServiceImpl();
    private CompanyServiceImpl companyService = new CompanyServiceImpl();
    private CompanyListingServiceImpl companyListingService = new CompanyListingServiceImpl();
    private InvestListingServiceImpl investListingService = new InvestListingServiceImpl();

    @Override
    public boolean addPortfolio(Long uid, Long lid, Long sid, Long cid) {
        Portfolio portfolio = new Portfolio(null, uid, lid, sid, cid);
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerNew(portfolio);
        return UnitOfWork.getCurrent().commit();
    }


    @Override
    public Portfolio findPortfolio(Long pid) {
        return portfolioMapper.findPortById(pid);
    }

    @Override
    public Long checkExisted(Long uid, Long sid) {
        return  portfolioMapper.findExistByuidAndsid(uid, sid);
    }

    @Override
    public boolean removePortfolio(Portfolio portfolio) {
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerDeleted(portfolio);
        return UnitOfWork.getCurrent().commit();
    }

    public List<Portfolio> getPortfoliosByUserId(Long userId) {
        return portfolioMapper.findAllPortfolioIdByUserId(userId);
    }

    public List<PortfolioDetailDTO> getDetailedPortfolioByUserId(Long userId) {
        List<Portfolio> portfolios = getPortfoliosByUserId(userId);// portfolios for 1 customer
        List<PortfolioDetailDTO> portfolioDetails = new ArrayList<>();

        for (Portfolio portfolio : portfolios) {
            PortfolioDetailDTO detail = new PortfolioDetailDTO();

            Share share = shareService.getShareById(portfolio.getShareId());
            Company company = companyService.getCompanyById(share.getCompanyId());

            ListingDetailDTO investListing = investListingService.getListingByCompanyNameAndType(company.getCompanyName(), share.getShareType());
            if(companyListingService.calculateProfitForListing(investListing, detail)) {
                portfolioDetails.add(detail);
            }
        }
        return portfolioDetails;
    }

}
