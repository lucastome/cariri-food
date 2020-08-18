package com.caririfood.controller;

import com.caririfood.domain.exception.EntityInUseException;
import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.City;
import com.caririfood.infrastructure.sevice.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping
    public List<City> findAll() {
        log.info("GET | /cities | Iniciado | findAll ");
        List<City> cityList = this.cityService.findAll();
        log.info("GET | /cities | Finalizando | findAll ");
        return cityList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<City> findById(@PathVariable Long id) {
        log.info("GET | /cities/{} | Iniciado | findById |", id);
        City city = this.cityService.findById(id);
        log.info("GET | /cities/{} | Finalizando | findById |", id);
        return city != null ? ResponseEntity.ok(city) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody City city) {
        try {
            log.info("POST | Iniciado | save | Entity: {}", new Object[]{city});
            City citySave = this.cityService.save(city);
            log.info("POST | Finalizando | save | Entity: {}", new Object[]{citySave});
            return ResponseEntity.status(HttpStatus.CREATED).body(citySave);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @Valid @RequestBody City city) {
        log.info("PUT | cities/{id} | Iniciado | update | Entity: {}", new Object[]{city});
        City cityUpdate = this.cityService.update(id, city);
        log.info("PUT | cities/{id} | Finalizando | update | Entity: {}", new Object[]{cityUpdate});
        return ResponseEntity.ok().body(cityUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            log.info("DELETE | cities/{id} | Iniciado | deleteById | Id: {}", id);
            cityService.deleteById(id);
            log.info("DELETE | cities/{id} | Finalizando | deleteById | Id: {}", id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
