package rs.raf.RentACarUserService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RankDto {

    private Long id;
    private String name;
    private String description;
    private Integer minDaysRented;
    private Integer maxDaysRented;
    private Integer discount;
}
