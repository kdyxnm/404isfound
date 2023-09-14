package com.unimelb.swen90007.group404notfound.api.service;

import com.unimelb.swen90007.group404notfound.api.domain.User;

public interface IUserService {

    boolean addUser(User user);
    User loginUser(String username, String password, String roleType);
    boolean checkUserExist(String username, String roleType);

}
