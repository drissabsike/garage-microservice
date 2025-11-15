package com.Renault.MicrosericeGarage.controller;


import com.Renault.MicrosericeGarage.dto.VehicleDto;
import com.Renault.MicrosericeGarage.entity.Garage;
import com.Renault.MicrosericeGarage.entity.Vehicle;
import com.Renault.MicrosericeGarage.repository.GarageRepository;
import com.Renault.MicrosericeGarage.service.VehicleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    private final GarageRepository garageRepository;

    public VehicleController(VehicleService vehicleService, GarageRepository garageRepository) {
        this.vehicleService = vehicleService;
        this.garageRepository = garageRepository;
    }

    @GetMapping
    public List<Vehicle> getAllVehicles() {
        return vehicleService.getAllVehicles();
    }

    @PostMapping
    public Vehicle create(@RequestBody VehicleDto dto) {
        return vehicleService.createVehicle(dto);
    }

    @PutMapping("/{id}")
    public Vehicle update(@PathVariable Long id, @RequestBody VehicleDto dto) {
        return vehicleService.updateVehicle(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        vehicleService.deleteVehicle(id);
    }

    @GetMapping("/garage/{garageId}")
    public List<Vehicle> getByGarage(@PathVariable Long garageId) {
        return vehicleService.getVehiclesByGarage(garageId);
    }

    @GetMapping("/brand/{brand}")
    public List<Vehicle> getByModel(@PathVariable String brand) {
        return vehicleService.getVehiclesByBrand(brand);
    }

}
