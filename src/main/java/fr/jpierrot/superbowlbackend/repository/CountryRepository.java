package fr.jpierrot.superbowlbackend.repository;

import fr.jpierrot.superbowlbackend.pojo.entities.Country;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {
    @Query("SELECT c FROM Country c")
    List<Country> findAllCountries();

    @Query("SELECT c FROM Country c WHERE c.id = ?1 ")
    Country findCountryById(Long id);

    @Query("SELECT c FROM Country c WHERE c.name like %?1% ")
    List<Country> findCountryByName(String name);

    Country findCountryByNameIs(String name);
}
