/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity.service;

import entity.Subscriber;
import util.Subscription_check;
import entity.Topic;
import entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author upcnet
 */
@Stateless
@Path("entity.subscriber")
public class SubscriberFacadeREST extends AbstractFacade<Subscriber> {

  @PersistenceContext(unitName = "PubSubWebServerPU")
  private EntityManager em;

  public SubscriberFacadeREST() {
    super(Subscriber.class);
  }

  @POST
  @Path("create")
  @Consumes({"application/xml", "application/json"})
  @Produces({"application/xml", "application/json"})
  public Subscription_check check_to_create(Subscriber entity) {
    
    // first, check out if the topic from which to subscribe is defined at
    // the Topic table, otherwise return the corresponding message:
    
    Query q = em.createNamedQuery("Topic.findByName");
    q.setParameter("name", entity.getTopic().getName());
    List<Topic> topicList = q.getResultList();
    // if retsult list is empty the topic does not exist return NO_TOPIC subscription check
    if (topicList.isEmpty()) {
        return new Subscription_check(entity.getTopic(),Subscription_check.Result.NO_TOPIC);
    }
    
    // set subscriber topic to already existing topic and check if he is already
    // subscribed to the topic
    entity.setTopic(topicList.get(0));
    q = em.createNamedQuery("Subscriber.findByUserAndTopic");
    q.setParameter("user", entity.getUser());
    q.setParameter("topic", entity.getTopic());
    // if subscriber is not subscribed to the topic yet add him to subscriber table
    // and afterwards return OKAY subscription check in either case
    if (q.getResultList().isEmpty()){
        super.create(entity);
    }
    // return a Subscription_check after saving the user as subscriber of the
    // topic:
    
    return new Subscription_check(entity.getTopic(),Subscription_check.Result.OKAY);
    // ...
    //throw new RuntimeException("To be completed by the student");
    
  }

  @POST
  @Path("delete")
  @Consumes({"application/xml", "application/json"})
  public Subscription_check check_to_delete(Subscriber entity) {
    
    // first, check out if the topic from which to unsubscribe is defined at
    // the Topic table, otherwise return the corresponding message:
    
    Query q = em.createNamedQuery("Topic.findByName");
    q.setParameter("name", entity.getTopic().getName());
    List<Topic> topicList = q.getResultList();
    
    // if there is no topic in db return return NO_TOPIC subscription check
    if (topicList.isEmpty()) {
        return new Subscription_check(entity.getTopic(),Subscription_check.Result.NO_TOPIC);
    }
    
    // check out if the user was subscribed to the intended topic, otherwise
    // return the corresponding message. Return the corresponding message
    // after removing the user from been subscribed to that topic:
    
    // check if subscriber is subscribed to the topic
    entity.setTopic(topicList.get(0));
    q = em.createNamedQuery("Subscriber.findByUserAndTopic");
    q.setParameter("user", entity.getUser());
    q.setParameter("topic", entity.getTopic());
    
    // if result list is empty subscriber is not subscribed to the topic
    // return NO_SUBSCRIPTION subscription check message
    if (q.getResultList().isEmpty()){
        //System.out.println("Sub list empty");
        return new Subscription_check(entity.getTopic(),Subscription_check.Result.NO_SUBSCRIPTION);
    }
    
    // otherwise delete subscription from db
    super.delete(entity);
    
    return new Subscription_check(entity.getTopic(),Subscription_check.Result.OKAY);
   
    
    // ...
    //throw new RuntimeException("To be completed by the student");
    
  }

  @POST
  @Path("subscriptions")
  @Consumes({"application/xml", "application/json"})
  @Produces({"application/xml", "application/json"})
  public List<Subscriber> subscriptions(User entity) {
    
    // return the list of subscriptions of the requested user:
    
    Query q = em.createNamedQuery("Subscriber.findByUser");
    q.setParameter("user", entity);
    return q.getResultList();
    //throw new RuntimeException("To be completed by the student");
  }

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

}
