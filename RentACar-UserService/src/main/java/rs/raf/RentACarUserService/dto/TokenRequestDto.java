package rs.raf.RentACarUserService.dto;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class TokenRequestDto {

    @Email
    private String email;
    @NotBlank
    private String password;
}
