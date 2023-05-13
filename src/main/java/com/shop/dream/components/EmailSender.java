package com.shop.dream.components;

import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
public class EmailSender {
    private String host = "smtp.gmail.com";
    private final String username = "ds.as.dream.shop@gmail.com";
    private final String password = "-----------";
    private final int port = 587;

    private Properties properties(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        return props;
    }

    private Session session() {
        return Session.getInstance(properties(), new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }
    public void sendEmail(String recipient, String topic, String body){
        Session session = session();
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(topic);
            message.setText(body);
//            HTML mail +
//            MimeMultipart multipart = new MimeMultipart();
//            MimeBodyPart html = new MimeBodyPart();
//            html.setContent(body, "text/html");
//            multipart.addBodyPart(html);
//            message.setContent(multipart);
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            System.out.println("ERROR to send massage");
            e.printStackTrace();
        }
    }
}
