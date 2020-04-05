package com.caririfood.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.caririfood.domain.model.Kitchen;
import com.caririfood.infrastructure.sevice.KitchenService;

import javax.validation.Valid;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

    @Autowired
    private KitchenService kitchenService;

    @GetMapping
    public List<Kitchen> list() {
        return this.kitchenService.list();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Kitchen> findById(@PathVariable Long id) {
        Kitchen kitchen = this.kitchenService.findById(id);
        return kitchen != null ? ResponseEntity.ok(kitchen) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Kitchen> create(@Valid @RequestBody Kitchen kitchen) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.kitchenService.save(kitchen));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @Valid @RequestBody Kitchen kitchen) {
        Kitchen kitchenOld = this.kitchenService.findById(id);
        return kitchenOld == null ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(this.kitchenService.update(id, kitchen, kitchenOld));
    }
}
