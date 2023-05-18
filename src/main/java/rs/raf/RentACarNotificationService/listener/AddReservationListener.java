package rs.raf.RentACarNotificationService.listener;

import javassist.NotFoundException;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.*;
import rs.raf.RentACarNotificationService.helper.MessageHelper;
import rs.raf.RentACarNotificationService.repository.NotificationRepository;
import rs.raf.RentACarNotificationService.repository.NotificationTypeRepository;
import rs.raf.RentACarNotificationService.service.EmailService;
import rs.raf.RentACarNotificationService.userservice.dto.ClientDto;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class AddReservationListener {

    private MessageHelper messageHelper;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;
    private EmailService emailService;

    public AddReservationListener(MessageHelper messageHelper, NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository, EmailService emailService) {
        this.messageHelper = messageHelper;
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
        this.emailService = emailService;
    }
    @JmsListener(destination = "${destination.addReservation}", concurrency = "5-10")
    public void addReservation(Message message) throws JMSException, NotFoundException {
        ClientDto clientDto = messageHelper.getMessage(message, ClientDto.class);
        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.ADD_RESERVATION)
                .orElseThrow(() -> new NotFoundException(String
                .format("Notification of this type not found.")));
        System.out.println(clientDto.getFirstName()+ " "+ clientDto.getLastName());
        Notification notification = new AddReservationNotification(clientDto.getFirstName(), clientDto.getLastName(), clientDto.getEmail(), notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(clientDto.getEmail(), "Reservation confirmed", notification);
    }
}
