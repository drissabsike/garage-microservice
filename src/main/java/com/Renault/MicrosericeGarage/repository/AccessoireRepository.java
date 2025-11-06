package com.Renault.MicrosericeGarage.repository;

import com.Renault.MicrosericeGarage.entity.Accessory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AccessoireRepository extends JpaRepository<Accessory, Long> {
    List<Accessory> findByVehicleId(Long vehicleId);
    boolean existsByNameAndVehicleId(String name, Long vehicleId);
}
