package com.example.qqzone.service.impl;

import com.example.qqzone.dao.TopicDAO;
import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;
import com.example.qqzone.service.ReplyService;
import com.example.qqzone.service.TopicService;
import com.example.qqzone.service.UserBasicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO;
    private ReplyService replyService;
    private UserBasicService userBasicService;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) throws Exception {
        return  topicDAO.getTopicList(userBasic);
    }
    private Topic getTopic(Integer id) throws Exception {
        Topic topic = topicDAO.getTopic(id);
        UserBasic author = topic.getAuthor();
        author=userBasicService.getUserByID(author.getId());
        topic.setAuthor(author);
        return topic;
    }

    @Override
    public Topic getTopicByID(Integer id) throws Exception {
        Topic topic = getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicID(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }

    @Override
    public void delTopic(Integer id) throws Exception {
        Topic topic = topicDAO.getTopic(id);
        if(topic!=null){
//            List<Reply> replyList=topic.getReplyList();
            replyService.delReplyList(topic);
            topicDAO.delTopic(topic);
        }

    }
}
