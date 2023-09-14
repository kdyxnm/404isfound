package com.unimelb.swen90007.group404notfound.api.domain;

public class Portfolio {
    private Long portfolioId;
    private Long userId;
    private Long listingId;
    private Long shareId;
    private Long companyId;
    public Portfolio(Long portfolioId, Long userId, Long listingId, Long shareId, Long companyId) {
        this.portfolioId = portfolioId;
        this.userId = userId;
        this.listingId = listingId;
        this.shareId = shareId;
        this.companyId = companyId;
    }
    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getListingId() {
        return listingId;
    }

    public void setListingId(Long listingId) {
        this.listingId = listingId;
    }



    public Long getShareId() {
        return shareId;
    }

    public void setShareId(Long shareId) {
        this.shareId = shareId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }



    public Portfolio(Long portfolioId) {
        this.portfolioId = portfolioId;
    }


}
