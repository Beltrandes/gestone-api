package com.gestone.gestone_api.domain.marbleshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/marbleshop")
public class MarbleshopController {
    @Autowired
    private MarbleshopService marbleshopService;

    @PostMapping("/{marbleshopId}/upload-logo")
    public ResponseEntity<?> uploadLogo(@PathVariable UUID marbleshopId, @RequestParam("file") MultipartFile file) throws IOException {
        marbleshopService.uploadMarbleshopLogo(marbleshopId, file);
        return ResponseEntity.ok("Logo uploaded successfully");
    }

    @GetMapping("/{marbleshopId}/logo")
    public ResponseEntity<Resource> getLogo(@PathVariable UUID marbleshopId) throws MalformedURLException {
       var resource = marbleshopService.getMarbleshopLogo(marbleshopId);
       return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(resource);
    }

}
