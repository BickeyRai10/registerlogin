package com.registerlogin.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.xml.crypto.dsig.spec.XPathType;
import java.io.IOException;
//@WebFilter("/all-topic")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest) servletRequest;
        HttpServletResponse resp=(HttpServletResponse) servletResponse;

        HttpSession session=req.getSession();
        if(session!=null && session.getAttribute("user")!=null){
            filterChain.doFilter(req,resp);
        }else{
            resp.sendRedirect(req.getContextPath()+"/login");
        }
    }

}
