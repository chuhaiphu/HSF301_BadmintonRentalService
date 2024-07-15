package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Slot;
import hsf.HSF301_BigAssignment.repo.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class SlotService implements ISlotService{
    private final SlotRepository slotRepository;

    @Override
    public List<Slot> getAllSlots() {
        return slotRepository.findAll();
    }

    @Override
    public List<Slot> getByCourtId(Long id) {
        return slotRepository.findAllByCourt_Id(id);
    }

    @Override
    public Slot getSlotById(Long id) {
        return slotRepository.findById(id).orElse(null );
    }

    @Override
    public void saveSlot(Slot slot) {
        slotRepository.save(slot);
    }

    @Override
    public void deleteSlot(Long id) {
        slotRepository.deleteById(id);
    }
}
