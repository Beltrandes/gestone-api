package com.gestone.gestone_api.marbleshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarbleshopService {
    @Autowired
    private MarbleshopRepository marbleshopRepository;

    public Marbleshop saveMarbleshop(Marbleshop marbleshop) {
        var marbleshopToSave = new Marbleshop(marbleshop.getName(), marbleshop.getEmail(), marbleshop.getPhone());
        var savedMarbleshop = marbleshopRepository.save(marbleshopToSave);
        return savedMarbleshop;
    }
}
