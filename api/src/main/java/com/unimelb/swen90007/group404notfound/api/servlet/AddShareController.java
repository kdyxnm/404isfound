package com.unimelb.swen90007.group404notfound.api.servlet;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.unimelb.swen90007.group404notfound.api.domain.CompanyListing;
import com.unimelb.swen90007.group404notfound.api.domain.Share;
import com.unimelb.swen90007.group404notfound.api.mapper.ShareMapper;
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
import java.math.BigDecimal;

//ToDo
@WebServlet("/addShare")
public class AddShareController extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader bufferedReader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject jsonObject = new JsonParser().parse(stringBuilder.toString()).getAsJsonObject();
        Long companyId = jsonObject.get("companyId").getAsLong();
        Character shareType = jsonObject.get("shareType").getAsCharacter();
        BigDecimal price = jsonObject.get("price").getAsBigDecimal();

        IShareService shareService = new ShareServiceImpl();
        JsonObject shareObject = new JsonObject();

        Share share = shareService.addShare(companyId, shareType, price);
        if (share!=null){
            shareObject.addProperty("status", "success");
            shareObject.addProperty("companyListingId", share.getId());
        }
        else{
            shareObject.addProperty("status", "fail");
            shareObject.addProperty("message", "add fail");
        }


        resp.getWriter().write(shareObject.toString());
    }
}
