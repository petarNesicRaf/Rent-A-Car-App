package rs.raf.RentACarNotificationService.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rs.raf.RentACarNotificationService.domain.NotificationType;
import rs.raf.RentACarNotificationService.dto.NotificationTypeDto;
import rs.raf.RentACarNotificationService.dto.NotificationTypeUpdateDto;
import rs.raf.RentACarNotificationService.exceptions.NotFoundException;
import rs.raf.RentACarNotificationService.mapper.NotificationTypeMapper;
import rs.raf.RentACarNotificationService.repository.NotificationTypeRepository;
import rs.raf.RentACarNotificationService.service.NotificationTypeService;

import javax.transaction.Transactional;

@Service
@Transactional
public class NotificationTypeServiceImpl implements NotificationTypeService {
    private NotificationTypeRepository notificationTypeRepository;
    private NotificationTypeMapper notificationTypeMapper;

    public NotificationTypeServiceImpl(NotificationTypeRepository notificationTypeRepository, NotificationTypeMapper notificationTypeMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.notificationTypeMapper = notificationTypeMapper;
    }

    @Override
    public Page<NotificationTypeDto> findAll(Pageable pageable) {
        return notificationTypeRepository.findAll(pageable)
                .map(notificationTypeMapper::notificationTypeToNotificationTypeDto);
    }

    @Override
    public NotificationTypeDto updateNotificationType(Long id, NotificationTypeUpdateDto notificationTypeUpdateDto) {
        NotificationType notificationType = notificationTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Notification type with id: %d not found.", id)));


        notificationType.setMessage(notificationTypeUpdateDto.getMessage());
        return notificationTypeMapper.notificationTypeToNotificationTypeDto(notificationTypeRepository.save(notificationType));
    }

    @Override
    public void deleteNotificationType(Long id) {
        NotificationType notificationType = notificationTypeRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Notification type with id: %d not found.", id)));

        notificationTypeRepository.delete(notificationType);
    }


}
