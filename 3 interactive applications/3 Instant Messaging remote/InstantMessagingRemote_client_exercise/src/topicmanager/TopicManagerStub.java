package topicmanager;

import apiREST.apiREST_TopicManager;
import util.Subscription_check;
import util.Topic;
import util.Topic_check;
import java.util.List;
import publisher.Publisher;
import publisher.PublisherStub;
import subscriber.Subscriber;
import webSocketService.WebSocketClient;

public class TopicManagerStub implements TopicManager {

  public String user;

  public TopicManagerStub(String user) {
    WebSocketClient.newInstance();
    this.user = user;
  }

  public void close() {
    WebSocketClient.close();
  }

  @Override
  public Publisher addPublisherToTopic(Topic topic) {
      apiREST_TopicManager.addPublisherToTopic(topic);
      return new PublisherStub(topic);
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void removePublisherFromTopic(Topic topic) {
      apiREST_TopicManager.removePublisherFromTopic(topic);
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Topic_check isTopic(Topic topic) {
      return apiREST_TopicManager.isTopic(topic);
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Topic> topics() {
      return apiREST_TopicManager.topics();
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Subscription_check subscribe(Topic topic, Subscriber subscriber) {
      
      Topic_check tc = this.isTopic(topic);
      
      if (tc.isOpen) {
          WebSocketClient.addSubscriber(topic, subscriber);
          return new Subscription_check(topic, Subscription_check.Result.OKAY);
      }
      
      return new Subscription_check(topic, Subscription_check.Result.NO_TOPIC);
      
      
      
      
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Subscription_check unsubscribe(Topic topic, Subscriber subscriber) {
      
    // if subscribed
    if (WebSocketClient.isSubscribed(topic)) {
          WebSocketClient.removeSubscriber(topic);
        return new Subscription_check(topic, Subscription_check.Result.OKAY);
    }
    
    return new Subscription_check(topic, Subscription_check.Result.NO_SUBSCRIPTION);
      
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
