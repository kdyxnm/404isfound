package com.unimelb.swen90007.group404notfound.api.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class MethodManagement {
    public static Map<String, Method> methodMap = new HashMap<>();

    static {
        try {
            methodMap.put("/api_war_exploded/login", LoginController.class.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/viewCustomers", AdminViewAllCustomerController.class.getMethod("doGet", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/purchase", PurchaseController.class.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/portfolioDetails", PortfolioDetailsController.class.getMethod("doGet", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/register", RegisterController.class.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/viewCompanies", ViewCompaniesController.class.getMethod("doGet", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/viewCompanyListings", ViewCompanyListingController.class.getMethod("doGet", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/addCompany", AddCompanyController.class.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/addCompanyListing", AddCompanyListingController.class.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/addShare", AddShareController.class.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/sell", SellController.class.getMethod("doPost", HttpServletRequest.class, HttpServletResponse.class));
            methodMap.put("/api_war_exploded/deleteCompanyListing", DeleteCompanyListingController.class.getMethod("doDelete", HttpServletRequest.class, HttpServletResponse.class));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
    public static Method getMethodByURI(String uri) {
        return methodMap.get(uri);
    }
}
