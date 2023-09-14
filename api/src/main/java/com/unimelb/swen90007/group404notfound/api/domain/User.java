package com.unimelb.swen90007.group404notfound.api.domain;

import com.unimelb.swen90007.group404notfound.api.mapper.AdminMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.CompanyUserMapper;
import com.unimelb.swen90007.group404notfound.api.mapper.CustomerMapper;

public abstract class User {

    public User(Long userId, String username, String password, String lastname, String firstname) {
        this.username = username;
        this.password = password;
        this.lastname = lastname;
        this.firstname = firstname;
        this.userId = userId;
    }

    private Long userId;
    private String username;
    private String password;
    private String lastname;
    private String firstname;





    public String getUsername() {
        if(this.username == null){
            load();
        }
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        if (this.password == null){
            load();
        }
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLastname() {
        if (this.lastname==null){
            load();
        }
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        if (this.firstname==null){
            load();
        }
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Long getUserId() {
        if (this.userId==null){
            load();
        }
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                '}';
    }
    // lazy load: ghost implementation
    void load(){
        User userRecord = null;
        if(this instanceof Customer) {
            CustomerMapper customerMapper = new CustomerMapper();
            if (this.userId == null && this.username != null) {
                customerMapper.findCustomerByUsername(this.username);
            }
            userRecord = customerMapper.findCustomerById(this.userId);
        } else if (this instanceof Admin) {
            AdminMapper adminMapper = new AdminMapper();
            if (this.userId == null && this.username != null) {
                adminMapper.findAdminByUsername(this.username);
            }
            userRecord = adminMapper.findAdminById(this.userId);
        } else{
            CompanyUserMapper companyUserMapper = new CompanyUserMapper();
            if (this.userId == null && this.username != null) {
                companyUserMapper.findCompanyUserByUsername(this.username);
            }
            userRecord = companyUserMapper.findCompanyUserById(this.userId);
        }
        if  (this.firstname==null){
            this.firstname = userRecord.getFirstname();
        }
        if  (this.lastname==null){
            this.lastname = userRecord.getLastname();
        }
        if  (this.username==null){
            this.username = userRecord.getUsername();
        }
        if  (this.password==null){
            this.password = userRecord.getPassword();
        }


    }

}
