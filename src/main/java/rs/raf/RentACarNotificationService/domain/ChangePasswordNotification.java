package rs.raf.RentACarNotificationService.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChangePasswordNotification extends Notification{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    public ChangePasswordNotification(String email, NotificationType type)
    {
        super(email,type);
        this.message = String.format(super.getMessage(),email);
    }

    public ChangePasswordNotification()
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