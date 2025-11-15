package com.Renault.MicrosericeGarage.event;


import com.Renault.MicrosericeGarage.entity.Vehicle;
import org.springframework.context.ApplicationEvent;

public class VehicleCreatedEvent extends ApplicationEvent {
    private final Vehicle vehicle;

    public VehicleCreatedEvent(Object source, Vehicle vehicle) {
        super(source);
        this.vehicle = vehicle;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }
}