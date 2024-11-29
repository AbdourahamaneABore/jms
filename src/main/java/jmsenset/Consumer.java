package jmsenset;


import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class Consumer{

	public static void main(String[] args) {
		
		try {
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory ("tcp://localhost:61616");	
			Connection connection=connectionFactory.createConnection();

			connection.start();
			Session session=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			//Destination destination=session.createQueue("enset.queue");
			Destination destination=session.createTopic("enset.topic");
			MessageConsumer consumer=session.createConsumer(destination);
			consumer.setMessageListener(new MessageListener() {
				
				@Override
				public void onMessage(Message message) {
					// TODO Auto-generated method stub
					if(message instanceof TextMessage) {
						  try {
							TextMessage textMessage=(TextMessage) message;
							System.out.println("Réception du message :"+textMessage.getText());
						}catch (Exception e) {
				           // TODO Auto-generated catch block
				           e.printStackTrace();
			            }
					
					}
				}
			});
		}catch (Exception e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
         }
		
	}
}
	

