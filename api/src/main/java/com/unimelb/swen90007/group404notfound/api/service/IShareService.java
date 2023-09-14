package com.unimelb.swen90007.group404notfound.api.service;

import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.domain.Share;

import java.math.BigDecimal;

public interface IShareService {
    public Long getCompanyIdbyId(Long sid);
    Share addShare(Long companyid, Character shareType, BigDecimal price);
}
