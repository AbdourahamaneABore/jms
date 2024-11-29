package jmsenset;

import java.util.Scanner;

import org.apache.activemq.ActiveMQConnectionFactory;

import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.DeliveryMode;
import jakarta.jms.Destination;
import jakarta.jms.JMSException;
import jakarta.jms.MessageProducer;
import jakarta.jms.Session;
import jakarta.jms.TextMessage;

public class Producer {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Session session;
		
		try {
			
			ConnectionFactory connectionFactory=new ActiveMQConnectionFactory("tcp://localhost:61616");
			Connection connection=connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
			//Destination destination=session.createQueue("enset.queue");
			Destination destination=session.createTopic("enset.topic");
			MessageProducer producer=session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			TextMessage textMessage = session.createTextMessage();
			textMessage.setText("Hello.....");
			producer.send(textMessage);
			System.out.println("Envoi du message");
			session.close();
			connection.close();
			
			} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  } 
}
		