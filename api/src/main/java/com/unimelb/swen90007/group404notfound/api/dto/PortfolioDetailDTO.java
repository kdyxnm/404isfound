package com.unimelb.swen90007.group404notfound.api.dto;

import java.math.BigDecimal;

public class PortfolioDetailDTO {
    private Character shareType;
    private String companyName;
    private int numShare;
    private BigDecimal price;
    private BigDecimal profit;

    public PortfolioDetailDTO() {}

    public Character getShareType() {
        return shareType;
    }

    public void setShareType(Character shareType) {
        this.shareType = shareType;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public BigDecimal getProfit() {
        return profit;
    }

    public void setProfit(BigDecimal profit) {
        this.profit = profit;
    }
}
