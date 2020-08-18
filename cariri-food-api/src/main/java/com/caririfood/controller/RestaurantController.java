package com.caririfood.controller;

import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.Restaurant;
import com.caririfood.infrastructure.sevice.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping
    private List<Restaurant> findAll() {
        log.info("GET | restaurants | Iniciado | findAll ");
        List<Restaurant> restaurantList = this.restaurantService.findAll();
        log.info("GET | restaurants | Finalizando | findAll ");
        return restaurantList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Restaurant> findById(@PathVariable Long id) {
        log.info("GET | restaurants/{} | Iniciado | findById |", id);
        Restaurant restaurant = this.restaurantService.findById(id);
        log.info("GET | restaurants/{} | Finalizando | findById |", id);
        return restaurant != null ? ResponseEntity.ok(restaurant) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Restaurant restaurant) {
        try {
            log.info("POST | restaurants | Iniciado | save | Entity: {}", new Object[]{restaurant});
            Restaurant restaurantSave = this.restaurantService.save(restaurant);
            log.info("POST | restaurants | Finalizando | save | Entity: {}", new Object[]{restaurantSave});
            return ResponseEntity.status(HttpStatus.CREATED).body(restaurantSave);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Restaurant restaurant) {
        try {
            log.info("PUT | restaurants/{id} | Iniciado | update | Entity: {}", new Object[]{restaurant});
            Restaurant restaurantUpate = this.restaurantService.update(id, restaurant);
            log.info("PUT | restaurants/{id} | Iniciado | update | Entity: {}", new Object[]{restaurantUpate});
            return ResponseEntity.ok().body(restaurantUpate);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<?> partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
        log.info("PATCH | restaurants/{id} | Iniciado | update | Entity: {}", new Object[]{fields});
        Restaurant restaurant = this.restaurantService.findById(id);
        if (Objects.isNull(restaurant))
            return ResponseEntity.notFound().build();
        restaurantService.merge(fields, restaurant);
        log.info("PATCH | restaurants/{id} | Iniciado | update | Entity: {}", new Object[]{restaurant});
        return this.update(id, restaurant);
    }

}
