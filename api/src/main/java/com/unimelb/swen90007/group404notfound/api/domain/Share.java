package com.unimelb.swen90007.group404notfound.api.domain;

import com.unimelb.swen90007.group404notfound.api.mapper.ShareMapper;

import java.math.BigDecimal;

public class Share {

    private Long id;
    private Long companyId;
    private Character shareType;

    private BigDecimal price;

    public Share(Long id, Long companyId, Character shareType, BigDecimal price) {
        this.id = id;
        this.companyId = companyId;
        this.shareType = shareType;
        this.price = price;
    }

    public Share(Long id){
        this.id = id;
        this.companyId = null;
        this.price = null;
        this.shareType = null;
    }

    //TODO: should be get from listing
    public Long getId() {
        return id;

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        if(companyId == null){
            load();
        }
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Character getShareType() {
        if(shareType == null){
            load();
        }
        return  shareType;
    }

    public void setShareType(Character shareType) {
        this.shareType = shareType;
    }

    public BigDecimal getPrice() {
        if(price == null){
            load();
        }
        return price;
    }



    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    private void load(){
        ShareMapper mapper = new ShareMapper();
        Share share = mapper.findShareById(id);
        this.id= share.id;
        this.shareType = share.shareType;
        this.companyId = share.companyId;
        this.price = share.getPrice();
    }


}
