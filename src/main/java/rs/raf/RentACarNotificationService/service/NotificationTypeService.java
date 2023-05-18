package rs.raf.RentACarNotificationService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.RentACarNotificationService.dto.NotificationTypeDto;
import rs.raf.RentACarNotificationService.dto.NotificationTypeUpdateDto;

public interface NotificationTypeService {

    Page<NotificationTypeDto> findAll(Pageable pageable);

    NotificationTypeDto updateNotificationType(Long id, NotificationTypeUpdateDto notificationTypeUpdateDto);

    void deleteNotificationType(Long id);

}
