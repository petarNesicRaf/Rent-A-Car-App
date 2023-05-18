package rs.raf.RentACarNotificationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.RentACarNotificationService.domain.Notification;
import rs.raf.RentACarNotificationService.domain.NotificationType;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findNotificationByEmail(String email);
}
