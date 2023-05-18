package rs.raf.RentACarUserService.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.RentACarUserService.dto.*;
import rs.raf.RentACarUserService.security.CheckID;
import rs.raf.RentACarUserService.security.CheckRole;
import rs.raf.RentACarUserService.service.UserService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @CheckRole(roles = {"ADMIN"})
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(userService.getAll(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody @Valid UserModifyDto userModifyDto){
        return new ResponseEntity<>(userService.add(userModifyDto), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody TokenRequestDto tokenRequestDto) {
        return new ResponseEntity<>(userService.login(tokenRequestDto), HttpStatus.OK);
    }

    //@CheckID
    //@CheckRole(roles = {"ADMIN"})
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(userService.get(id), HttpStatus.OK);
    }

    //@CheckID
    @CheckRole(roles = {"ADMIN", "CLIENT", "MANAGER"})
    @PostMapping("/{id}")
    public ResponseEntity<UserDto> update(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id,
                                          @RequestBody @Valid UserModifyDto userModifyDto){
        return new ResponseEntity<>(userService.update(id, userModifyDto), HttpStatus.OK);
    }

    @CheckRole(roles = {"ADMIN"})
    @PostMapping("/{id}/ban")
    public ResponseEntity<Void> ban(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        userService.ban(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @CheckRole(roles = {"ADMIN"})
    @PostMapping("/{id}/unban")
    public ResponseEntity<Void> unban(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        userService.unban(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@CheckID
    @CheckRole(roles = {"ADMIN", "CLIENT", "MANAGER"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@CheckRole(roles = {"ADMIN"})
    @PostMapping("/{id}/days-rented")
    public ResponseEntity<Void> updateDays(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id, @RequestBody Integer daysRented){
        userService.updateDays(id, daysRented);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@CheckID
    @GetMapping("changePassword/{email}")
    public ResponseEntity<Void> changePassword(@PathVariable("email") String email){
        userService.saveNewPassword(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@CheckID
    @PutMapping("/{email}/updatePassword")
    public ResponseEntity<Void> updatePassword(@RequestHeader("Authorization") String authorization, @PathVariable("email") String email,
                                               @RequestBody @Valid UserPasswordDto userPasswordDto) {
        userService.changePassword(email, userPasswordDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //@CheckID
    @GetMapping("/verifyMail/{email}")
    public ResponseEntity<Void> verifyMail(@PathVariable("email") String email) {
        userService.verifyMail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
