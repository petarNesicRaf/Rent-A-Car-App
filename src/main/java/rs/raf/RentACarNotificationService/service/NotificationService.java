package rs.raf.RentACarNotificationService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.RentACarNotificationService.dto.NotificationDto;
import rs.raf.RentACarNotificationService.dto.NotificationSimpleDto;

public interface NotificationService {

    Page<NotificationSimpleDto> findAll(Pageable pageable);

    Page<NotificationDto> findClientNotifications(Long id, Pageable pageable);

    Page<NotificationDto> findManagerNotifications(Long id, Pageable pageable);

    Page<NotificationSimpleDto> findByEmail(String email, Pageable pageable);

    Page<NotificationSimpleDto> findByType(String type, Pageable pageable);

    Page<NotificationSimpleDto> findBetweenDates(String date1, String date2, Pageable pageable);

}
