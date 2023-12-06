package webSocketService;

import com.google.gson.Gson;
import entity.Message;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import util.Subscription_request;
import entity.Topic;
import util.Subscription_close;
import entity.service.TopicFacadeREST;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@ServerEndpoint("/ws")
public class WebSocketServer {

  TopicFacadeREST topicFacadeREST = lookupTopicFacadeRESTBean();

  // to store the open websocket sessions with the clients:
  private static Set<Session> sessions = new HashSet<Session>();
  
  // to store the subscriptions to the different topics of the connected clients:
  private static Map<Session, List<Topic>> subscriptions = new HashMap<Session, List<Topic>>();

  @OnMessage
  public void onMessage(String message, Session session)
    throws IOException, SQLException {
    System.out.println("onMessage: " + message);

    Subscription_request s_req = new Gson().fromJson(message, Subscription_request.class);
    
    // check if the topic exists, otherwise exit:
    if (!topicFacadeREST.isTopic(s_req.topic).isOpen){
        return;
    }
    
    
    // process the subscription request, from a given client, according
    // to how the websocket client has been programmed at the other end:
    if (s_req.type == Subscription_request.Type.ADD){
        if(!subscriptions.get(session).contains(s_req.topic)){
            subscriptions.get(session).add(s_req.topic);
            System.out.println("Subscirber added");
        }
    } else if (s_req.type == Subscription_request.Type.REMOVE){
        if(subscriptions.get(session).contains(s_req.topic)){
            subscriptions.get(session).remove(s_req.topic);
            System.out.println("Subscirber removed");
        }
    }
    //throw new RuntimeException("To be completed by the student");

  }

  @OnOpen
  public void onOpen(Session session) {
    sessions.add(session);
    subscriptions.put(session, new ArrayList<Topic>());
    System.out.println("new session: " + session.getId());
  }

  @OnClose
  public void onClose(Session session) {
    System.out.println("closed session: " + session.getId());
    sessions.remove(session);
    subscriptions.remove(session);
  }

  public static void notifyNewMessage(Message message) {
    String json_message = new Gson().toJson(message);
    Topic topic = message.getTopic();
    System.out.println(message.getTopic());
    
    try {
      
      // send the message to the clients subscribed to the
      // message's topic and presently connected by a websocket:
      
      for (Session session : sessions){
          List<Topic> topicList = subscriptions.get(session);
          for (Topic top : topicList){
              if(top.equals(topic)){
                  System.out.println("Sending msg");
                  session.getBasicRemote().sendText(json_message);
              }
          }
      }
      
      //throw new RuntimeException("To be completed by the student");
    
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  public static void notifyTopicClose(Subscription_close subs_close) {
    Gson gson = new Gson();
    String json_subs_close = gson.toJson(subs_close);
    Topic topic = subs_close.topic;
    
    try {
      
      // send the closing notification to the clients presently
      // connected by a websocket and subscribed to the topic which
      // is about to be closed:
      // ...
      throw new RuntimeException("To be completed by the student");
      
    } catch (Throwable e) {
      e.printStackTrace();
    }
  }

  private TopicFacadeREST lookupTopicFacadeRESTBean() {
    try {
      Context c = new InitialContext();
      return (TopicFacadeREST) c.lookup("java:global/InstantMessagingRemote_server_with_ddbb_exercise/TopicFacadeREST!entity.service.TopicFacadeREST");
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
