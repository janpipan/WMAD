package main;

import util.Message;
import util.Subscription_check;
import util.Topic;
import subscriber.SubscriberImpl;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import publisher.Publisher;
import subscriber.Subscriber;
import topicmanager.TopicManager;
import util.Topic_check;

public class SwingClient {

  TopicManager topicManager;
  public Map<Topic, Subscriber> my_subscriptions;
  Publisher publisher;
  Topic publisherTopic;

  JFrame frame;
  JTextArea topic_list_TextArea;
  public JTextArea messages_TextArea;
  public JTextArea my_subscriptions_TextArea;
  JTextArea publisher_TextArea;
  JTextField argument_TextField;

  public SwingClient(TopicManager topicManager) {
    this.topicManager = topicManager;
    my_subscriptions = new HashMap<Topic, Subscriber>();
    publisher = null;
    publisherTopic = null;
  }

  public void createAndShowGUI() {

    frame = new JFrame("Publisher/Subscriber demo");
    frame.setSize(300, 300);
    frame.addWindowListener(new CloseWindowHandler());

    topic_list_TextArea = new JTextArea(5, 10);
    messages_TextArea = new JTextArea(10, 20);
    my_subscriptions_TextArea = new JTextArea(5, 10);
    publisher_TextArea = new JTextArea(1, 10);
    argument_TextField = new JTextField(20);

    JButton show_topics_button = new JButton("show Topics");
    JButton new_publisher_button = new JButton("new Publisher");
    JButton remove_publisher = new JButton("remove Publisher");
    JButton new_subscriber_button = new JButton("new Subscriber");
    JButton to_unsubscribe_button = new JButton("to unsubscribe");
    JButton to_post_an_event_button = new JButton("post an event");
    JButton to_close_the_app = new JButton("close app.");
    

    show_topics_button.addActionListener(new showTopicsHandler());
    new_publisher_button.addActionListener(new newPublisherHandler());
    remove_publisher.addActionListener(new removePublisherHandler());
    new_subscriber_button.addActionListener(new newSubscriberHandler());
    to_unsubscribe_button.addActionListener(new UnsubscribeHandler());
    to_post_an_event_button.addActionListener(new postEventHandler());
    to_close_the_app.addActionListener(new CloseAppHandler());
    

    JPanel buttonsPannel = new JPanel(new FlowLayout());
    buttonsPannel.add(show_topics_button);
    buttonsPannel.add(new_publisher_button);
    buttonsPannel.add(remove_publisher);
    buttonsPannel.add(new_subscriber_button);
    buttonsPannel.add(to_unsubscribe_button);
    buttonsPannel.add(to_post_an_event_button);
    buttonsPannel.add(to_close_the_app);

    JPanel argumentP = new JPanel(new FlowLayout());
    argumentP.add(new JLabel("Write content to set a new_publisher / new_subscriber / unsubscribe / post_event:"));
    argumentP.add(argument_TextField);

    JPanel topicsP = new JPanel();
    topicsP.setLayout(new BoxLayout(topicsP, BoxLayout.PAGE_AXIS));
    topicsP.add(new JLabel("Topics:"));
    topicsP.add(topic_list_TextArea);
    topicsP.add(new JScrollPane(topic_list_TextArea));
    topicsP.add(new JLabel("My Subscriptions:"));
    topicsP.add(my_subscriptions_TextArea);
    topicsP.add(new JScrollPane(my_subscriptions_TextArea));
    topicsP.add(new JLabel("I'm Publisher of topic:"));
    topicsP.add(publisher_TextArea);
    topicsP.add(new JScrollPane(publisher_TextArea));

    JPanel messagesPanel = new JPanel();
    messagesPanel.setLayout(new BoxLayout(messagesPanel, BoxLayout.PAGE_AXIS));
    messagesPanel.add(new JLabel("Messages:"));
    messagesPanel.add(messages_TextArea);
    messagesPanel.add(new JScrollPane(messages_TextArea));

    Container mainPanel = frame.getContentPane();
    mainPanel.add(buttonsPannel, BorderLayout.PAGE_START);
    mainPanel.add(messagesPanel, BorderLayout.CENTER);
    mainPanel.add(argumentP, BorderLayout.PAGE_END);
    mainPanel.add(topicsP, BorderLayout.LINE_START);

    frame.pack();
    frame.setVisible(true);
  }

  class showTopicsHandler implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      
      List<Topic> topicList = topicManager.topics();
      
