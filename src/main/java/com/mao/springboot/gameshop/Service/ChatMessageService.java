package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.ChatMessageDao;
import com.mao.springboot.gameshop.Entity.ChatMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageDao chatMessageDao;

    @Transactional
    public void addMessage(ChatMessage chatMessage){
        chatMessageDao.addMessage(chatMessage);
    }

    @Transactional
    public List<ChatMessage> getChatMessages(String from,String to){
        return chatMessageDao.getChatMessages(from,to);
    }

    @Transactional
    public void readAllMessages(String from,String to){
        chatMessageDao.readAllMessages(from,to);
    }

    @Transactional
    public long countUnreadMessage(String from,String to){
        return chatMessageDao.countUnreadMessage(from,to);
    }

}
