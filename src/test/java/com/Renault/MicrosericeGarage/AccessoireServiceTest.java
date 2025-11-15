package com.Renault.MicrosericeGarage;

import com.Renault.MicrosericeGarage.entity.Accessory;
import com.Renault.MicrosericeGarage.entity.Vehicle;
import com.Renault.MicrosericeGarage.repository.AccessoireRepository;
import com.Renault.MicrosericeGarage.repository.VehiculeRepository;
import com.Renault.MicrosericeGarage.service.AccessoireService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccessoireServiceTest {

    @Mock
    private AccessoireRepository accessoireRepository;

    @Mock
    private VehiculeRepository vehicleRepository;

    @InjectMocks
    private AccessoireService accessoireService;

    private Vehicle vehicle;
    private Accessory accessoire;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setBrand("Renault");
        vehicle.setFabricationYear(2020);
        vehicle.setFuelType("Essence");
        accessoire = new Accessory(20L,"GPS","Navigation GPS",199.99,"Electronique",vehicle);
        accessoire.setId(1L);
    }

    @Test
    void testCreateAccessoire() {
        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.of(vehicle));
        when(accessoireRepository.save(any(Accessory.class))).thenReturn(accessoire);

        Accessory created = accessoireService.createAccessoire(accessoire, vehicle.getId());

        assertNotNull(created);
        assertEquals("GPS", created.getName());
        assertEquals(vehicle, created.getVehicle());
        verify(accessoireRepository, times(1)).save(accessoire);
    }

    @Test
    void testUpdateAccessoire() {
        when(accessoireRepository.findById(1L)).thenReturn(Optional.of(accessoire));
        when(accessoireRepository.save(accessoire)).thenReturn(accessoire);

        accessoire.setName("GPS Plus");
        Accessory updated = accessoireService.updateAccessoire(1L, accessoire);

        assertEquals("GPS Plus", updated.getName());
        verify(accessoireRepository, times(1)).save(accessoire);
    }

    @Test
    void testDeleteAccessoire() {
        when(accessoireRepository.existsById(1L)).thenReturn(true);
        doNothing().when(accessoireRepository).deleteById(1L);

        assertDoesNotThrow(() -> accessoireService.deleteAccessoire(1L));
        verify(accessoireRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAccessoiresByVehicle() {
        when(accessoireRepository.findByVehicleId(vehicle.getId())).thenReturn(List.of(accessoire));

        List<Accessory> list = accessoireService.getAccessoiresByVehicle(vehicle.getId());

        assertEquals(1, list.size());
        assertEquals("GPS", list.get(0).getName());
        verify(accessoireRepository, times(1)).findByVehicleId(vehicle.getId());
    }
}
