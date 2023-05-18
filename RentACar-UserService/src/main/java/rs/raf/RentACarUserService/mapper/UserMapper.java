package rs.raf.RentACarUserService.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import rs.raf.RentACarUserService.domain.Rank;
import rs.raf.RentACarUserService.domain.User;
import rs.raf.RentACarUserService.dto.*;
import rs.raf.RentACarUserService.repository.RankRepository;
import rs.raf.RentACarUserService.repository.RoleRepository;

import java.util.List;

@AllArgsConstructor
@Component
public class UserMapper {

    private RoleRepository roleRepository;
    private RankRepository rankRepository;
    private RankMapper rankMapper;

    public User userRequestDtoToUser(UserModifyDto userModifyDto){
        User user = new User(userModifyDto.getUsername(), userModifyDto.getPassword(), userModifyDto.getEmail(), userModifyDto.getPhoneNumber(),
                            userModifyDto.getBirthDate(), userModifyDto.getFirstName(), userModifyDto.getLastName());
        user.setVerifiedEmail(false);
        if (userModifyDto instanceof ClientModifyDto){
            user.setRole(roleRepository.findRoleByName("CLIENT").get());
            user.setPassportNumber(((ClientModifyDto) userModifyDto).getPassportNumber());
            user.setDaysRented(0);
            user.setRank(getRank(0));
        } else {
            user.setRole(roleRepository.findRoleByName("MANAGER").get());
            ManagerModifyDto managerModifyDto = (ManagerModifyDto) userModifyDto;
            user.setCompanyName(managerModifyDto.getCompanyName());
            user.setHireDate(managerModifyDto.getHireDate());
        }

        return user;
    }

    public UserDto userToUserDto(User user){
        if (user.getRole().getName().equals("CLIENT"))
            return new ClientDto(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getBirthDate(), user.getFirstName(),
                    user.getLastName(), user.getPassportNumber(), user.getDaysRented(), rankMapper.rankToRankDto(user.getRank()));
        else if(user.getRole().getName().equals("MANAGER"))
            return new ManagerDto(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getBirthDate(), user.getFirstName(),
                user.getLastName(), user.getCompanyName(), user.getHireDate());
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNumber(), user.getBirthDate(), user.getFirstName(),
                user.getLastName());
    }

    public void updateUser(User user, UserModifyDto userModifyDto){
        user.setUsername(userModifyDto.getUsername());
        user.setPassword(userModifyDto.getPassword());
        user.setEmail(userModifyDto.getEmail());
        user.setPhoneNumber(userModifyDto.getPhoneNumber());
        user.setBirthDate(userModifyDto.getBirthDate());
        user.setFirstName(userModifyDto.getFirstName());
        user.setLastName(userModifyDto.getLastName());

        if (userModifyDto instanceof ClientModifyDto)
            user.setPassportNumber(((ClientModifyDto) userModifyDto).getPassportNumber());
        else {
            ManagerModifyDto managerModifyDto = (ManagerModifyDto) userModifyDto;
            user.setCompanyName(managerModifyDto.getCompanyName());
            user.setHireDate(managerModifyDto.getHireDate());
        }
    }

    public Rank getRank(Integer daysRented){
        List<Rank> rankList = rankRepository.findAll();
        return rankList.stream().filter(rank -> rank.getMaxDaysRented() > daysRented && rank.getMinDaysRented() <= daysRented).findAny().get();
    }
}
