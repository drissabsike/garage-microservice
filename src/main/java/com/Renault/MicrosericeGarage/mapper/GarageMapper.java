package com.Renault.MicrosericeGarage.mapper;


import com.Renault.MicrosericeGarage.dto.GarageDto;
import com.Renault.MicrosericeGarage.entity.Garage;

public class GarageMapper {
    public static GarageDto toDto(Garage g){
        if(g==null) return null;
        return new GarageDto(g.getId(), g.getName(), g.getAddress(), g.getCity(), g.getTelephone(), g.getEmail());
    }

    public static Garage toEntity(GarageDto d){
        if(d==null) return null;
        Garage g = new Garage();
        g.setId(d.getId());
        g.setName(d.getName());
        g.setAddress(d.getAddress());
        g.setCity(d.getCity());
        g.setTelephone(d.getTelephone());
        g.setEmail(d.getEmail());
        return g;
    }
}
