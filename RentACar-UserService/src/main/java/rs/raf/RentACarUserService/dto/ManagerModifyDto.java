package rs.raf.RentACarUserService.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class ManagerModifyDto extends UserModifyDto {

    @NotBlank
    private String companyName;
    @NotNull
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date hireDate;
}
