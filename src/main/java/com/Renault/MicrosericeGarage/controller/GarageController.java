package com.Renault.MicrosericeGarage.controller;

import com.Renault.MicrosericeGarage.dto.GarageDto;
import com.Renault.MicrosericeGarage.service.GarageService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/garages")
public class GarageController {

    private final GarageService service;
    public GarageController(GarageService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<GarageDto> create(@Valid @RequestBody GarageDto dto){
        GarageDto created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/garages/"+created.getId())).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GarageDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GarageDto> update(@PathVariable Long id, @Valid @RequestBody GarageDto dto){
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.delete(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Garage with ID " + id + " has been successfully deleted.");
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<GarageDto>> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(required = false) String city
    ){
        Page<GarageDto> result = service.findAll(page, size, sortBy, direction, Optional.ofNullable(city));
        return ResponseEntity.ok(result);
    }
}
