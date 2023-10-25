package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.util.ArrayList;
import java.util.List;

public class MessageWall_and_RemoteLogin_Impl implements RemoteLogin, MessageWall {

  private List<Message> messages;

  public MessageWall_and_RemoteLogin_Impl() {
    this.messages = new ArrayList<Message>();
  }

  @Override
  public UserAccess connect(String usr, String passwd) {
      return new UserAccess_Impl((MessageWall) this.messages ,usr); 
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void put(String user, String msg) {
      this.messages.add(new Message_Impl(user, msg));
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(String user, int index) {
      Message msg = this.messages.get(index);
      if (msg.getOwner() == user) {
          this.messages.remove(index);
          return true;
      } else {
          return false;
      }
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Message getLast() {
      return this.messages.get(this.messages.size()-1);
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getNumber() {
      return this.messages.size();
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Message> getAllMessages() {
      return this.messages;
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
