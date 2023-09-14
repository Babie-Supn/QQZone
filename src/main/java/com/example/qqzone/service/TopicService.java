package com.example.qqzone.service;

import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicService {
    //查询特定用户的日志列表
    List<Topic> getTopicList(UserBasic userBasic) throws Exception;
    //根据id获取特定topic
    Topic getTopicByID(Integer id) throws Exception;

}
