package com.example.qqzone.service.impl;

import com.example.qqzone.dao.ReplyDAO;
import com.example.qqzone.pojo.HostReply;
import com.example.qqzone.pojo.Reply;
import com.example.qqzone.pojo.Topic;
import com.example.qqzone.pojo.UserBasic;
import com.example.qqzone.service.HostReplyService;
import com.example.qqzone.service.ReplyService;
import com.example.qqzone.service.UserBasicService;

import java.util.List;

public class ReplyServiceImpl implements ReplyService {
       private ReplyDAO replyDAO;

       //此处引入的是其他POJO对应的service接口，而不是DAO接口
    //其他POJO对应的业务逻辑是封装在service层的，我只需要调用别人的业务逻辑方法，而不要去深入考虑人家的内部细节
       private HostReplyService hostReplyService;
       private UserBasicService userBasicService;
    @Override
    public List<Reply> getReplyListByTopicID(Integer topicID) throws Exception {
        List<Reply> replyList= replyDAO.getReplyList(new Topic(topicID));
        for(int i=0;i<replyList.size();i++){
            Reply reply = replyList.get(i);
            //将关联的作者设置进去
            UserBasic author = userBasicService.getUserByID(reply.getAuthor().getId());
            reply.setAuthor(author);
            //将关联的hostReply设置进去
            HostReply hostReply = hostReplyService.getHReplyByReplyID(reply.getId());
            reply.setHostReply(hostReply);
        }
        return replyList;
    }

    @Override
    public void addReply(Reply reply) throws Exception {
        replyDAO.addReply(reply);
    }

    @Override
    public void delReply(Integer replyId) throws Exception {
        //1.根据id获取到reply
        Reply reply = replyDAO.getReplyById(replyId);
        if(reply!=null){
            HostReply hostReply = hostReplyService.getHReplyByReplyID(replyId);
//            reply.setHostReply(hostReply);
            //2.如果reply有关联的hostReply,则先删除hostReply
            if(reply.getHostReply()!=null){
                hostReplyService.delHostReplyByID(hostReply.getId());
            }
        //3.删除reply
        replyDAO.delReplyByID(replyId);
        }
    }

    @Override
    public void delReplyList(Topic topic) throws Exception {
        List<Reply> replyList = replyDAO.getReplyList(topic);
        if(replyList!=null){
            for(Reply reply:replyList){
                delReply(reply.getId());
            }
        }
    }


}
