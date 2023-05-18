package rs.raf.RentACarNotificationService.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ManagerModifyDto extends UserModifyDto{
    @NotBlank
    private String companyName;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date hireDate;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }
}
