package com.caririfood.controller;

import com.caririfood.domain.exception.EntityInUseException;
import com.caririfood.domain.exception.EntityNotFoundException;
import com.caririfood.domain.model.State;
import com.caririfood.infrastructure.sevice.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/states")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List<State> list() {
        return this.stateService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<State> findById(@PathVariable Long id) {
        State state = this.stateService.findById(id);
        return state != null ? ResponseEntity.ok(state) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody State state) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.stateService.save(state));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @Valid @RequestBody State state) {
        return ResponseEntity.ok().body(this.stateService.update(id, state));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id) {
        try {
            stateService.delete(id);
            return ResponseEntity.noContent().build();

        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
}
