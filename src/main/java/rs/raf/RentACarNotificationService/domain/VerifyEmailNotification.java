package rs.raf.RentACarNotificationService.domain;

import jdk.jfr.Enabled;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class VerifyEmailNotification extends Notification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public VerifyEmailNotification(String firstName, String lastName, String email, NotificationType type) {
        super(email, type);
        this.message = String.format(super.getMessage(), firstName, lastName, email);
    }

    public VerifyEmailNotification() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
