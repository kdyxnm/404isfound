package com.unimelb.swen90007.group404notfound.api.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.unimelb.swen90007.group404notfound.api.annotation.NormalToken;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyListingService;
import com.unimelb.swen90007.group404notfound.api.service.IShareService;
import com.unimelb.swen90007.group404notfound.api.service.impl.CompanyListingServiceImpl;
import com.unimelb.swen90007.group404notfound.api.service.impl.ShareServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

//ToDo
@WebServlet("/deleteCompanyListing")
public class DeleteCompanyListingController extends HttpServlet {
    @NormalToken
    @Override
    public void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        JsonObject jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        Long conpanyListingId = jsonObject.get("listingid").getAsLong();
        ICompanyListingService companyListingService = new CompanyListingServiceImpl();
        boolean result = companyListingService.deleteCompanyListing(conpanyListingId);
        JsonObject resultObject = new JsonObject();
        if (result){
            resultObject.addProperty("status", "success");
        }
        else{
            resultObject.addProperty("status", "fail");
            resultObject.addProperty("message", "delete fail");
        }
        resp.getWriter().write(resultObject.toString());
    }
}
