package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.mapper.ShareMapper;
import com.unimelb.swen90007.group404notfound.api.service.IShareService;
import com.unimelb.swen90007.group404notfound.api.util.UnitOfWork;

import java.math.BigDecimal;

public class ShareServiceImpl implements IShareService {
    private ShareMapper shareMapper = new ShareMapper();
    @Override
    public Long getCompanyIdbyId(Long sid) {

        return shareMapper.findShareById(sid).getCompanyId();
    }

    public Share getShareById(Long shareId) {
        return shareMapper.findShareById(shareId);
    }

    @Override
    public Share addShare(Long companyId, Character shareType, BigDecimal price) {
        Share share = new Share(null, companyId, shareType, price);
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerNew(share);
        boolean result = UnitOfWork.getCurrent().commit();
        if (result){
            return share;
        }else{
            return null;
        }
    }

}
