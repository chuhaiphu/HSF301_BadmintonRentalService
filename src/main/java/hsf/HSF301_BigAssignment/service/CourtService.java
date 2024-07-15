package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Court;
import hsf.HSF301_BigAssignment.repo.CourtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourtService implements ICourtService {

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

    @Override
    public void delete(Long id) {

    }

    public void deactivateCourt(Long id) {
        Court court = courtRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Court not found"));
        court.setStatus(false);
        courtRepository.save(court);
    }

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

    public List<Court> findAllActiveCourts() {
        return courtRepository.findByStatusTrue();
    }
    
    public List<Court> searchActiveCourts(String search) {
        return courtRepository.findByStatusTrueAndNameContainingIgnoreCase(search);
    }
}
