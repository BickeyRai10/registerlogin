package com.registerlogin.topic.controller;

import com.registerlogin.topic.dao.TopicDAO;
import com.registerlogin.user.model.TopicModel;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

public class AllTopicServlet extends HttpServlet {
    TopicDAO topicDAO=new TopicDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<TopicModel> topics=topicDAO.getAllTopics();
        req.setAttribute("topics",topics);
        req.getRequestDispatcher("/WEB-INF/Pages/topic/allTopic.jsp").forward(req,resp);
    }
}
