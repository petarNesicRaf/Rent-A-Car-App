package rs.raf.RentACarNotificationService.userservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ClientDto {

    private Long id;
    private String username;
    private String email;
    private String phoneNumber;
    @JsonFormat(pattern = "dd.MM.yyyy")
    private Date birthDate;
    private String firstName;
    private String lastName;
    private Long passportNumber;
    private Integer daysRented;
    private RankDto rank;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(Long passportNumber) {
        this.passportNumber = passportNumber;
    }

    public Integer getDaysRented() {
        return daysRented;
    }

    public void setDaysRented(Integer daysRented) {
        this.daysRented = daysRented;
    }

    public RankDto getRank() {
        return rank;
    }

    public void setRank(RankDto rank) {
        this.rank = rank;
    }
}
