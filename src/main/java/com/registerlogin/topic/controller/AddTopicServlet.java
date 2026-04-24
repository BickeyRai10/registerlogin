package com.registerlogin.topic.controller;

import com.registerlogin.topic.dao.TopicDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class AddTopicServlet extends HttpServlet {
    TopicDAO topicDAO= new TopicDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/Pages/topic/addTopic.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String topic_name=req.getParameter("topic-name");
        System.out.println(topic_name);
        boolean isTopicExist=topicDAO.isTopicExist(topic_name);
        if(isTopicExist){
            req.setAttribute("error","Topic already exist!!");
            req.getRequestDispatcher("/WEB-INF/Pages/topic/addTopic.jsp");

        }
        boolean isTopicInsert= topicDAO.insertTopic(topic_name);
        if(isTopicInsert){
            resp.sendRedirect(req.getContextPath()+"/dashboard");
        }else{
            req.setAttribute("error","Unable to add topic!");
            req.getRequestDispatcher("/WEB-INF/Pages/topic/addTopic.jsp");
        }
    }
}
