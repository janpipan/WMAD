package webSocketService;

import apiREST.Cons;
import com.google.gson.Gson;
import util.Message;
import util.Topic;
import util.Subscription_close;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import subscriber.Subscriber;
import util.Subscription_check;
import util.Subscription_request;

@ClientEndpoint
public class WebSocketClient {

  static Map<Topic, Subscriber> subscriberMap;
  static Session session;

  public static void newInstance() {
    subscriberMap = new HashMap<Topic, Subscriber>();
    try {
      WebSocketContainer container = ContainerProvider.getWebSocketContainer();
      session = container.connectToServer(WebSocketClient.class,
        URI.create(Cons.SERVER_WEBSOCKET));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
  public static void close() {
    try {
      session.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static synchronized void addSubscriber(Topic topic, Subscriber subscriber) {
    try {
        
        Subscription_request sub_req = new Subscription_request(topic, Subscription_request.Type.ADD);
        
        Gson gson = new Gson();
        String json = gson.toJson(sub_req);
      
        session.getBasicRemote().sendText(json);
        
        subscriberMap.put(topic, subscriber);
        
        
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static synchronized void removeSubscriber(Topic topic) {
    try {
      
        Subscription_request sub_req = new Subscription_request(topic, Subscription_request.Type.REMOVE);
        
        Gson gson = new Gson();
        String json = gson.toJson(sub_req);
      
        session.getBasicRemote().sendText(json);
        
        subscriberMap.remove(topic);
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @OnMessage
  public void onMessage(String json) {

    Gson gson = new Gson();
    Subscription_close subs_close = gson.fromJson(json, Subscription_close.class);
    System.out.println(json);

    //ordinary message from topic:
    if (subs_close.cause==null) {
        
        Message msg = gson.fromJson(json, Message.class);
        //Subscription_check sub_check = gson.fromJson(json, Subscription_check.class);
        
        //System.out.println(msg);
        //System.out.println(sub_check);
        
        if (msg != null){
            Subscriber sub = subscriberMap.get(msg.topic);
            sub.onMessage(msg);
        }
      
      
    }
    //ending subscription message:
    else if (subs_close.cause == Subscription_close.Cause.PUBLISHER){
        System.out.println("No more publishers");
        try {
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
    } 
  }

}
