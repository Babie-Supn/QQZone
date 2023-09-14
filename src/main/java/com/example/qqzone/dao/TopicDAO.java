package com.example.qqzone.dao;

import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;

import java.util.List;

public interface TopicDAO {
    //获取指定用户所有的日志
    public List<Topic> getTopicList(UserBasic userBasic) throws Exception;

    //添加指定用户的日志
    public void addTopic(Topic topic);

    //删除日志
    public void delTopic(Topic topic);

    //获取指定日志信息
    public Topic getTopic(Integer id) throws Exception;

}
