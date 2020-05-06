package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.ChatMessage;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ChatMessageDao {

    @Autowired
    private EntityManager entityManager;

    public void addMessage(ChatMessage chatMessage){

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(chatMessage);

    }

    public List<ChatMessage> getChatMessages(String from,String to){

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "from ChatMessage c " +
                        "where " +
                        "(c.fromUser.userName=:fromUser and c.toUser.userName=:toUser) " +
                        "or (c.fromUser.userName=:toUser and c.toUser.userName=:fromUser) order by c.id");

        query.setParameter("fromUser",from);
        query.setParameter("toUser",to);

        List<ChatMessage> chatMessages = query.getResultList();

        return chatMessages;
    }

    public void readAllMessages(String from, String to) {
//        Session session = entityManager.unwrap(Session.class);
//
//        Query query = session.createQuery(
//                "update ChatMessage c set isRead=true " +
//                        "where " +
//                        "(c.fromUser.userName=:fromUser and c.toUser.userName=:toUser) " +
//                        "or (c.fromUser.userName=:toUser and c.toUser.userName=:fromUser)");
//
//        query.setParameter("fromUser",from);
//        query.setParameter("toUser",to);
//
//        query.executeUpdate();

        List<ChatMessage> messages = getChatMessages(from,to);
        for(var m : messages){
            m.setRead(true);
            addMessage(m);
        }

    }

    public long countUnreadMessage(String from, String to) {

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "select count(*) from ChatMessage c " +
                        "where " +
                        "(c.fromUser.userName=:fromUser and c.toUser.userName=:toUser) " +
                        " and (isRead=false) ");

        query.setParameter("fromUser",from);
        query.setParameter("toUser",to);

        long count = (long)query.getSingleResult();

        return count;
    }
}
