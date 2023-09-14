package com.example.qqzone.service.impl;

import com.example.qqzone.dao.TopicDAO;
import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;
import com.example.qqzone.service.ReplyService;
import com.example.qqzone.service.TopicService;

import java.util.List;

public class TopicServiceImpl implements TopicService {
    private TopicDAO topicDAO;
    private ReplyService replyService;
    @Override
    public List<Topic> getTopicList(UserBasic userBasic) throws Exception {
        return  topicDAO.getTopicList(userBasic);
    }

    @Override
    public Topic getTopicByID(Integer id) throws Exception {
        Topic topic = topicDAO.getTopic(id);
        List<Reply> replyList = replyService.getReplyListByTopicID(topic.getId());
        topic.setReplyList(replyList);
        return topic;
    }
}
