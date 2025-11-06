package com.Renault.MicrosericeGarage.entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.io.IOException;
import java.time.DayOfWeek;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Converter
public class DayOpeningMapConverter implements AttributeConverter<Map<DayOfWeek, List<OpeningTime>>, String> {
    private final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Override
    public String convertToDatabaseColumn(Map<DayOfWeek, List<OpeningTime>> attribute) {
        if (attribute == null) return null;
        try { return mapper.writeValueAsString(attribute); }
        catch (JsonProcessingException e) { throw new IllegalStateException(e); }
    }
    @Override
    public Map<DayOfWeek, List<OpeningTime>> convertToEntityAttribute(String dbData) {
        if (dbData == null) return new HashMap<>();
        try {
            return mapper.readValue(dbData, new TypeReference<Map<DayOfWeek, List<OpeningTime>>>() {});
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
