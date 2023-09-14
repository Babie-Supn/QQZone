package com.example.qqzone.service.impl;

import com.example.qqzone.dao.HostReplyDAO;
import com.example.qqzone.pojo.HostReply;
import com.example.qqzone.service.HostReplyService;

public class HostReplyServiceImpl implements HostReplyService {
    private HostReplyDAO hostReplyDAO;
    @Override
    public HostReply getHReplyByReplyID(Integer replyID) throws Exception {
        return hostReplyDAO.getHostReplyByReplyByID(replyID);
    }
}
