package rs.raf.RentACarNotificationService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

public class NotificationSimpleDto {
    private Long id;
    private String message;
    private String email;

    private String type;


    public NotificationSimpleDto() {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
