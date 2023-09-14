package com.unimelb.swen90007.group404notfound.api.filter;


import com.unimelb.swen90007.group404notfound.api.annotation.LoginToken;
import com.unimelb.swen90007.group404notfound.api.annotation.NormalToken;
import com.unimelb.swen90007.group404notfound.api.domain.User;
import com.unimelb.swen90007.group404notfound.api.util.JwtUtil;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.lang.reflect.Method;

import static com.unimelb.swen90007.group404notfound.api.servlet.MethodManagement.getMethodByURI;

public class AuthenticationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
        System.out.println("EncodeFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        Method targetMethod = getMethodByURI(requestURI);

        // Get JWT token from the header of http
        String token = request.getHeader("Authorization");

        if (targetMethod.isAnnotationPresent(LoginToken.class)) {
            // if existing @LoginToken, check if the user did not log in
            if (token != null && !token.isEmpty()) {
                // if jwt token is not empty
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "The user has already login");
                return;
            }
        } else if (targetMethod.isAnnotationPresent(NormalToken.class)) {
            // if existing @NormalToken，check whether the user has the token
            if (token == null || token.isEmpty()) {
                // if jwt token is empty
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authorization header is missing");
                return;
            }

            // decrypt jwt token and validate
            User user = (User) request.getSession().getAttribute("currentUser");
            boolean isValid = JwtUtil.isVerify(token, user);

            if (!isValid) {
                // token is invalid
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
                return;
            }
        }
        // token pass
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Authentication Filter destroy");
    }
}
