package com.example.qqzone.dao;

import com.example.qqzone.pojo.HostReply;

public interface  HostReplyDAO {
    //根据replyid查询关联的HostReply实体
    HostReply getHostReplyByReplyByID(Integer replyID) throws Exception;
}
