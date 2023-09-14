package com.unimelb.swen90007.group404notfound.api.servlet;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.unimelb.swen90007.group404notfound.api.annotation.NormalToken;
import com.unimelb.swen90007.group404notfound.api.dto.PortfolioDetailDTO;
import com.unimelb.swen90007.group404notfound.api.service.IPortfolioService;
import com.unimelb.swen90007.group404notfound.api.service.impl.PortfolioServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/portfolioDetails")
public class PortfolioDetailsController extends HttpServlet {
    IPortfolioService portofolioService = new PortfolioServiceImpl();

    @NormalToken
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject jsonResponse = new JsonObject();

        String userIdParam = req.getParameter("userId");
        if (userIdParam == null || userIdParam.isEmpty()) {
            jsonResponse.addProperty("status", "fail");
            jsonResponse.addProperty("message", "userId required");
            resp.getWriter().write(jsonResponse.toString());
            return;
        }

        Long userId = Long.parseLong(userIdParam);
        List<PortfolioDetailDTO> details = portofolioService.getDetailedPortfolioByUserId(userId);

        JsonArray portfolioArray = new JsonArray();
        for (PortfolioDetailDTO detail : details) {
            JsonObject portfolioObject = new JsonObject();

            portfolioObject.addProperty("shareType", detail.getShareType());
            portfolioObject.addProperty("companyName", detail.getCompanyName());
            portfolioObject.addProperty("numShare", detail.getNumShare());
            portfolioObject.addProperty("price", detail.getPrice());
            portfolioObject.addProperty("profit", detail.getProfit());
            portfolioArray.add(portfolioObject);
        }

        jsonResponse.addProperty("status", "success");
        jsonResponse.add("portfolioDetails", portfolioArray);
        resp.getWriter().write(jsonResponse.toString());
    }

}
