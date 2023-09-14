package com.unimelb.swen90007.group404notfound.api.domain;

import java.math.BigDecimal;

public class Admin extends User{


    public Admin(Long userId, String username, String password, String lastname, String firstname) {
        super(userId, username, password, lastname, firstname);
    }
}
