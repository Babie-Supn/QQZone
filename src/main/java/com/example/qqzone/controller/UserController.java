package com.example.qqzone.controller;

import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;
import com.example.qqzone.service.TopicService;
import com.example.qqzone.service.UserBasicService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserController {
    private UserBasicService userBasicService;
    private TopicService topicService;
    public String login(String loginID, String pwd, HttpSession session) throws Exception {
        UserBasic userBasic = userBasicService.login(loginID, pwd);
        if(userBasic!=null){
        List<UserBasic> friendList=userBasicService.getFriendList(userBasic);
        List<Topic> topicList = topicService.getTopicList(userBasic);
        userBasic.setFriendList(friendList);
        userBasic.setTopicList(topicList);

        //userBasic 这个key保存的是登陆者的信息
            //friend 这个key保存的是被访问空间者的信息
        session.setAttribute("userBasic",userBasic);
        session.setAttribute("friend",userBasic);
        return "index";
        }else{
            return "login";
        }

    }
    public String friend(Integer id,HttpSession session) throws Exception {
        UserBasic currFriend = userBasicService.getUserByID(id);
         List<Topic> topicList=topicService.getTopicList(currFriend);
            currFriend.setTopicList(topicList);
            session.setAttribute("friend",currFriend);
            return "index";
    }

}
