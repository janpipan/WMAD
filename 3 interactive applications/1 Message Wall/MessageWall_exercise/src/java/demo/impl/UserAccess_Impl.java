package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.UserAccess;
import java.util.List;

public class UserAccess_Impl implements UserAccess {

  private String user;
  private MessageWall messageWall;

  public UserAccess_Impl(MessageWall mw, String usr) {
    this.messageWall = mw;
    this.user = usr;
  }

  @Override
  public String getUser() {
    return this.user;
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Message getLast() {
    return messageWall.getLast();
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getNumber() {
      return messageWall.getNumber();
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void put(String msg) {
      this.messageWall.put(user, msg);
     //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(int index) {
      return this.messageWall.delete(user, index);
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Message> getAllMessages() {
      return messageWall.getAllMessages();
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
