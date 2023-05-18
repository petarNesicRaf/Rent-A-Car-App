package rs.raf.RentACarUserService.dto;

import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class RankModifyDto {

    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @NotNull
    private Integer minDaysRented;
    @NotNull
    private Integer maxDaysRented;
    @NotNull
    private Integer discount;
}
