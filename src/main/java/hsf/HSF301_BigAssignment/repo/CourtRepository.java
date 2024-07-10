package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Court;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtRepository extends JpaRepository<Court, Long> {
}
