package rs.raf.RentACarNotificationService.mapper;

import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.Notification;
import rs.raf.RentACarNotificationService.dto.NotificationDto;
import rs.raf.RentACarNotificationService.dto.NotificationSimpleDto;
import rs.raf.RentACarNotificationService.repository.NotificationRepository;

@Component
public class NotificationMapper {

    private NotificationRepository notificationRepository;

    public NotificationMapper(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }
    public NotificationSimpleDto noticationToNotificationSimpleDto(Notification notification)
    {
        NotificationSimpleDto notificationDto = new NotificationSimpleDto();
        notificationDto.setEmail(notification.getEmail());
        notificationDto.setId(notification.getId());
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setType(notification.getType().getType().toString());
        return notificationDto;
    }
    public NotificationDto notificationToNotificationDto(Notification notification) {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setEmail(notification.getEmail());
        notificationDto.setId(notification.getId());
        notificationDto.setMessage(notification.getMessage());
        notificationDto.setType(notification.getType());
        notificationDto.setInstant(notification.getInstant());
        return notificationDto;
    }

}
