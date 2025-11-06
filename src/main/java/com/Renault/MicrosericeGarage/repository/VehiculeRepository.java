package com.Renault.MicrosericeGarage.repository;

import com.Renault.MicrosericeGarage.entity.Vehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByGarageId(Long garageId);
    List<Vehicle> findByBrandIgnoreCase(String brand);
    long countByGarageId(Long garageId);
}
