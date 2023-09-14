package com.example.qqzone.controller;

import com.example.qqzone.pojo.Topic;
import com.example.qqzone.service.ReplyService;
import com.example.qqzone.service.TopicService;

import javax.servlet.http.HttpSession;

public class TopicController {
    private TopicService topicService;
    private ReplyService replyService;

    public String topicDetail(Integer id, HttpSession session) throws Exception {
        Topic topic = topicService.getTopicByID(id);
        session.setAttribute("topic",topic);
        return "frames/detail";
    }
}
