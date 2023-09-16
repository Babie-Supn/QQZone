package com.example.qqzone.dao;

import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;

import java.util.List;

public interface ReplyDAO {
    //获取指定日志的回复列表
    public List<Reply> getReplyList(Topic topic) throws Exception;

    //添加回复
    public void addReply(Reply reply) throws Exception;

    //删除回复
    public void delReplyByID(Integer id) throws Exception;



}
