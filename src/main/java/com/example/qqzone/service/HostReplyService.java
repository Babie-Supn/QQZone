package com.example.qqzone.service;

import com.example.qqzone.pojo.HostReply;

import java.util.List;

public interface HostReplyService {
    HostReply getHReplyByReplyID(Integer replyID) throws Exception;
}
