package com.Renault.MicrosericeGarage.service;

import com.Renault.MicrosericeGarage.entity.Accessory;
import com.Renault.MicrosericeGarage.entity.Vehicle;
import com.Renault.MicrosericeGarage.repository.AccessoireRepository;
import com.Renault.MicrosericeGarage.repository.VehiculeRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AccessoireService{

    private final AccessoireRepository accessoireRepository;

    private final VehiculeRepository vehicleRepository;

    public AccessoireService(AccessoireRepository accessoireRepository, VehiculeRepository vehicleRepository) {
        this.accessoireRepository = accessoireRepository;
        this.vehicleRepository = vehicleRepository;
    }

    public Accessory createAccessoire(Accessory accessoire, Long vehicleId) {
        Vehicle vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));
        accessoire.setVehicle(vehicle);
        return accessoireRepository.save(accessoire);
    }

    public Accessory updateAccessoire(Long id, Accessory accessoireDetails) {
        Accessory accessoire = accessoireRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accessoire non trouvé"));

        accessoire.setName(accessoireDetails.getName());
        accessoire.setDescription(accessoireDetails.getDescription());
        accessoire.setPrice(accessoireDetails.getPrice());
        accessoire.setType(accessoireDetails.getType());

        return accessoireRepository.save(accessoire);
    }

    public void deleteAccessoire(Long id) {
        if (!accessoireRepository.existsById(id)) {
            throw new RuntimeException("Accessoire non trouvé");
        }
        accessoireRepository.deleteById(id);
    }

    public List<Accessory> getAccessoiresByVehicle(Long vehicleId) {
        return accessoireRepository.findByVehicleId(vehicleId);
    }
}
