package com.mao.springboot.gameshop.Dao;

import com.mao.springboot.gameshop.Entity.Message;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.hibernate.query.Query;
import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ChatMessageDao {

    @Autowired
    private EntityManager entityManager;

    public void addMessage(Message message){

        Session session = entityManager.unwrap(Session.class);

        session.saveOrUpdate(message);

    }


    public List<Message> getChatMessages(String from, String to, int page){

        int itemPerPage = 20;
        int offset = (page - 1) * itemPerPage;

        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "from Message c " +
                        "where " +
                        "(c.fromUser.userName=:fromUser and c.toUser.userName=:toUser) " +
                        "or (c.fromUser.userName=:toUser and c.toUser.userName=:fromUser) order by c.id desc ");

        query.setParameter("fromUser",from);
        query.setParameter("toUser",to);

        query.setFirstResult(offset);
        query.setMaxResults(itemPerPage);

        List<Message> messages = query.getResultList();

        return messages;
    }

    public void readAllMessages(String from, String to) {
        Session session = entityManager.unwrap(Session.class);

        Query query = session.createQuery(
                "from Message c " +
                        "where " +
                        "((c.fromUser.userName=:fromUser and c.toUser.userName=:toUser) " +
                        "or (c.fromUser.userName=:toUser and c.toUser.userName=:fromUser)) and c.isRead=false ");

        query.setParameter("fromUser",from);
        query.setParameter("toUser",to);

        List<Message> messages = query.getResultList();
        for(var m : messages){
            m.setRead(true);
            addMessage(m);
        }

    }

    public long countMessage(String from, String to) {

        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery(
                "select count(*) from Message c " +
                        "where " +
                        "(c.fromUser.userName=:fromUser and c.toUser.userName=:toUser) " +
                        "or (c.fromUser.userName=:toUser and c.toUser.userName=:fromUser)");

        query.setParameter("fromUser",from);
        query.setParameter("toUser",to);

        long count = query.uniqueResult();

        return count;
    }

    public long countUnreadMessage(String from, String to) {

        Session session = entityManager.unwrap(Session.class);

        Query<Long> query = session.createQuery(
                "select count(*) from Message c " +
                        "where " +
                        "(c.fromUser.userName=:fromUser and c.toUser.userName=:toUser) " +
                        " and (isRead=false) ");

        query.setParameter("fromUser",from);
        query.setParameter("toUser",to);

        long count = (long)query.uniqueResult();

        return count;
    }
}
