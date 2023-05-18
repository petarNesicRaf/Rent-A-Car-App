package rs.raf.RentACarNotificationService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import rs.raf.RentACarNotificationService.domain.NotificationType;

import java.time.Instant;

public class NotificationDto {
    private Long id;
    private String message;
    private String email;
    private NotificationType type;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy",timezone = "GMT+1")
    private Instant instant;

    public NotificationDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

}

