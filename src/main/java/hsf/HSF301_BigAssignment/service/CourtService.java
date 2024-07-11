package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.repo.CourtRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourtService {
    private final CourtRepository courtRepository;

    public List<Court> getAllCourts() {
        return courtRepository.findAll();
    }


    public List<Court> getCourtsByName(String name) {
        return courtRepository.findCourtsByName(name);
    }

    public void addCourt(Court court) {
        courtRepository.save(court);
    }

    public void deleteCourt(Long id) {
        courtRepository.deleteById(id);
    }

    public Court findById(Long courtId) {
        Court court = courtRepository.findById(courtId).get();
        return court;
    }


}
