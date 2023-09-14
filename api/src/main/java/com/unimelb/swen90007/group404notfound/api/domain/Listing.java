package com.unimelb.swen90007.group404notfound.api.domain;

import com.unimelb.swen90007.group404notfound.api.mapper.CompanyListingMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.CompanyUserMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.InvestListingMapper;

public abstract class Listing {
    private Long listingId;
    private Long shareId;
    private Integer numShare;


    public Listing(Long id, Long shareid, Integer numshare) {
        this.listingId = id;
        this.shareId = shareid;
        this.numShare = numshare;

    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }

    public Long getShareId() {
        if(this.shareId == null){
            load();
        }
        return this.shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Integer getNumShare() {
        if(this.numShare == null){
            load();
        }
        return numShare;
    }

    public void setNumShare(Integer numShare) {
        this.numShare = numShare;
    }

    private void load(){
        Listing listingRecord = null;
        if(this instanceof  InvestListing){
            InvestListingMapper listingMapper = new InvestListingMapper();
            listingRecord = listingMapper.findListingById(this.listingId);
        }else{
            CompanyListingMapper companyListingMapper = new CompanyListingMapper();
            listingRecord = companyListingMapper.findListingById(this.listingId);
        }

        if(this.shareId == null){
            this.shareId = listingRecord.getShareId();
        }
        if(this.numShare == null){
            this.numShare = listingRecord.getNumShare();
        }
    }
}
