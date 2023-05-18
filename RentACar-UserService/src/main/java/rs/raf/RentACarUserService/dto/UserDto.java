package rs.raf.RentACarUserService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    private String firstName;
    private String lastName;
}
