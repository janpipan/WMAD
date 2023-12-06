package publisher;

import util.Subscription_close;
import util.Message;
import util.Topic;
import java.util.ArrayList;
import java.util.List;
import subscriber.Subscriber;
import util.Subscription_close.Cause;

public class PublisherImpl implements Publisher {

  private List<Subscriber> subscriberSet;
  private int numPublishers;
  private Topic topic;

  public PublisherImpl(Topic topic) {
    subscriberSet = new ArrayList<Subscriber>();
    numPublishers = 1;
    this.topic = topic;
  }

  @Override
  public void incPublishers() {
       this.numPublishers++;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int decPublishers() {
      return --this.numPublishers;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void attachSubscriber(Subscriber subscriber) {
      this.subscriberSet.add(subscriber);
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean detachSubscriber(Subscriber subscriber) {
      // if subscriber is subscribed to publisher send on close cause as subscriber 
      // and remove him from subscriber set
      // if he is not in subscriber set return false
      if (this.subscriberSet.contains(subscriber)){
          subscriber.onClose(new Subscription_close(topic, Cause.SUBSCRIBER));
        return this.subscriberSet.remove(subscriber);
      }
      return false;
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void detachAllSubscribers() {
      for (Subscriber sub : subscriberSet) {
          sub.onClose(new Subscription_close(topic, Cause.PUBLISHER));
      }
      this.subscriberSet.clear();
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void publish(Message message) {
      for (Subscriber sub : subscriberSet){
          sub.onMessage(message);
      }
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  
}
