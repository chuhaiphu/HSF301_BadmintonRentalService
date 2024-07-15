package hsf.HSF301_BigAssignment.repo;

import hsf.HSF301_BigAssignment.pojo.Slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SlotRepository extends JpaRepository<Slot, Long> {

    List<Slot> findAllByCourt_Id(Long id);


}
