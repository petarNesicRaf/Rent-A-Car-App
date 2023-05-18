package rs.raf.RentACarNotificationService.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.RentACarNotificationService.dto.NotificationTypeDto;
import rs.raf.RentACarNotificationService.dto.NotificationTypeUpdateDto;
import rs.raf.RentACarNotificationService.service.NotificationTypeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/notification-type")
public class NotificationTypeController {
    private NotificationTypeService notificationTypeService;

    public NotificationTypeController(NotificationTypeService notificationTypeService) {
        this.notificationTypeService = notificationTypeService;
    }

    @GetMapping
    public ResponseEntity<Page<NotificationTypeDto>> getAllNotificationTypes(@RequestHeader("Authorization") String authorization,
                                                                             Pageable pageable) {
        return new ResponseEntity<>(notificationTypeService.findAll(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<NotificationTypeDto> updateNotificationType(@RequestHeader("Authorization") String authorization,
                                                                      @PathVariable("id") Long id, @RequestBody @Valid NotificationTypeUpdateDto notificationTypeUpdateDto) {
        return ResponseEntity.ok(notificationTypeService.updateNotificationType(id, notificationTypeUpdateDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotificationType(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id) {
        notificationTypeService.deleteNotificationType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
