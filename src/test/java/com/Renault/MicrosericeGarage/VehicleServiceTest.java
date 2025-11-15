package com.Renault.MicrosericeGarage;

import com.Renault.MicrosericeGarage.dto.GarageDto;
import com.Renault.MicrosericeGarage.entity.Garage;
import com.Renault.MicrosericeGarage.repository.GarageRepository;
import com.Renault.MicrosericeGarage.service.GarageService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class VehicleServiceTest {

    //Mock repository
    GarageRepository repository = mock(GarageRepository.class);

    //Service under test
    GarageService service = new GarageService(repository);

    private GarageDto createGarageDto() {
        GarageDto dto = new GarageDto();
        dto.setName("Garage Test");
        dto.setAddress("123 Street");
        dto.setCity("Rabat");
        dto.setEmail("garage@test.com");
        dto.setTelephone("0600000000");
        return dto;
    }

    private Garage createGarageEntity() {
        Garage g = new Garage();
        g.setId(1L);
        g.setName("new Garage Test");
        g.setAddress("123 Street");
        g.setCity("Rabat");
        g.setEmail("garage@test.com");
        g.setTelephone("0600000000");
        return g;
    }

    @Test
    void testCreateGarage() {
        // Mock repository.save()
        when(repository.save(any(Garage.class))).thenReturn(createGarageEntity());

        // Call the service
        GarageDto result = service.create(createGarageDto());

        // Verify repository.save() has been called exactly once
        verify(repository, times(1)).save(any(Garage.class));

        // Verify returned result
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("new Garage Test", result.getName());

        // capture the entity passed to save()
        ArgumentCaptor<Garage> captor = ArgumentCaptor.forClass(Garage.class);
        verify(repository).save(captor.capture());
        Garage captured = captor.getValue();
        assertEquals("Garage Test", captured.getName());
    }

    @Test
    void testUpdateGarage() {
        when(repository.findById(1L)).thenReturn(Optional.of(createGarageEntity()));

        Garage savedGarage = new Garage(1L, createGarageDto().getName(),
                createGarageDto().getAddress(),
                createGarageDto().getCity(),
                createGarageDto().getTelephone(),
                createGarageDto().getEmail());
        when(repository.save(any(Garage.class))).thenReturn(savedGarage);

        GarageDto result = service.update(1L, createGarageDto());

        // Vérification que findById et save ont été appelés
        verify(repository, times(1)).findById(1L);
        verify(repository, times(1)).save(any(Garage.class));

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Garage Test", result.getName());
        assertEquals("Rabat", result.getCity());
        assertEquals("0600000000", result.getTelephone());

        // Optionnel
        ArgumentCaptor<Garage> captor = ArgumentCaptor.forClass(Garage.class);
        verify(repository).save(captor.capture());
        Garage captured = captor.getValue();
        assertEquals("Garage Test", captured.getName());
        assertEquals("123 Street", captured.getAddress());
    }
}
