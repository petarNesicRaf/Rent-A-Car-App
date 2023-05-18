package rs.raf.RentACarNotificationService.listener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.ChangePasswordNotification;
import rs.raf.RentACarNotificationService.domain.Notification;
import rs.raf.RentACarNotificationService.domain.NotificationType;
import rs.raf.RentACarNotificationService.domain.Type;
import rs.raf.RentACarNotificationService.exceptions.NotFoundException;
import rs.raf.RentACarNotificationService.helper.MessageHelper;
import rs.raf.RentACarNotificationService.repository.NotificationRepository;
import rs.raf.RentACarNotificationService.repository.NotificationTypeRepository;
import rs.raf.RentACarNotificationService.service.EmailService;
import rs.raf.RentACarNotificationService.userservice.dto.UserModifyDto;
import rs.raf.RentACarNotificationService.userservice.dto.UserPasswordDto;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class ChangePasswordListener {

    private MessageHelper messageHelper;

    private EmailService emailService;

    private NotificationTypeRepository notificationTypeRepository;

    private NotificationRepository notificationRepository;

    public ChangePasswordListener(MessageHelper messageHelper, EmailService emailService, NotificationTypeRepository notificationTypeRepository, NotificationRepository notificationRepository) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationRepository = notificationRepository;
    }
    @JmsListener(destination = "${destination.changePassword}", concurrency = "5-10")
    public void changePassword(Message message) throws JMSException {
        UserPasswordDto userPasswordDto = messageHelper.getMessage(message, UserPasswordDto.class);
        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.CHANGE_PASSWORD)
                .orElseThrow(()->new NotFoundException("Notification type not found"));
        Notification notification = new ChangePasswordNotification(userPasswordDto.getEmail(),notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(userPasswordDto.getEmail(), "Password reset", notification);

    }
}
