package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.InvestListing;
import com.unimelb.swen90007.group404notfound.api.domain.Listing;
import com.unimelb.swen90007.group404notfound.api.dto.ListingDetailDTO;
import com.unimelb.swen90007.group404notfound.api.mapper.InvestListingMapper;
import com.unimelb.swen90007.group404notfound.api.service.IInvestListingService;
import com.unimelb.swen90007.group404notfound.api.util.UnitOfWork;


public class InvestListingServiceImpl implements IInvestListingService {
    InvestListingMapper investListingMapper = new InvestListingMapper();
    @Override
    public Listing addListing(Long sid, Integer amount) {
        UnitOfWork.newCurrent();
        Listing listing = new InvestListing(1L,sid, amount);
        UnitOfWork.getCurrent().registerNew(listing);
        UnitOfWork.getCurrent().commit();
        return listing;
    }

    @Override
    public int removeOrUpdate(Long lid, Integer amount) {
        InvestListing listing = (InvestListing) investListingMapper.findListingById(lid);
        Integer numShare = listing.getNumShare();
        if(numShare < amount){
            return -1;
        } else if (numShare > amount) {
            //Update numShare
            UnitOfWork.newCurrent();
            listing.setNumShare(numShare - amount);
            UnitOfWork.getCurrent().registerDirty(listing);
            UnitOfWork.getCurrent().commit();
            return 1;
        }else{
            return 0;
        }
    }

    @Override
    public boolean updateNumShare(Long lid, Integer amount) {
        UnitOfWork.newCurrent();
        Listing curListing = investListingMapper.findListingById(lid);
        curListing.setNumShare(curListing.getNumShare()+amount);
        UnitOfWork.getCurrent().registerDirty(curListing);
        return UnitOfWork.getCurrent().commit();

    }

    @Override
    public ListingDetailDTO getListingByCompanyNameAndType(String cname, Character type) {
        return investListingMapper.getListingByCompanyNameAndType(cname, type);
    }

    @Override
    public boolean removeInvestListing(Long lid) {
        InvestListing investListing = (InvestListing) investListingMapper.findListingById(lid);
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerDeleted(investListing);
        return UnitOfWork.getCurrent().commit();

    }


}
