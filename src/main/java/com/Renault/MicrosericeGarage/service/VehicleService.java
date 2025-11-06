package com.Renault.MicrosericeGarage.service;

import com.Renault.MicrosericeGarage.dto.VehicleDto;
import com.Renault.MicrosericeGarage.entity.Garage;
import com.Renault.MicrosericeGarage.entity.Vehicle;
import com.Renault.MicrosericeGarage.repository.GarageRepository;
import com.Renault.MicrosericeGarage.repository.VehiculeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    private final VehiculeRepository vehicleRepository;
    private final GarageRepository garageRepository;

    public VehicleService(VehiculeRepository vehicleRepository, GarageRepository garageRepository) {
        this.vehicleRepository = vehicleRepository;
        this.garageRepository = garageRepository;
    }

    // CREATE
    public Vehicle createVehicle(VehicleDto dto) {

        Garage garage = garageRepository.findById(dto.getGarageId())
                .orElseThrow(() -> new RuntimeException("Garage not found"));

        // ✅ CONTRAINTE : max 50 véhicules par garage
        long count = vehicleRepository.countByGarageId(garage.getId());
        if (count >= 50) {
            throw new IllegalStateException("Le garage a atteint le quota maximum de 50 véhicules");
        }
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(dto.getBrand());
        vehicle.setFabricationYear(dto.getYear());
        vehicle.setFuelType(dto.getFuelType());
        vehicle.setGarage(garage);

        return vehicleRepository.save(vehicle);
    }

    // UPDATE
    public Vehicle updateVehicle(Long id, VehicleDto dto) {
        Vehicle vehicle = vehicleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        vehicle.setBrand(dto.getBrand());
        vehicle.setFabricationYear(dto.getYear());
        vehicle.setFuelType(dto.getFuelType());

        return vehicleRepository.save(vehicle);
    }


    // DELETE
    public void deleteVehicle(Long id) {
        if (!vehicleRepository.existsById(id)) {
            throw new RuntimeException("Vehicle not found");
        }
        vehicleRepository.deleteById(id);
    }


    // GET VEHICLES OF ONE GARAGE
    public List<Vehicle> getVehiclesByGarage(Long garageId) {
        return vehicleRepository.findByGarageId(garageId);
    }

    // GET VEHICLES BY BRAND ACROSS GARAGES
    public List<Vehicle> getVehiclesByBrand(String brand) {
        return vehicleRepository.findByBrandIgnoreCase(brand);
    }
    public List<Vehicle> getAllVehicles() {
        return vehicleRepository.findAll();
    }
}
