package com.Renault.MicrosericeGarage.service;

import com.Renault.MicrosericeGarage.dto.GarageDto;
import com.Renault.MicrosericeGarage.entity.Garage;
import com.Renault.MicrosericeGarage.exception.NotFoundException;
import com.Renault.MicrosericeGarage.mapper.GarageMapper;
import com.Renault.MicrosericeGarage.repository.GarageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;


@Service
@Transactional
public class GarageService {

    private final GarageRepository repository;

    public GarageService(GarageRepository repository) {
        this.repository = repository;
    }

    public GarageDto create(GarageDto dto) {
        Garage g = GarageMapper.toEntity(dto);
        Garage saved = repository.save(g);
        return GarageMapper.toDto(saved);
    }

    public GarageDto update(Long id, GarageDto dto) {
        Garage existing = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Garage not found with id=" + id));
        existing.setName(dto.getName());
        existing.setAddress(dto.getAddress());
        existing.setCity(dto.getCity());
        existing.setTelephone(dto.getTelephone());
        existing.setEmail(dto.getEmail());
        Garage saved = repository.save(existing);
        return GarageMapper.toDto(saved);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Garage not found with id=" + id);
        }
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public GarageDto findById(Long id) {
        Garage g = repository.findById(id).orElseThrow(() -> new NotFoundException("Garage not found with id=" + id));
        return GarageMapper.toDto(g);
    }

    @Transactional(readOnly = true)
    public Page<GarageDto> findAll(int page, int size, String sortBy, String direction, Optional<String> cityFilter){
        Sort sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        Specification<Garage> spec = (root, query, cb) -> {
            if(cityFilter.isPresent()){
                return cb.equal(cb.lower(root.get("city")), cityFilter.get().toLowerCase());
            }
            return cb.conjunction();
        };
        return repository.findAll(spec, pageable).map(GarageMapper::toDto);
    }

}