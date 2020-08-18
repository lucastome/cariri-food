package com.caririfood.controller;

import com.caririfood.domain.exception.EntityInUseException;
import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.State;
import com.caririfood.infrastructure.sevice.StateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> findAll() {
        log.info("GET | states | Iniciado | findAll ");
        List<State> stateList = this.stateService.findAll();
        log.info("GET | states | Iniciado | findAll ");
        return stateList;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<State> findById(@PathVariable Long id) {
        log.info("GET | states/{} | Iniciado | findById |", id);
        State state = this.stateService.findById(id);
        log.info("GET | states/{} | Finalizando | findById |", id);
        return state != null ? ResponseEntity.ok(state) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody State state) {
        try {
            log.info("POST | Iniciado | save | Entity: {}", new Object[]{state});
            State stateSave = this.stateService.save(state);
            log.info("POST | Finalizando | save | Entity: {}", new Object[]{stateSave});
            return ResponseEntity.status(HttpStatus.CREATED).body(stateSave);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @Valid @RequestBody State state) {
        log.info("PUT | states/{id} | Iniciado | update | Entity: {}", new Object[]{state});
        State stateUpdate = this.stateService.update(id, state);
        log.info("PUT | states/{id} | Finalizando | update | Entity: {}", new Object[]{stateUpdate});
        return ResponseEntity.ok().body(stateUpdate);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            log.info("DELETE | states/{id} | Iniciado | deleteById | Id: {}", id);
            stateService.deleteById(id);
            log.info("DELETE | states/{id} | Finalizando | deleteById | Id: {}", id);
            return ResponseEntity.noContent().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
