package com.registerlogin.user.controller;

import com.registerlogin.user.dao.UserDAO;
import com.registerlogin.user.model.UserModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;


public class RegisterServlet extends HttpServlet {
    UserDAO userDAO=new UserDAO();
    String registerURL="/WEB-INF/Pages/register.jsp";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(registerURL).forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        String salt= BCrypt.gensalt(12);
        String hashPassword= BCrypt.hashpw(password,salt);

        UserModel user=new UserModel();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(hashPassword);

        if(name.isEmpty()||email.isEmpty()||password.isEmpty()){
            req.setAttribute("error","Please fill all the fields");
            req.getRequestDispatcher(registerURL).forward(req,resp);
            return;
        }
        boolean isEmailExist= userDAO.isEmailExists(email);
        if(isEmailExist){
            req.setAttribute("error","Email already exit");
            req.getRequestDispatcher(registerURL).forward(req,resp);
            return;
        }
        boolean isUserCreated=userDAO.insertUser(user);
        if(isUserCreated){
            req.getRequestDispatcher("/WEB-INF/Pages/login.jsp").forward(req,resp);
        }else{
            req.setAttribute("error","Failed to register user");
            req.getRequestDispatcher(registerURL).forward(req,resp);
        }

    }
}
