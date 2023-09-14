package com.example.qqzone.service;

import com.example.qqzone.pojo.Reply;

import java.util.List;

public interface ReplyService {
    List<Reply> getReplyListByTopicID(Integer topicID) throws Exception;
}
