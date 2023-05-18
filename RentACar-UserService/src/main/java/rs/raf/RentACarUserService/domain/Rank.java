package rs.raf.RentACarUserService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Rank {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Integer minDaysRented;
    private Integer maxDaysRented;
    private Integer discount;

    public Rank(String name, String description, Integer minDaysRented, Integer maxDaysRented, Integer discount) {
        this.name = name;
        this.description = description;
        this.minDaysRented = minDaysRented;
        this.maxDaysRented = maxDaysRented;
        this.discount = discount;
    }
}
