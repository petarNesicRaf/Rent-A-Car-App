package rs.raf.RentACarNotificationService.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import rs.raf.RentACarNotificationService.domain.NotificationType;
import rs.raf.RentACarNotificationService.domain.Type;
import rs.raf.RentACarNotificationService.repository.NotificationTypeRepository;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private NotificationTypeRepository notificationTypeRepository;

    public TestDataRunner(NotificationTypeRepository notificationTypeRepository) {
        this.notificationTypeRepository = notificationTypeRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        notificationTypeRepository.save(new NotificationType(Type.EMAIL_VERIFICATION,"Hi, %s %s. To complete your registration, please click the following link: http://localhost:8080/api/user/verifyMail/%s."));
        notificationTypeRepository.save(new NotificationType(Type.CHANGE_PASSWORD,"To change your password, please click the following link: http://localhost:8080/api/user/changePassword/%s."));
        notificationTypeRepository.save(new NotificationType(Type.ADD_RESERVATION, "Hi, %s %s. You have successfully reserved a car from our company."));
        notificationTypeRepository.save(new NotificationType(Type.DELETE_RESERVATION,"Hi, you canceled your car reservation."));

    }

}
