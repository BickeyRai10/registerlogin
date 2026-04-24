package com.registerlogin.topic.controller;

import com.registerlogin.topic.dao.TopicDAO;
import com.registerlogin.user.model.TopicModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class UpdateTopicServlet extends HttpServlet {
    TopicDAO topicDAO=new TopicDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        int topicId=Integer.parseInt(id);
        TopicModel topic=topicDAO.getSingleTopic(topicId);
        req.setAttribute("topic",topic);
        req.getRequestDispatcher("/WEB-INF/pages/topic/updateTopic.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String topic_name = req.getParameter("topic_name");
            int id = Integer.parseInt(req.getParameter("id"));
//            System.out.println(id + topic_name);
            boolean isUpdate=topicDAO.updateTopic(topic_name,id);
            if(isUpdate){
                resp.sendRedirect(req.getContextPath()+"/topic-list");
            }else{
                req.setAttribute("error","Unable to update topic");
                req.getRequestDispatcher("/WEB-INF/pages/topic/updateTopic.jsp").forward(req,resp);

            }
        }catch (NumberFormatException nfe){

            req.setAttribute("error","Id must be in number!!");
            req.getRequestDispatcher("/WEB-INF/pages/topic/updateTopic.jsp").forward(req,resp);
        }
    }
}
