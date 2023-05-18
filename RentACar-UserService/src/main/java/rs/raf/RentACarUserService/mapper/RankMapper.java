package rs.raf.RentACarUserService.mapper;

import org.springframework.stereotype.Component;
import rs.raf.RentACarUserService.domain.Rank;
import rs.raf.RentACarUserService.dto.*;

@Component
public class RankMapper {

    public RankDto rankToRankDto(Rank rank){
        return new RankDto(rank.getId(), rank.getName(), rank.getDescription(), rank.getMinDaysRented(), rank.getMaxDaysRented(), rank.getDiscount());
    }

    public Rank rankModifyDtoToRank(RankModifyDto rankModifyDto){
        return new Rank(rankModifyDto.getName(), rankModifyDto.getDescription(), rankModifyDto.getMinDaysRented(), rankModifyDto.getMaxDaysRented(),
                rankModifyDto.getDiscount());
    }

    public void updateRank(Rank rank, RankModifyDto rankModifyDto){
        rank.setName(rankModifyDto.getName());
        rank.setDescription(rankModifyDto.getDescription());
        rank.setMinDaysRented(rankModifyDto.getMinDaysRented());
        rank.setMaxDaysRented(rankModifyDto.getMaxDaysRented());
        rank.setDiscount(rankModifyDto.getDiscount());
    }
}
