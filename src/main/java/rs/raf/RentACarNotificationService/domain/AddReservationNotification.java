package rs.raf.RentACarNotificationService.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AddReservationNotification extends Notification{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public AddReservationNotification(String firstName, String lastName, String email, NotificationType type) {
        super(email, type);
        this.message = String.format(super.getMessage(), firstName, lastName);
    }

    public AddReservationNotification()
    {

    }
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
