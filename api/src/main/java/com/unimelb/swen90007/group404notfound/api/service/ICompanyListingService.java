package com.unimelb.swen90007.group404notfound.api.service;

import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.domain.CompanyListing;
import com.unimelb.swen90007.group404notfound.api.dto.ListingDetailDTO;
import com.unimelb.swen90007.group404notfound.api.dto.PortfolioDetailDTO;

import java.math.BigDecimal;
import java.util.List;

public interface ICompanyListingService {
    Long sell(Long listingid, Integer amount);
    List<ListingDetailDTO> findAllCompanyListing();
    BigDecimal totalPrice(Long lid, int amount);
    CompanyListing addCompanyListing(Long shareId, Integer numShare);
    boolean deleteCompanyListing(Long listingId);
    Long getShareIdByListingId(Long listingId);
    boolean calculateProfitForListing(ListingDetailDTO investListing, PortfolioDetailDTO detailDTO);
    List<ListingDetailDTO> findCompanyListingByCompanyId(Long companyId);

    BigDecimal getTotalPrice(Long sid, Long cid,Integer amount);
    boolean addNumShareToCompanyListing(Long sid, Long cid, Integer amount);
}
