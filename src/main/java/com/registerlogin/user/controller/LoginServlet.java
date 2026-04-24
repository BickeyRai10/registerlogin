package com.registerlogin.user.controller;

import com.registerlogin.user.dao.UserDAO;
import com.registerlogin.user.model.UserModel;
import com.registerlogin.utils.SessionUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    UserDAO userDAO=new UserDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        UserModel user=userDAO.getUSerDetail(email);
        if(user == null){
            req.setAttribute("error", "Email not found");
            req.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(req,resp);
        }
        if(!BCrypt.checkpw(password,user.getPassword())){
            req.setAttribute("error", "Incorrect Password");
            req.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(req,resp);
        }
        req.setAttribute("user",user);
        req.getRequestDispatcher("/WEB-INF/Pages/index.jsp").forward(req,resp);

        SessionUtil.setUserSession(req,user);
        Cookie cookie=new Cookie("email",email);
        resp.addCookie(cookie);

        resp.sendRedirect(req.getContextPath()+"/");
    }

}

