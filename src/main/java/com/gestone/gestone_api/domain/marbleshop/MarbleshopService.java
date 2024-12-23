package com.gestone.gestone_api.domain.marbleshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public void uploadMarbleshopLogo(UUID marbleshopId, MultipartFile file) throws IOException {
       Marbleshop marbleshop = findMarbleshopById(marbleshopId);
       String uploadDir = "/uploads/logos/";
       String fileName = marbleshopId + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        marbleshop.setLogoPath(filePath.toString());
        marbleshopRepository.save(marbleshop);

    }

    public Resource getMarbleshopLogo(UUID marbleshopId) throws MalformedURLException {
        Marbleshop marbleshop = findMarbleshopById(marbleshopId);
        Path filePath = Paths.get(marbleshop.getLogoPath());
        Resource resource = new UrlResource(filePath.toUri());
        return resource;
    }

    public Marbleshop findMarbleshopById(UUID id) {
        return marbleshopRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Marbleshop not found with id: " + id));
    }
}