      StringBuilder topicListText = new StringBuilder();
      for (Topic topic : topicList){
          topicListText.append(topic.getName() + "\n");
      }
      topic_list_TextArea.setText(topicListText.toString());
      
    }
  }

  class newPublisherHandler implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        
        if (publisher == null) {
            String inputText = argument_TextField.getText();
            if (!inputText.equals("")){
                Topic topic = new Topic(inputText);
        
                publisherTopic = topic;
                publisher = topicManager.addPublisherToTopic(publisherTopic);
                argument_TextField.setText("");
                publisher_TextArea.setText(topic.name);
            } else {
                messages_TextArea.append("System: Please enter valid topic.\n");
            }
            
        } else {
            
            Topic topic = new Topic(argument_TextField.getText());

            publisherTopic = topic;
            publisher = topicManager.addPublisherToTopic(publisherTopic);
            argument_TextField.setText("");
            publisher_TextArea.setText(topic.name);
            
            
        }
      
        
      
    }
  }
  
  class removePublisherHandler implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          if (publisher != null) {
                topicManager.removePublisherFromTopic(publisherTopic);
                publisherTopic = null;
                publisher = null;
                publisher_TextArea.setText("");
          } else {
              messages_TextArea.append("System: You are not Publisher.\n");
          }
          argument_TextField.setText("");
      }
  }

  class newSubscriberHandler implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      
      
      
        if (!argument_TextField.getText().equals("")){
            Subscriber sub = new SubscriberImpl(SwingClient.this);
            Topic top = new Topic(argument_TextField.getText());
            if (my_subscriptions.containsKey(top)){
                messages_TextArea.append("System: You are already subscribed to this topic.\n");
            } else {
                Subscription_check sub_check = topicManager.subscribe(top,sub);
                //System.out.println(sub_check.result == );
                if (sub_check.result == Subscription_check.Result.OKAY) {
                    my_subscriptions.put(sub_check.topic,sub);
                    StringBuilder subscriptionListText = new StringBuilder();
                    for (Topic topic : my_subscriptions.keySet()){
                        subscriptionListText.append(topic.getName() + "\n");
                    }
                    my_subscriptions_TextArea.setText(subscriptionListText.toString());
                } else if (sub_check.result == Subscription_check.Result.NO_TOPIC) {
                    messages_TextArea.append("System: Topic " + top.getName() + " does not exist.\n");
                }
            }
            argument_TextField.setText("");
        } else {
            messages_TextArea.append("System: Please enter valid topic.\n");
        }
      
      
      
    }
  }

  class UnsubscribeHandler implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        Topic topic = new Topic(argument_TextField.getText());
        Subscriber sub = my_subscriptions.get(topic);
        Subscription_check sub_check = topicManager.unsubscribe(topic, sub);
        if (sub_check.result == Subscription_check.Result.OKAY) {
            my_subscriptions.remove(topic);
            StringBuilder subscriptionListText = new StringBuilder();
            for (Topic top : my_subscriptions.keySet()){
                subscriptionListText.append(top.getName() + "\n");
            }
            my_subscriptions_TextArea.setText(subscriptionListText.toString());
        } else if (sub_check.result == Subscription_check.Result.NO_SUBSCRIPTION){
            messages_TextArea.append("System: You are not subscribed to " + topic.getName() + ".\n");
        } else if (sub_check.result == Subscription_check.Result.NO_TOPIC) {
            messages_TextArea.append("System: Topic " + topic.getName() + " does not exist.\n");
        }
      argument_TextField.setText("");
    }
  }

  class postEventHandler implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        
        if (publisher != null) {
            String inputText = argument_TextField.getText();
            // if input field is not empty send msg otherwise notify pusblisher
            if (!inputText.equals("")) {
                publisher.publish(new Message(publisherTopic, inputText));
            } else {
                messages_TextArea.append("System: Please enter a message.\n");
            }
        } else {
            messages_TextArea.append("System: You are not a Publisher.\n");
        }
        argument_TextField.setText("");
        
      
    }
  }

  class CloseAppHandler implements ActionListener {

    public void actionPerformed(ActionEvent e) {
      System.out.println("all users closed");
      System.exit(0);
    }
  }

  class CloseWindowHandler implements WindowListener {

    public void windowDeactivated(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
      
        if (publisher != null) {
            topicManager.removePublisherFromTopic(publisherTopic);
            publisher = null;
            publisherTopic = null;
        } 
        
        if (!my_subscriptions.isEmpty()) {
            for (Map.Entry<Topic, Subscriber> subscription : my_subscriptions.entrySet()) {
                topicManager.unsubscribe(subscription.getKey(), subscription.getValue());
            }
            my_subscriptions.clear();
        }
      
        System.out.println("one user closed");
    }
  }
}
