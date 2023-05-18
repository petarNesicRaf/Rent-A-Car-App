package rs.raf.RentACarNotificationService.listener;

import javassist.NotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.Notification;
import rs.raf.RentACarNotificationService.domain.NotificationType;
import rs.raf.RentACarNotificationService.domain.Type;
import rs.raf.RentACarNotificationService.domain.VerifyEmailNotification;
import rs.raf.RentACarNotificationService.helper.MessageHelper;
import rs.raf.RentACarNotificationService.repository.NotificationRepository;
import rs.raf.RentACarNotificationService.repository.NotificationTypeRepository;
import rs.raf.RentACarNotificationService.service.EmailService;
import rs.raf.RentACarNotificationService.userservice.dto.UserModifyDto;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddClientListener {
    private MessageHelper messageHelper;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;

    private EmailService emailService;

    public AddClientListener(MessageHelper messageHelper, NotificationRepository notificationRepository,
                             NotificationTypeRepository notificationTypeRepository, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.addClient}", concurrency = "5-10")
    public void addClient(Message message) throws JMSException, NotFoundException {
        //ClientCreateDto clientCreateDto = messageHelper.getMessage(message, ClientCreateDto.class);
        System.out.println("USLO");
        UserModifyDto userModifyDto = messageHelper.getMessage(message,UserModifyDto.class);
        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.EMAIL_VERIFICATION)
                .orElseThrow(() -> new NotFoundException(String
                        .format("Notification of this type not found.")));
        System.out.println(userModifyDto.getFirstName() +" " +userModifyDto.getLastName() + " " +userModifyDto.getEmail() +" " +notificationType);
        Notification notification = new VerifyEmailNotification(userModifyDto.getFirstName(), userModifyDto.getLastName(), userModifyDto.getEmail(), notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(userModifyDto.getEmail(), "Email verification", notification);
    }
}
