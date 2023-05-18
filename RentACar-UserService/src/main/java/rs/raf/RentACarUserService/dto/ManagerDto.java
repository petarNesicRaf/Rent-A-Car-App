package rs.raf.RentACarUserService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import java.util.Date;

@Getter
public class ManagerDto extends UserDto{

    private String companyName;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date hireDate;

    public ManagerDto(Long id, String username, String email, String phoneNumber, Date birthDate, String firstName, String lastName, String companyName, Date hireDate) {
        super(id, username, email, phoneNumber, birthDate, firstName, lastName);
        this.companyName = companyName;
        this.hireDate = hireDate;
    }
}
