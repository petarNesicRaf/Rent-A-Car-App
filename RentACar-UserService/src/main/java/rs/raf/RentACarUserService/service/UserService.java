package rs.raf.RentACarUserService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.RentACarUserService.dto.*;

public interface UserService {

    void saveNewPassword(String email);
    void changePassword(String email, UserPasswordDto userPasswordDto);
    void verifyMail(String email);
    Page<UserDto> getAll(Pageable pageable);
    UserDto add(UserModifyDto userModifyDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    UserDto update(Long id, UserModifyDto userModifyDto);
    UserDto get(Long id);
    void ban(Long id);
    void unban(Long id);
    void delete(Long id);
    void updateDays(Long id, Integer daysRented);

}
