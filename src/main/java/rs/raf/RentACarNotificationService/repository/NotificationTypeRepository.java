package rs.raf.RentACarNotificationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.raf.RentACarNotificationService.domain.NotificationType;
import rs.raf.RentACarNotificationService.domain.Type;
import rs.raf.RentACarNotificationService.dto.NotificationTypeDto;

import java.util.Optional;

public interface NotificationTypeRepository extends JpaRepository<NotificationType, Long> {
    Optional<NotificationType> findNotificationTypeByType(Type type);

}
