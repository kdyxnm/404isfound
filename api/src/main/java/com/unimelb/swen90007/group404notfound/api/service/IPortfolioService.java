package com.unimelb.swen90007.group404notfound.api.service;

import com.unimelb.swen90007.group404notfound.api.domain.Portfolio;
import com.unimelb.swen90007.group404notfound.api.dto.PortfolioDetailDTO;

import java.util.List;

public interface IPortfolioService {

    boolean addPortfolio(Long uid, Long lid, Long sid, Long cid);
    List<PortfolioDetailDTO> getDetailedPortfolioByUserId(Long userId);
    Portfolio findPortfolio(Long pid);
    Long checkExisted(Long uid, Long sid);

    boolean removePortfolio(Portfolio portfolio);

}
