package com.gestone.gestone_api.domain.installation_order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/installation")
public class InstallationOrderController {

    @Autowired
    private InstallationOrderService installationOrderService;

    @PostMapping
    public ResponseEntity<InstallationOrderResponseDTO> saveInstallationOrder(@RequestBody InstallationOrderRequestDTO requestDTO) {
        InstallationOrder installationOrder = installationOrderService.save(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new InstallationOrderResponseDTO(installationOrder));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<InstallationOrderResponseDTO>> findAllByMarbleshopId(@PathVariable UUID marbleshopId) {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.installationOrderService.findAll(marbleshopId).stream().map(InstallationOrderResponseDTO::new).toList()
        );
    }
    
    @GetMapping("/order/{orderId}")
    public ResponseEntity<InstallationOrderResponseDTO> findByOrderId(@PathVariable UUID orderId) {
        InstallationOrder installationOrder = installationOrderService.findByOrderId(orderId);
        if (installationOrder == null) return ResponseEntity.notFound().build();
        return ResponseEntity.status(HttpStatus.OK).body(new InstallationOrderResponseDTO(installationOrder));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<InstallationOrderResponseDTO> updateStatus(
            @PathVariable UUID id,
            @RequestParam("status") InstallationOrderStatus status) {
        InstallationOrder installationOrder = installationOrderService.updateStatus(id, status);
        return ResponseEntity.ok(new InstallationOrderResponseDTO(installationOrder));
    }

    @PostMapping("/{id}/upload-image")
    public ResponseEntity<?> uploadImage(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        installationOrderService.uploadImage(id, file);
        return ResponseEntity.ok("Image uploaded successfully");
    }

    @GetMapping("/image/{fileName:.+}")
    public ResponseEntity<UrlResource> getImage(@PathVariable String fileName) throws IOException {
        String uploadDir = "/uploads/installations/";
        Path path = Paths.get(uploadDir + fileName);

        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        String contentType = Files.probeContentType(path);

        UrlResource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .body(resource);
    }
}
