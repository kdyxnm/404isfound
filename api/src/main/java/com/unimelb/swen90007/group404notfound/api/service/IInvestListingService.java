package com.unimelb.swen90007.group404notfound.api.service;

import com.unimelb.swen90007.group404notfound.api.domain.InvestListing;
import com.unimelb.swen90007.group404notfound.api.domain.Listing;
import com.unimelb.swen90007.group404notfound.api.dto.ListingDetailDTO;

import java.math.BigDecimal;
import java.util.List;

public interface IInvestListingService {
    Listing addListing(Long sid, Integer amount);

    int removeOrUpdate(Long lid, Integer amount);

    boolean updateNumShare(Long lid, Integer amount);

    ListingDetailDTO getListingByCompanyNameAndType(String cname, Character type);
    boolean removeInvestListing(Long lid);
}
