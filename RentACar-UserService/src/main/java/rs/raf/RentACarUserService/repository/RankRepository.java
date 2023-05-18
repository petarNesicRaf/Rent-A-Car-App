package rs.raf.RentACarUserService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rs.raf.RentACarUserService.domain.Rank;

@Repository
public interface RankRepository extends JpaRepository<Rank, Long> {
}
