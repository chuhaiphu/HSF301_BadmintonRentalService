package hsf.HSF301_BigAssignment.service;

import hsf.HSF301_BigAssignment.pojo.Court;

import java.util.List;

public interface ICourtService {

    List<Court> findAll();

    List<Court> searchCourts(String searchTerm);

    Court save(Court court);

    Court update(Court court);

    void delete(Long id);

    void deactivateCourt(Long id);

    List<Court> getAllCourts();

    List<Court> getCourtsByName(String name);

    void addCourt(Court court);

    void deleteCourt(Long id);

    Court findById(Long courtId);
}
