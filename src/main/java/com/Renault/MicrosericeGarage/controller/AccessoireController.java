package com.Renault.MicrosericeGarage.controller;

import com.Renault.MicrosericeGarage.entity.Accessory;
import com.Renault.MicrosericeGarage.service.AccessoireService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/accessoires")
public class AccessoireController {

    private final AccessoireService accessoireService;

    public AccessoireController(AccessoireService accessoireService) {
        this.accessoireService = accessoireService;
    }

    @PostMapping("/vehicle/{vehicleId}")
    public ResponseEntity<Accessory> createAccessoires(@PathVariable Long vehicleId, @RequestBody Accessory accessoire) {
        return ResponseEntity.ok(accessoireService.createAccessoire(accessoire, vehicleId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Accessory> updateAccessoires(@PathVariable Long id, @RequestBody Accessory accessoire) {
        return ResponseEntity.ok(accessoireService.updateAccessoire(id, accessoire));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteAccessoires(@PathVariable Long id) {
        accessoireService.deleteAccessoire(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Accessoire with ID " + id + " has been successfully deleted.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/vehicle/{vehicleId}")
    public ResponseEntity<List<Accessory>> listAccessoiresByVehicle(@PathVariable Long vehicleId) {
        return ResponseEntity.ok(accessoireService.getAccessoiresByVehicle(vehicleId));
    }
}