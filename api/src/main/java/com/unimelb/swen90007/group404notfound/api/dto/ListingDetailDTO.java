package com.unimelb.swen90007.group404notfound.api.dto;

import java.math.BigDecimal;

public class ListingDetailDTO {
    private Long id;
    private Long shareId;
    private int numShare;
    private BigDecimal price;
    private String companyName;
    private Character shareType;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Character getShareType() {
        return shareType;
    }

    public void setShareType(Character shareType) {
        this.shareType = shareType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public int getNumShare() {
        return numShare;
    }

    public void setNumShare(int numShare) {
        this.numShare = numShare;
    }


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}

