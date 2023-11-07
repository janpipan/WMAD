package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.RemoteLogin;
import demo.spec.UserAccess;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class MessageWall_and_RemoteLogin_Impl implements RemoteLogin, MessageWall {

  private List<Message> messages;

  public MessageWall_and_RemoteLogin_Impl() {
    this.messages = new ArrayList<Message>();
  }

  @Override
  public UserAccess connect(String usr, String passwd) {
        Hashtable<String, String> userPass = new Hashtable<String, String>();
        
        userPass.put("Oliver","password1");
        userPass.put("Jack","password2");
        userPass.put("Harry","password3");
        userPass.put("Alice","password4");
        userPass.put("Emily","password5");
        
        
        if (userPass.containsKey(usr)) {
            if (userPass.get(usr).equals(passwd)){
                return new UserAccess_Impl(this,usr);
            }
        }
        return null;
        //return new UserAccess_Impl(this ,usr); 
        
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public void put(String user, String msg) {
      messages.add(new Message_Impl(user, msg));
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public boolean delete(String user, int index) {
      Message msg = messages.get(index);
      if (msg.getOwner().equals(user)) {
          messages.remove(index);
          return true;
      } else {
          return false;
      }
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public Message getLast() {
      int size = this.messages.size();
      if (size > 0){
          return this.messages.get(this.messages.size() -1);
      }
      return new Message_Impl("","");
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public int getNumber() {
      return messages.size();
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

  @Override
  public List<Message> getAllMessages() {
      return messages;
      //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
  }

}
