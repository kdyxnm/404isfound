package com.unimelb.swen90007.group404notfound.api.servlet;

import com.unimelb.swen90007.group404notfound.api.annotation.LoginToken;
import com.unimelb.swen90007.group404notfound.api.domain.CompanyUser;
import com.unimelb.swen90007.group404notfound.api.domain.Customer;
import com.unimelb.swen90007.group404notfound.api.domain.User;
import com.unimelb.swen90007.group404notfound.api.service.impl.UserServiceImpl;
import com.unimelb.swen90007.group404notfound.api.util.JwtUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {

    @LoginToken
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        JsonObject jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        String username = jsonObject.get("username").getAsString();
        String password = jsonObject.get("password").getAsString();
        String roleType = jsonObject.get("roletype").getAsString();

        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.loginUser(username, password, roleType);

        JsonObject jsonResponse = new JsonObject();
        if (user != null) {
            // if login successfully
            // 24 hrs token expired
            long oneDayInMillis = 24 * 60 * 60 * 1000;
            String jwtToken = JwtUtil.createJwt(oneDayInMillis, user);

            // put JWT Token in response
            jsonResponse.addProperty("token", jwtToken);
            req.getSession().setAttribute("currentUser", user);
            jsonResponse.addProperty("status", "success");
            jsonResponse.addProperty("message", "Login successful");
            jsonResponse.addProperty("userId", user.getUserId());
            jsonResponse.addProperty("roletype", roleType);
            if(user instanceof Customer){
                jsonResponse.addProperty("balance", ((Customer) user).getBalance());
            }else if(user instanceof CompanyUser){
                jsonResponse.addProperty("balance", ((CompanyUser) user).getBalance());
                jsonResponse.addProperty("companyid", ((CompanyUser) user).getCompanyId());
            }
        } else {
            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "Invalid username or password");
        }
        resp.getWriter().write(jsonResponse.toString());
    }
}