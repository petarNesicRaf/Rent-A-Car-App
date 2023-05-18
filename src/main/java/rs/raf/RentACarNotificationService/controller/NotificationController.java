package rs.raf.RentACarNotificationService.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.RentACarNotificationService.dto.NotificationDto;
import rs.raf.RentACarNotificationService.dto.NotificationSimpleDto;
import rs.raf.RentACarNotificationService.service.NotificationService;
@RestController
@RequestMapping("/notification")
public class NotificationController {
    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping
    //@CheckRole(roles = {"ADMIN"})
    public ResponseEntity<Page<NotificationSimpleDto>> getAllNotifications(Pageable pageable) {
        return new ResponseEntity<>(notificationService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/client/{id}")
    //@CheckRole(roles = {"CLIENT"})
    public ResponseEntity<Page<NotificationDto>> getClientNotifications(@RequestHeader("Authorization") String authorization,
                                                                        @PathVariable("id") Long id, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findClientNotifications(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/manager/{id}")
    //@CheckRole(roles = {"ADMIN"})
    public ResponseEntity<Page<NotificationDto>> getManagerNotifications(@RequestHeader("Authorization") String authorization,
                                                                         @PathVariable("id") Long id, Pageable pageable) {
        return new ResponseEntity<>(notificationService.findManagerNotifications(id, pageable), HttpStatus.OK);
    }

    @GetMapping("/filter/email={email}")
    //@CheckRole(roles = {"ADMIN"})
    public ResponseEntity<Page<NotificationSimpleDto>> getNotificationsByEmail(@RequestHeader("Authorization") String authorization,
                                                                         @PathVariable("email") String email,
                                                                         Pageable pageable) {
        return new ResponseEntity<>(notificationService.findByEmail(email, pageable), HttpStatus.OK);
    }

    @GetMapping("/filter/type={type}")
    //@CheckRole(roles = {"ADMIN"})
    public ResponseEntity<Page<NotificationSimpleDto>> getNotificationsByType(@RequestHeader("Authorization") String authorization,
                                                                        @PathVariable("type") String type,
                                                                        Pageable pageable) {
        return new ResponseEntity<>(notificationService.findByType(type, pageable), HttpStatus.OK);
    }

    @GetMapping("/filter/between={date1},{date2}")
    //@CheckRole(roles = {"ADMIN"})
    public ResponseEntity<Page<NotificationSimpleDto>> getNotificationsBetweenDates(@RequestHeader("Authorization") String authorization,
                                                                              @PathVariable("date1") String date1, @PathVariable("date2") String date2,
                                                                              Pageable pageable) {
        return new ResponseEntity<>(notificationService.findBetweenDates(date1, date2, pageable), HttpStatus.OK);
    }
}
