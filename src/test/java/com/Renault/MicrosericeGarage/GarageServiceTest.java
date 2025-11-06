package com.Renault.MicrosericeGarage;

import com.Renault.MicrosericeGarage.dto.GarageDto;
import com.Renault.MicrosericeGarage.entity.Garage;
import com.Renault.MicrosericeGarage.repository.GarageRepository;
import com.Renault.MicrosericeGarage.service.GarageService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GarageServiceTest {

    @Test
    void testCreateGarage() {

        // Mock repository
        GarageRepository repository = mock(GarageRepository.class);

        // Service under test
        GarageService service = new GarageService(repository);

        // Input DTO
        GarageDto inputDto = new GarageDto();
        inputDto.setName("Garage Test");
        inputDto.setAddress("123 Street");
        inputDto.setCity("Rabat");
        inputDto.setEmail("garage@test.com");
        inputDto.setTelephone("0600000000");

// Entity returned by the repository
        Garage savedEntity = new Garage();
        savedEntity.setId(1L);
        savedEntity.setName("Garage Test");
        savedEntity.setAddress("123 Street");
        savedEntity.setCity("Rabat");
        savedEntity.setEmail("garage@test.com");
        savedEntity.setTelephone("0600000000");

        // Mock repository.save()
        when(repository.save(any(Garage.class))).thenReturn(savedEntity);

        // Call the service
        GarageDto result = service.create(inputDto);

        // Verify repository.save() has been called exactly once
        verify(repository, times(1)).save(any(Garage.class));

        // Verify returned result
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Garage Test", result.getName());

        // Optional: capture the entity passed to save()
        ArgumentCaptor<Garage> captor = ArgumentCaptor.forClass(Garage.class);
        verify(repository).save(captor.capture());
        Garage captured = captor.getValue();
        assertEquals("Garage Test", captured.getName());
    }
}
