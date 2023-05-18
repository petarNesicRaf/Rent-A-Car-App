package rs.raf.RentACarUserService.service.imp;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.raf.RentACarUserService.domain.Rank;
import rs.raf.RentACarUserService.dto.RankModifyDto;
import rs.raf.RentACarUserService.dto.RankDto;
import rs.raf.RentACarUserService.error.CustomException;
import rs.raf.RentACarUserService.error.ErrorType;
import rs.raf.RentACarUserService.mapper.RankMapper;
import rs.raf.RentACarUserService.repository.RankRepository;
import rs.raf.RentACarUserService.service.RankService;

@AllArgsConstructor
@Service
@Transactional
public class RankServiceImp implements RankService {

    private RankRepository rankRepository;
    private RankMapper rankMapper;

    @Override
    public Page<RankDto> getAll(Pageable pageable) {
        return rankRepository.findAll(pageable).map(rankMapper::rankToRankDto);
    }

    @Override
    public RankDto add(RankModifyDto rankModifyDto) {
        Rank rank = rankMapper.rankModifyDtoToRank(rankModifyDto);
        rankRepository.save(rank);
        return rankMapper.rankToRankDto(rank);
    }

    @Override
    public RankDto get(Long id) {
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new CustomException("Rank with id: " + id +  " not found.", ErrorType.NOT_FOUND));
        return rankMapper.rankToRankDto(rank);
    }

    @Override
    public RankDto update(Long id, RankModifyDto rankModifyDto) {
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new CustomException("Rank with id: " + id +  " not found.", ErrorType.NOT_FOUND));
        rankMapper.updateRank(rank, rankModifyDto);
        return rankMapper.rankToRankDto(rank);
    }

    @Override
    public void delete(Long id) {
        Rank rank = rankRepository.findById(id).orElseThrow(() -> new CustomException("Rank with id: " + id +  " not found.", ErrorType.NOT_FOUND));
        rankRepository.delete(rank);
    }
}
