package rs.raf.RentACarNotificationService.listener;

import ch.qos.logback.core.net.server.Client;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.*;
import rs.raf.RentACarNotificationService.exceptions.NotFoundException;
import rs.raf.RentACarNotificationService.helper.MessageHelper;
import rs.raf.RentACarNotificationService.repository.NotificationRepository;
import rs.raf.RentACarNotificationService.repository.NotificationTypeRepository;
import rs.raf.RentACarNotificationService.service.EmailService;
import rs.raf.RentACarNotificationService.userservice.dto.ClientDto;
import rs.raf.RentACarNotificationService.userservice.dto.UserPasswordDto;

import javax.jms.JMSException;
import javax.jms.Message;

@Component
public class DeleteReservationListener {

    private MessageHelper messageHelper;
    private NotificationRepository notificationRepository;
    private NotificationTypeRepository notificationTypeRepository;
    private EmailService emailService;

    @JmsListener(destination = "${destination.deleteReservation}", concurrency = "5-10")
    public void deleteReservation(Message message) throws JMSException {
        ClientDto clientDto = messageHelper.getMessage(message, ClientDto.class);

        NotificationType notificationType = notificationTypeRepository
                .findNotificationTypeByType(Type.DELETE_RESERVATION)
                .orElseThrow(()->new NotFoundException("Notification type not found"));
        Notification notification = new DeleteReservationNotification(clientDto.getEmail(),notificationType);
        notificationRepository.save(notification);
        emailService.sendSimpleMessage(clientDto.getEmail(), "Reservation canceled", notification);

    }

}
