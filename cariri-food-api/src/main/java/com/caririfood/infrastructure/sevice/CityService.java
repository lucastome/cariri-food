package com.caririfood.infrastructure.sevice;

import com.caririfood.domain.exception.EntityInUseException;
import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.City;
import com.caririfood.domain.repository.CityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private StateService stateService;

    public List<City> findAll() {
        return this.cityRepository.findAll();
    }

    public City findById(Long id) {
        return this.cityRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Não existe cidade cadastrada com o código %d", id)));
    }

    public City save(City city) {
        this.setReferences(city);
        return this.cityRepository.save(city);
    }

    public City update(Long id, City newCity) {
        City oldCity = this.findById(id);
        BeanUtils.copyProperties(newCity, oldCity, "id");
        return this.save(oldCity);
    }

    public void deleteById(Long id) {
        try {
            this.cityRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Cidade de códido %d não localizada", id));
        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Cidade de código %d não pode ser removida pois está em uso", id));
        }
    }

    private void setReferences(City city) {
        city.setState(stateService.findById(city.getState().getId()));
    }

}

