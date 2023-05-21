package com.shop.dream.components;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Component
@Log4j2
public class EmailSender {
    private static final String HOST = "smtp.gmail.com";
    @Value("${email}")
    private String username;
    @Value("${email.password}")
    private String password;
    private static final int PORT = 587;

    private Properties properties(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", HOST);
        props.put("mail.smtp.port", PORT);
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

            log.info("Sent message successfully....");

        } catch (MessagingException e) {
            log.error("Sent message successfully....");
            e.printStackTrace();
        }
    }
}
