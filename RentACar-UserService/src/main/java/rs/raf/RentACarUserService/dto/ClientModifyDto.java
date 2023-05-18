package rs.raf.RentACarUserService.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
public class ClientModifyDto extends UserModifyDto {

    @NotNull
    private Long passportNumber;
}
