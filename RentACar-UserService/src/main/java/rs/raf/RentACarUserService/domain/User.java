package rs.raf.RentACarUserService.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Users", indexes = {@Index(columnList = "username", unique = true), @Index(columnList = "email", unique = true)})
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String firstName;
    private String lastName;
    @ManyToOne(optional = false)
    private Role role;
    private Long passportNumber;
    private Integer daysRented;
    @ManyToOne
    private Rank rank;
    private String companyName;
    private Date hireDate;
    private boolean verifiedEmail = false;
    private boolean banned = false;


    public User(String username, String password, String email, String phoneNumber, Date birthDate, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
