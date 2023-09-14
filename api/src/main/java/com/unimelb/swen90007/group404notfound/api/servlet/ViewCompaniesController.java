package com.unimelb.swen90007.group404notfound.api.servlet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyService;
import com.unimelb.swen90007.group404notfound.api.service.impl.CompanyServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/viewCompanies")
public class ViewCompaniesController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        JsonArray companyArray = new JsonArray();
        ICompanyService companyService = new CompanyServiceImpl();
        List<Company> companies = companyService.findAllCompany();
        for (Company company:companies){
            JsonObject companyObject = new JsonObject();
            companyObject.addProperty("userid", company.getCompanyId());
            companyObject.addProperty("username", company.getCompanyName());
            companyArray.add(companyObject);
        }
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("status", "success");
        jsonResponse.add("companies", companyArray);
        try {
            resp.getWriter().write(jsonResponse.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
