package topicmanager;

import util.Subscription_check;
import util.Topic;
import util.Topic_check;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import publisher.Publisher;
import publisher.PublisherImpl;
import subscriber.Subscriber;
import util.Subscription_check.Result;

public class TopicManagerImpl implements TopicManager {

  private Map<Topic, Publisher> topicMap;

  public TopicManagerImpl() {
    topicMap = new HashMap<Topic, Publisher>();
  }

  @Override
  public Publisher addPublisherToTopic(Topic topic) {
      Publisher pub;
      Topic_check tc = this.isTopic(topic);
      
      if (tc.isOpen) {
          pub = topicMap.get(tc.topic);
          pub.incPublishers();
      } else {
          pub = new PublisherImpl(tc.topic);
          topicMap.put(tc.topic, pub);
      }
      return pub;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void removePublisherFromTopic(Topic topic) {
      Publisher pub = topicMap.get(topic);
      int numOfPublishers = pub.decPublishers();
      if (numOfPublishers == 0) {
          pub.detachAllSubscribers();
          topicMap.remove(topic);
      } 
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Topic_check isTopic(Topic topic) {
      for (Map.Entry<Topic, Publisher> set : topicMap.entrySet()){
          Topic top = set.getKey();
          if (top.equals(topic)){
              return new Topic_check(top,true);
          }
          
      }
      return new Topic_check(topic, false);
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Topic> topics() {
      List<Topic> topicList = new ArrayList<Topic>(this.topicMap.keySet());
      return topicList;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Subscription_check subscribe(Topic topic, Subscriber subscriber) {
      for (Topic top : this.topics()){
          if (top.equals(topic)){
              topicMap.get(topic).attachSubscriber(subscriber);
              return new Subscription_check(top, Result.OKAY);
          }
      }
      return new Subscription_check(topic, Result.NO_TOPIC);
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Subscription_check unsubscribe(Topic topic, Subscriber subscriber) {
      for (Topic top : this.topics()){
          if (top.equals(topic)){
              if (topicMap.get(topic).detachSubscriber(subscriber)){
                  return new Subscription_check(topic, Result.OKAY);
              }
              return new Subscription_check(topic, Result.NO_SUBSCRIPTION);
          }
      }
      return new Subscription_check(topic, Result.NO_TOPIC);
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
}
