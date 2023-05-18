package rs.raf.RentACarNotificationService.userservice.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClientModifyDto extends UserModifyDto{

    @NotNull
    private Long passportNumber;

    public Long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
    }
}
