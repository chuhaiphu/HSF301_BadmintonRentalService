package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.repo.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtService {

    @Autowired
    private CourtRepository courtRepository;

    public List<Court> findAll() {
        return courtRepository.findAll();
    }

public List<Court> searchCourts(String searchTerm) {
        List<Court> allCourts = courtRepository.findAll();
        if (searchTerm == null || searchTerm.isEmpty()) {
            return allCourts;
        }

        String lowerSearchTerm = searchTerm.toLowerCase();
        return allCourts.stream()
            .filter(court -> 
                court.getName().toLowerCase().contains(lowerSearchTerm) ||
                court.getAddress().toLowerCase().contains(lowerSearchTerm) ||
                court.getDescription().toLowerCase().contains(lowerSearchTerm) ||
                court.getOpenTime().toString().contains(searchTerm) ||
                court.getCloseTime().toString().contains(searchTerm) ||
                Boolean.toString(court.getStatus()).contains(searchTerm)
            )
            .collect(Collectors.toList());
    }

    public Court save(Court court) {
        return courtRepository.save(court);
    }

    public Court update(Court court) {
        return courtRepository.save(court);
    }

    public void delete(Long id) {
        courtRepository.deleteById(id);
    }
}
