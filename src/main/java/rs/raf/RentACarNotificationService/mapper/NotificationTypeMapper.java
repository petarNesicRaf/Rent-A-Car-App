package rs.raf.RentACarNotificationService.mapper;

import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.NotificationType;
import rs.raf.RentACarNotificationService.dto.NotificationTypeDto;
import rs.raf.RentACarNotificationService.repository.NotificationTypeRepository;

@Component
public class NotificationTypeMapper {
    private NotificationTypeRepository notificationTypeRepository;

    public NotificationTypeMapper(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    public NotificationTypeDto notificationTypeToNotificationTypeDto(NotificationType notificationType) {
        NotificationTypeDto notificationTypeDto = new NotificationTypeDto();
        notificationTypeDto.setType(notificationType.getType());
        notificationTypeDto.setMessage(notificationType.getMessage());
        notificationTypeDto.setId(notificationType.getId());
        return notificationTypeDto;
    }
}
