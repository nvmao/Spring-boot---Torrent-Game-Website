package com.mao.springboot.gameshop.Service;

import com.mao.springboot.gameshop.Dao.ChatMessageDao;
import com.mao.springboot.gameshop.Entity.Message;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private ChatMessageDao chatMessageDao;

    @Transactional
    public void addMessage(Message message){
        chatMessageDao.addMessage(message);
    }

    @Transactional
    public List<Message> getChatMessages(String from, String to, int page){
        return chatMessageDao.getChatMessages(from,to,page);
    }

    @Transactional
    public void readAllMessages(String from,String to){
        chatMessageDao.readAllMessages(from,to);
    }

    @Transactional
    public long countUnreadMessage(String from,String to){
        return chatMessageDao.countUnreadMessage(from,to);
    }

    @Transactional
    public long countMessage(String from, String to){
        return chatMessageDao.countMessage(from,to);
    }


    @Transactional
    public long countUnreadNotification(String to) {
        return chatMessageDao.countUnreadNotification(to);
    }

    @Transactional
    public long countNotification(String to) {

        return chatMessageDao.countNotification(to);
    }

    public List<Message> getNotification(String to, int page){

        return chatMessageDao.getNotification(to,page);
    }
}
