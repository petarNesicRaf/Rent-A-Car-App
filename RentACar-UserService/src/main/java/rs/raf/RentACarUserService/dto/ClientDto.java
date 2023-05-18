package rs.raf.RentACarUserService.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ClientDto extends UserDto{

    private Long passportNumber;
    private Integer daysRented;
    private RankDto rank;

    public ClientDto(Long id, String username, String email, String phoneNumber, Date birthDate, String firstName, String lastName, Long passportNumber, Integer daysRented, RankDto rank) {
        super(id, username, email, phoneNumber, birthDate, firstName, lastName);
        this.passportNumber = passportNumber;
        this.daysRented = daysRented;
        this.rank = rank;
    }
}
