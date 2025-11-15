package com.Renault.MicrosericeGarage.event;

import com.Renault.MicrosericeGarage.entity.Vehicle;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class VehiclePublisher {

    private final ApplicationEventPublisher publisher;

    public VehiclePublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    public void publishVehicleCreated(Vehicle vehicle) {
        publisher.publishEvent(new VehicleCreatedEvent(this, vehicle));
    }
}

