package rs.raf.RentACarUserService.service.imp;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.raf.RentACarUserService.domain.User;
import rs.raf.RentACarUserService.dto.*;
import rs.raf.RentACarUserService.error.CustomException;
import rs.raf.RentACarUserService.error.ErrorType;
import rs.raf.RentACarUserService.listener.MessageHelper;
import rs.raf.RentACarUserService.mapper.UserMapper;
import rs.raf.RentACarUserService.repository.UserRepository;
import rs.raf.RentACarUserService.security.service.TokenService;
import rs.raf.RentACarUserService.service.UserService;


@Service
@Transactional
public class UserServiceImp implements UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;
    private TokenService tokenService;
    private MessageHelper messageHelper;
    private String addClientDestination;
    private JmsTemplate jmsTemplate;

    private String changePasswordDestination;
    public UserServiceImp(UserRepository userRepository, UserMapper userMapper, TokenService tokenService,
                          MessageHelper messageHelper, @Value("${destination.addClient}") String addClientDestination,
                          JmsTemplate jmsTemplate, @Value("${destination.changePassword}") String changePasswordDestination) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tokenService = tokenService;
        this.messageHelper = messageHelper;
        this.addClientDestination = addClientDestination;
        this.jmsTemplate = jmsTemplate;
        this.changePasswordDestination = changePasswordDestination;
    }

    @Override
    public Page<UserDto> getAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public UserDto add(UserModifyDto userModifyDto) {
        User user = userMapper.userRequestDtoToUser(userModifyDto);
        userRepository.save(user);

        jmsTemplate.convertAndSend(addClientDestination, messageHelper.createTextMessage(userModifyDto));
        return userMapper.userToUserDto(user);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        String email = tokenRequestDto.getEmail();
        String password = tokenRequestDto.getPassword();
        User user = userRepository.findUserByEmailAndPassword(email, password).orElseThrow(() -> new CustomException(
                "User with email: " + email + " and password: " + password + " not found.", ErrorType.NOT_FOUND));

        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getName());

        if(user.isBanned())
            return new TokenResponseDto("banned");

        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public UserDto update(Long id, UserModifyDto userModifyDto){
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User with id: " + id + " not found.", ErrorType.NOT_FOUND));
        userMapper.updateUser(user, userModifyDto);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto get(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User with id: " + id + " not found.", ErrorType.NOT_FOUND));
        return userMapper.userToUserDto(user);
    }

    @Override
    public void ban(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User with id: " + id + " not found.", ErrorType.NOT_FOUND));
        user.setBanned(true);
    }

    @Override
    public void unban(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User with id: " + id + " not found.", ErrorType.NOT_FOUND));
        user.setBanned(false);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User with id: " + id + " not found.", ErrorType.NOT_FOUND));
        userRepository.delete(user);
    }

    @Override
    public void updateDays(Long id, Integer daysRented) {
        User user = userRepository.findById(id).orElseThrow(() -> new CustomException("User with id: " + id + " not found.", ErrorType.NOT_FOUND));
        Integer newDaysRented = user.getDaysRented() + daysRented;
        user.setDaysRented(newDaysRented);
        user.setRank(userMapper.getRank(newDaysRented));
    }


    @Override
    public void saveNewPassword(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()->new CustomException("User with email:" + email + " has not been found.",ErrorType.NOT_FOUND));
        userRepository.save(user);
    }

    @Override
    public void changePassword(String email, UserPasswordDto userPasswordDto) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new CustomException("User with this email: " + email +" has not been found",ErrorType.NOT_FOUND));
        jmsTemplate.convertAndSend(changePasswordDestination, messageHelper.createTextMessage(userPasswordDto));
        user.setPassword(userPasswordDto.getPassword());
    }

    @Override
    public void verifyMail(String email) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(()-> new CustomException("User with this email: "+ email + " has not been found",ErrorType.NOT_FOUND));
        user.setVerifiedEmail(true);
    }

}
