package workout.calendar.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import workout.calendar.domain.entity.Recode;

import java.util.List;

public interface RecodeRepository extends JpaRepository<Recode, Long> {

    List<Recode> findByUserId(Long id);

    @Query("SELECT r FROM Recode r LEFT JOIN FETCH r.performance WHERE r.id = :recodeId")
    Recode findByIdWithPerformance(@Param("recodeId") Long recodeId);
}
