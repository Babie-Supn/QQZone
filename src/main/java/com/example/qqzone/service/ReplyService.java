package com.example.qqzone.service;

import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyListByTopicID(Integer topicID) throws Exception;
    //添加回复
    void addReply(Reply reply) throws Exception;

    void delReply(Integer replyId) throws Exception;

    //删除topic中所有的reply
    void delReplyList(Topic topic) throws Exception;
}
