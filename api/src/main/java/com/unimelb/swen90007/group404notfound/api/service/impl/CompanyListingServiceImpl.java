package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.domain.CompanyListing;
import com.unimelb.swen90007.group404notfound.api.domain.Listing;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.dto.ListingDetailDTO;
import com.unimelb.swen90007.group404notfound.api.dto.PortfolioDetailDTO;
import com.unimelb.swen90007.group404notfound.api.mapper.CompanyListingMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.ShareMapper;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyListingService;
import com.unimelb.swen90007.group404notfound.api.util.UnitOfWork;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class CompanyListingServiceImpl implements ICompanyListingService {
    private CompanyListingMapper companyListingMapper = new CompanyListingMapper();
    private ShareMapper shareMapper = new ShareMapper();

    @Override
    public CompanyListing addCompanyListing(Long shareId, Integer numShare) {

        CompanyListing companyListing = new CompanyListing(null, shareId, numShare);

        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerNew(companyListing);

        boolean result = UnitOfWork.getCurrent().commit();
        if (result){
            return companyListing;
        }else{
            return null;
        }
    }

    @Override
    public boolean deleteCompanyListing(Long listingId) {
        UnitOfWork.newCurrent();
        CompanyListing companyListing = new CompanyListing(listingId, null, null);
        UnitOfWork.getCurrent().registerDeleted(companyListing);
        boolean result = UnitOfWork.getCurrent().commit();
        return result;
    }

    @Override
    public Long getShareIdByListingId(Long listingId) {
        return companyListingMapper.getShareIdByListingId(listingId);
    }

    @Override
    public Long sell(Long listingid, Integer amount) {
        UnitOfWork.newCurrent();

        Listing listing = companyListingMapper.findListingById(listingid);
        Long sid = listing.getShareId();
        if(listing.getNumShare()!=null && amount!= null &&  listing.getNumShare()>=amount ){
            listing.setNumShare(listing.getNumShare() - amount);
            UnitOfWork.getCurrent().registerDirty(listing);
            UnitOfWork.getCurrent().commit();
            return sid;
        }else{
            return  -1L;
        }
    }

    @Override
    public List<ListingDetailDTO> findAllCompanyListing() {
        return companyListingMapper.findAllCompanyListingDetail();
    }



    @Override
    public BigDecimal totalPrice(Long lid, int amount) {
        Listing listing = companyListingMapper.findListingById(lid);
        Long sid = listing.getShareId();


        Share share = shareMapper.findShareById(sid);
        BigDecimal price = share.getPrice();
        return  price.multiply(new BigDecimal(amount));
    }

    @Override
    public boolean calculateProfitForListing(ListingDetailDTO investListing, PortfolioDetailDTO detailDTO) {
        BigDecimal profit = companyListingMapper.getProfit(investListing);
        detailDTO.setShareType(investListing.getShareType());
        detailDTO.setCompanyName(investListing.getCompanyName());
        detailDTO.setNumShare(investListing.getNumShare());
        detailDTO.setPrice(investListing.getPrice());
        detailDTO.setProfit(profit);
        return true;
    }

    @Override
    public BigDecimal getTotalPrice(Long sid, Long cid, Integer amount) {
        Share Customershare = shareMapper.findShareById(sid);
        Character type = Customershare.getShareType();
        Share companyShare = null;
        List<CompanyListing> companyListings = companyListingMapper.findAllCompanyListingByCompanyId(cid);
        boolean findShare = false;
        for(CompanyListing companyListing: companyListings){
            Share currentShare = shareMapper.findShareById(companyListing.getShareId());
            if(currentShare.getShareType() == type){
                findShare = true;
                companyShare = currentShare;
                break;
            }
        }
        if (!findShare){
            return BigDecimal.valueOf(-1);
        }
        return companyShare.getPrice().multiply(BigDecimal.valueOf(amount));


    }

    @Override
    public boolean addNumShareToCompanyListing(Long sid, Long cid, Integer amount) {
        Share Customershare = shareMapper.findShareById(sid);
        Character type = Customershare.getShareType();
        CompanyListing companyListing = null;
        boolean findShare = false;
        List<CompanyListing> companyListings = companyListingMapper.findAllCompanyListingByCompanyId(cid);
        for (CompanyListing currentListing : companyListings) {
            Share currentShare = shareMapper.findShareById(currentListing.getShareId());
            if (currentShare.getShareType() == type) {
                findShare = true;
                companyListing = currentListing;
                break;
            }
        }
        if (!findShare) {
            return false;
        }
        companyListing.setNumShare(companyListing.getNumShare() + amount);
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerDirty(companyListing);
        return UnitOfWork.getCurrent().commit();
    }
    public List<ListingDetailDTO> findCompanyListingByCompanyId(Long companyId) {
        return companyListingMapper.findCompanyListingDetailById(companyId);

    }


}
