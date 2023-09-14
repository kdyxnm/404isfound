package com.unimelb.swen90007.group404notfound.api.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.unimelb.swen90007.group404notfound.api.domain.Company;
import com.unimelb.swen90007.group404notfound.api.domain.CompanyListing;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.mapper.ShareMapper;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyListingService;
import com.unimelb.swen90007.group404notfound.api.service.ICompanyService;
import com.unimelb.swen90007.group404notfound.api.service.impl.CompanyListingServiceImpl;
import com.unimelb.swen90007.group404notfound.api.service.impl.CompanyServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

//ToDo
@WebServlet("/addCompanyListing")
public class AddCompanyListingController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        Long shareId = jsonObject.get("shareId").getAsLong();
        Integer numShare = jsonObject.get("numShare").getAsInt();
        ICompanyListingService companyListingService = new CompanyListingServiceImpl();
        ShareMapper shareMapper = new ShareMapper();
        Share existingShare = shareMapper.findShareById(shareId);
        JsonObject companyListingObject = new JsonObject();

        if(existingShare != null){
            CompanyListing companyListing = companyListingService.addCompanyListing(shareId, numShare);

            if (companyListing!=null){
                companyListingObject.addProperty("status", "success");
                companyListingObject.addProperty("companyListingId", companyListing.getListingId());
            }
            else{
                companyListingObject.addProperty("status", "fail");
                companyListingObject.addProperty("message", "add fail");
            }
        }else{
            companyListingObject.addProperty("status", "fail");
            companyListingObject.addProperty("message", "Already exist");
        }


        resp.getWriter().write(companyListingObject.toString());
    }
}
