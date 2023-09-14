package com.unimelb.swen90007.group404notfound.api.service.impl;

import com.unimelb.swen90007.group404notfound.api.domain.User;
import com.unimelb.swen90007.group404notfound.api.mapper.AdminMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.CompanyUserMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.CustomerMapper;
import com.unimelb.swen90007.group404notfound.api.service.IUserService;
import com.unimelb.swen90007.group404notfound.api.util.UnitOfWork;

public class UserServiceImpl implements IUserService {
    private CustomerMapper customerMapper = new CustomerMapper();
    private CompanyUserMapper companyUserMapper = new CompanyUserMapper();
    private AdminMapper adminMapper = new AdminMapper();

    @Override
    public boolean addUser(User user) {
        UnitOfWork.newCurrent();
        UnitOfWork.getCurrent().registerNew(user);
        boolean result = UnitOfWork.getCurrent().commit();
        return result;
    }

    @Override
    public User loginUser(String username, String password, String roleType) {
        User user = null;
        if (roleType.equals("User")){
            user = customerMapper.findCustomerByUsername(username);
        } else if (roleType.equals("Admin")) {
            user = adminMapper.findAdminByUsername(username);
        } else if (roleType.equals("Company")) {
            user = companyUserMapper.findCompanyUserByUsername(username);
        }
        if ( user!=null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    @Override
    public boolean checkUserExist(String username, String roleType) {
        boolean result = false;
        if (roleType.equals("User")){
            result = customerMapper.findCustomerByUsername(username)!=null;
        } else if (roleType.equals("Company")) {
            result = companyUserMapper.findCompanyUserByUsername(username)!=null;
        }
        return result;
    }
}
