package fr.jpierrot.superbowlbackend.service.impl;

import fr.jpierrot.superbowlbackend.pojo.entities.Country;
import fr.jpierrot.superbowlbackend.repository.CountryRepository;
import fr.jpierrot.superbowlbackend.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Override
    public List<Country> getAllCountries() {
        return countryRepository.findAllCountries();
    }

    @Override
    public Country getCountryById(Long id) {
        return countryRepository.findCountryById(id);
    }

    @Override
    public List<Country> getCountryByName(String countryName) {
        return countryRepository.findCountryByName(countryName);
    }
}
