package com.example.qqzone.controller;

import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;
import com.example.qqzone.service.ReplyService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


public class ReplyController {
    private ReplyService replyService;
    public String addReply(HttpSession session,String content,Integer topicId) throws Exception {
        UserBasic author=(UserBasic) session.getAttribute("userBasic");
        Reply reply=new Reply(content,LocalDateTime.now(),author,new Topic(topicId));
        replyService.addReply(reply);
        //需要更新信息，重定向
        return "redirect:topic.do?operate=topicDetail&id="+topicId;
    }
}
