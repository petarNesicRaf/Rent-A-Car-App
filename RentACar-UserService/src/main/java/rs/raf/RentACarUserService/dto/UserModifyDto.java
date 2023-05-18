package rs.raf.RentACarUserService.dto;


import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({@JsonSubTypes.Type(value = ClientModifyDto.class, name = "client"), @JsonSubTypes.Type(value = ManagerModifyDto.class, name = "manager")})
public abstract class UserModifyDto {


    @NotBlank
    private String username;
    @Length(min = 8, max = 20)
    private String password;
    @Email
    private String email;
    @NotBlank
    private String phoneNumber;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
}
