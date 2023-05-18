package rs.raf.RentACarNotificationService.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import rs.raf.RentACarNotificationService.domain.Notification;
import rs.raf.RentACarNotificationService.dto.NotificationDto;
import rs.raf.RentACarNotificationService.dto.NotificationSimpleDto;
import rs.raf.RentACarNotificationService.exceptions.NotFoundException;
import rs.raf.RentACarNotificationService.mapper.NotificationMapper;
import rs.raf.RentACarNotificationService.repository.NotificationRepository;
import rs.raf.RentACarNotificationService.service.NotificationService;
import rs.raf.RentACarNotificationService.userservice.dto.UserDto;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;
    private RestTemplate userServiceRestTemplate;


    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper, RestTemplate userServiceRestTemplate) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.userServiceRestTemplate = userServiceRestTemplate;
    }

    @Override
    public Page<NotificationSimpleDto> findAll(Pageable pageable) {
        return notificationRepository.findAll(pageable)
                .map(notificationMapper::noticationToNotificationSimpleDto);
    }

    @Override
    public Page<NotificationDto> findClientNotifications(Long id, Pageable pageable) {
        ResponseEntity<UserDto> userDtoResponseEntity = null;
        try {

            userDtoResponseEntity = userServiceRestTemplate.exchange("/user/"
                    + id, HttpMethod.GET, null, UserDto.class);

        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().equals(HttpStatus.NOT_FOUND))
                throw new NotFoundException(String.format("User with id: %d not found.", id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String email = userDtoResponseEntity.getBody().getEmail();

        System.out.println(email);
        return null;
    }

    @Override
    public Page<NotificationDto> findManagerNotifications(Long id, Pageable pageable) {
        return null;
    }

    @Override
    public Page<NotificationSimpleDto> findByEmail(String email, Pageable pageable) {
        List<Notification> notificationList;
        notificationList = notificationRepository.findNotificationByEmail(email);

        List<NotificationSimpleDto> foundNotifications = new ArrayList<>();

        for (Notification notification : notificationList)
            foundNotifications.add(notificationMapper.noticationToNotificationSimpleDto(notification));

        Page<NotificationSimpleDto> notificationDtoPage = new PageImpl<>(foundNotifications);
        return notificationDtoPage;
    }

    @Override
    public Page<NotificationSimpleDto> findByType(String type, Pageable pageable) {
        List<Notification> notificationList;
        notificationList = notificationRepository.findAll();


        List<Notification> foundNotifications = new ArrayList<>();

        for (Notification notification : notificationList) {
            if (notification.getType().getType().toString().equalsIgnoreCase(type)) {
                foundNotifications.add(notification);
            }
        }

        List<NotificationSimpleDto> toReturn = new ArrayList<>();

        for (Notification notification : foundNotifications)
            toReturn.add(notificationMapper.noticationToNotificationSimpleDto(notification));

        Page<NotificationSimpleDto> notificationDtoPage = new PageImpl<>(toReturn);
        return notificationDtoPage;
    }

    @Override
    public Page<NotificationSimpleDto> findBetweenDates(String date1, String date2, Pageable pageable) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate date1Formatted = LocalDate.parse(date1, dateTimeFormatter);
        LocalDate date2Formatted = LocalDate.parse(date2, dateTimeFormatter);


        List<Notification> notificationList = new ArrayList<>();
        notificationList = notificationRepository.findAll();

        List<Notification> foundNotifications = new ArrayList<>();

        for (Notification notification : notificationList) {
            LocalDateTime ldt = notification.getInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            LocalDate notificationDateFormatted = LocalDate.parse(ldt.format(dateTimeFormatter), dateTimeFormatter);

            if (notificationDateFormatted.isAfter(date1Formatted) && notificationDateFormatted.isBefore(date2Formatted)) {
                foundNotifications.add(notification);
            }

        }

        List<NotificationSimpleDto> toReturn = new ArrayList<>();

        for (Notification notification : foundNotifications)
            toReturn.add(notificationMapper.noticationToNotificationSimpleDto(notification));

        Page<NotificationSimpleDto> notificationDtoPage = new PageImpl<>(toReturn);
        return notificationDtoPage;
    }
}

