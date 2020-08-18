package com.caririfood.controller;

import com.caririfood.domain.model.Kitchen;
import com.caririfood.infrastructure.sevice.KitchenService;
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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> findAll() {
        log.info("GET | kitchens | Iniciado | findAll ");
        List<Kitchen> kitchenList = this.kitchenService.findAll();
        log.info("GET | kitchens | Finalizando | findAll ");
        return kitchenList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
        log.info("GET | kitchens/{} | Iniciado | findById |", id);
        Kitchen kitchen = this.kitchenService.findById(id);
        log.info("GET | kitchens/{} | Finalizando | findById |", id);
        return kitchen != null ? ResponseEntity.ok(kitchen) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Kitchen> save(@Valid @RequestBody Kitchen kitchen) {
        log.info("POST | Iniciado | save | Entity: {}", new Object[]{kitchen});
        Kitchen kitchenSave = this.kitchenService.save(kitchen);
        log.info("POST | Iniciado | save | Entity: {}", new Object[]{kitchenSave});
        return ResponseEntity.status(HttpStatus.CREATED).body(kitchenSave);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @Valid @RequestBody Kitchen kitchen) {
        log.info("PUT | kitchens/{id} | Iniciado | update | Entity: {}", new Object[]{kitchen});
        Kitchen kitchenUpdate = this.kitchenService.update(id, kitchen);
        log.info("PUT | kitchens/{id} | Finalizando | update | Entity: {}", new Object[]{kitchenUpdate});
        return ResponseEntity.ok().body(kitchenUpdate);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public ResponseEntity<Kitchen> deleteById(@PathVariable Long id) {
        log.info("DELETE | kitchens/{id} | Iniciado | deleteById | Id: {}", id);
        this.kitchenService.deleteById(id);
        log.info("DELETE | kitchens/{id} | Finalizando | deleteById | Id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
