package com.Renault.MicrosericeGarage.controller;

import com.Renault.MicrosericeGarage.dto.GarageDto;
import com.Renault.MicrosericeGarage.entity.response.ApiResponse;
import com.Renault.MicrosericeGarage.entity.response.ApiResponseFactory;
import com.Renault.MicrosericeGarage.service.GarageService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/garages")
public class GarageController {
    private final GarageService service;
    public GarageController(GarageService service){ this.service = service; }

    @PostMapping
    public ResponseEntity<ApiResponse<GarageDto>> createGarage(@Valid @RequestBody GarageDto dto) {
        GarageDto created = service.insertGarage(dto);
        ApiResponse<GarageDto> response =
                ApiResponseFactory.success("Garage créé avec succès", created);
        return ResponseEntity
                .created(URI.create("/api/garages/" + created.getId()))
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<GarageDto>> getGarageById(@PathVariable Long id) {

        GarageDto garage = service.findGarageById(id);

        return ResponseEntity.ok(
                ApiResponseFactory.success("Garage récupéré avec succès", garage));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<GarageDto>> updateGarage(
            @PathVariable Long id,
            @Valid @RequestBody GarageDto dto) {

        GarageDto updated = service.updateGarage(id, dto);

        return ResponseEntity.ok(
                ApiResponseFactory.success("Garage mis à jour avec succès", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteGarage(@PathVariable Long id) {

        service.deleteGarage(id);

        return ResponseEntity.ok(
                ApiResponseFactory.success("Garage supprimé avec succès"));
    }

    @GetMapping
    public ResponseEntity<Page<GarageDto>> listGarage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "ASC") String direction,
            @RequestParam(required = false) String city
    ){
        Page<GarageDto> result = service.findAllGarage(page, size, sortBy, direction, Optional.ofNullable(city));
        return ResponseEntity.ok(result);
    }
}
