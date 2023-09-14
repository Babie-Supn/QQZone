package com.example.qqzone.service.impl;

import com.example.qqzone.dao.ReplyDAO;
import com.example.qqzone.pojo.HostReply;
import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;
import com.example.qqzone.service.HostReplyService;
import com.example.qqzone.service.ReplyService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
       private ReplyDAO replyDAO;

       //此处引入的是其他POJO对应的service接口，而不是DAO接口
    //其他POJO对应的业务逻辑是封装在service层的，我只需要调用别人的业务逻辑方法，而不要去深入考虑人家的内部细节
       private HostReplyService hostReplyService;
    @Override
    public List<Reply> getReplyListByTopicID(Integer topicID) throws Exception {
        List<Reply> replyList= replyDAO.getReplyList(new Topic(topicID));
        for(int i=0;i<replyList.size();i++){
            Reply reply = replyList.get(i);
            HostReply hostReply = hostReplyService.getHReplyByReplyID(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }


}
