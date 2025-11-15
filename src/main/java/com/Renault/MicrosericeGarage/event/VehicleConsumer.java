package com.Renault.MicrosericeGarage.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class VehicleConsumer {

    @EventListener
    public void handleVehicleCreated(VehicleCreatedEvent event) {
        System.out.println("Nouvelle publication de véhicule : " + event.getVehicle().getBrand());
    }
}