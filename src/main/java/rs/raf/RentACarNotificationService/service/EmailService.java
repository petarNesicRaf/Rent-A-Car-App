package rs.raf.RentACarNotificationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.Notification;

@Component
public class EmailService {
    @Autowired
    public JavaMailSender mailSender;

    public void sendSimpleMessage(String to, String subject, Notification notification)
    {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(notification.getMessage());
        mailSender.send(message);
    }

}
