package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Team;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

    @Query("SELECT t FROM Team t")
    List<Team> findAllTeams();

    @Query("SELECT t FROM Team t WHERE t.id = ?1 ")
    Team findTeamById(Long id);

    @Query("SELECT t FROM Team t WHERE t.name like %?1% ")
    List<Team> findTeamByName(String name);

    Team findTeamByNameIs(String name);

    @Query("SELECT t FROM Team t WHERE t.country.id = ?1 ")
    List<Team> findTeamByCountryId(Long countryId);

    @Query("SELECT t FROM Team t WHERE t.country.name like %?1% ")
    List<Team> findTeamByCountryName(String countryName);

    List<Team> findTeamsByIdAndCountryIdOrderByCountry(Long id, Long countryId);

    List<Team> findTeamsByNameContainingAndCountryIdOrderByName(String teamName, Long countryId);

    List<Team> findTeamsByIdAndCountryNameContainingOrderByCountry(Long id, String countryName);

    List<Team> findTeamsByNameContainingAndCountryNameContainingOrderByName(String teamName, String countryName);
}
