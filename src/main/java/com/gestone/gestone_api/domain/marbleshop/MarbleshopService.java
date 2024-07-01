package com.gestone.gestone_api.domain.marbleshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MarbleshopService {
    @Autowired
    private MarbleshopRepository marbleshopRepository;

    public Marbleshop saveMarbleshop(Marbleshop marbleshop) {
        var marbleshopToSave = new Marbleshop(marbleshop.getName(), marbleshop.getEmail(), marbleshop.getPhone());
        var savedMarbleshop = marbleshopRepository.save(marbleshopToSave);
        return savedMarbleshop;
    }

    public Marbleshop findMarbleshopById(UUID id) {
        return marbleshopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Marbleshop not found with id: " + id));
    }
}
