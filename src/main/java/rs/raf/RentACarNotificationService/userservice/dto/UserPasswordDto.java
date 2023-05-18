package rs.raf.RentACarNotificationService.userservice.dto;

public class UserPasswordDto {
    private String password;
    private String email;

    public UserPasswordDto() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
