package com.unimelb.swen90007.group404notfound.api.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.unimelb.swen90007.group404notfound.api.annotation.NormalToken;
import com.unimelb.swen90007.group404notfound.api.domain.Customer;
import com.unimelb.swen90007.group404notfound.api.domain.User;
import com.unimelb.swen90007.group404notfound.api.service.ICustomerService;
import com.unimelb.swen90007.group404notfound.api.service.impl.CustomerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewCustomers")
public class AdminViewAllCustomerController extends HttpServlet {

    @NormalToken
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        JsonArray userArray = new JsonArray();
        ICustomerService customerService = new CustomerServiceImpl();
        List<Customer> customers = customerService.viewAllCustomer();

        for(User user: customers){
            JsonObject customerObject = new JsonObject();
            customerObject.addProperty("userid", user.getUserId());
            customerObject.addProperty("username", user.getUsername());
            userArray.add(customerObject);
        }

        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("status", "success");
        jsonResponse.add("customers", userArray);

        try {
            resp.getWriter().write(jsonResponse.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
