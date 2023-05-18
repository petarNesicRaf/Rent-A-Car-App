package rs.raf.RentACarUserService.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.RentACarUserService.dto.RankModifyDto;
import rs.raf.RentACarUserService.dto.RankDto;
import rs.raf.RentACarUserService.security.CheckRole;
import rs.raf.RentACarUserService.service.RankService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/rank")
public class RankController {

    private RankService rankService;

    @CheckRole(roles = {"ADMIN", "MANAGER", "CLIENT"})
    @GetMapping
    public ResponseEntity<Page<RankDto>> getAll(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(rankService.getAll(pageable), HttpStatus.OK);
    }

    @CheckRole(roles = {"ADMIN"})
    @PostMapping
    public ResponseEntity<RankDto> add(@RequestHeader("Authorization") String authorization, @RequestBody @Valid RankModifyDto rankModifyDto){
        return new ResponseEntity<>(rankService.add(rankModifyDto), HttpStatus.CREATED);
    }

    @CheckRole(roles = {"ADMIN", "MANAGER", "CLIENT"})
    @GetMapping("/{id}")
    public ResponseEntity<RankDto> get(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        return new ResponseEntity<>(rankService.get(id), HttpStatus.OK);
    }

    @CheckRole(roles = {"ADMIN"})
    @PostMapping("/{id}")
    public ResponseEntity<RankDto> update(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id,
                                       @RequestBody @Valid RankModifyDto rankModifyDto){
        return new ResponseEntity<>(rankService.update(id, rankModifyDto), HttpStatus.OK);
    }

    @CheckRole(roles = {"ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader("Authorization") String authorization, @PathVariable("id") Long id){
        rankService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
