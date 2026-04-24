package com.registerlogin.utils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

public class SessionUtil {
    public static void setUserSession(HttpServletRequest req,Object user){
        HttpSession session=req.getSession();
        session.setAttribute("User",user);
    }
}