package rs.raf.RentACarUserService.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rs.raf.RentACarUserService.dto.RankModifyDto;
import rs.raf.RentACarUserService.dto.RankDto;

public interface RankService {

    Page<RankDto> getAll(Pageable pageable);
    RankDto add(RankModifyDto rankModifyDto);
    RankDto get(Long id);
    RankDto update(Long id, RankModifyDto rankModifyDto);
    void delete(Long id);
}
