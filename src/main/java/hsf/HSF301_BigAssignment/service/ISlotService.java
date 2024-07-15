package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Slot;

import java.util.List;

public interface ISlotService {
    public List<Slot> getAllSlots();

    public List<Slot> getByCourtId(Long id);

    public Slot getSlotById(Long id);

    public void saveSlot(Slot slot);

    public void deleteSlot(Long id);

}
