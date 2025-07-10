package com.gestone.gestone_api.domain.production_order;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/production")
public class ProductionOrderController {

    @Autowired
    private ProductionOrderService productionOrderService;
    @PostMapping
    public ResponseEntity<ProductionOrderResponseDTO> saveProductionOrder(@RequestBody ProductionOrderRequestDTO productionOrderRequestDTO) {
        ProductionOrder productionOrder = productionOrderService.save(productionOrderRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductionOrderResponseDTO(productionOrder));
    }

    @GetMapping("/{marbleshopId}")
    public ResponseEntity<List<ProductionOrderResponseDTO>> findAllByMarbleshopId(@PathVariable UUID marbleshopId) {
        return ResponseEntity.status(HttpStatus.OK).body(this.productionOrderService.findAll(marbleshopId).stream().map(ProductionOrderResponseDTO::new).toList());
    }
    @PostMapping("/{id}/upload-project") 
    public ResponseEntity<?> uploadProject(@PathVariable UUID id, @RequestParam("file") MultipartFile file) throws IOException {
        productionOrderService.uploadProject(id, file);
        return ResponseEntity.ok("Project uploaded successfully");
    }

    @GetMapping("/project-image/{id}")
    public ResponseEntity<UrlResource> getProjectImage(@PathVariable UUID id) throws IOException {
        ProductionOrder order = productionOrderService.findById(id);

        String filePath = order.getProjectUrl();
        Path path = Paths.get(filePath);

        // Verificar se o arquivo existe
        if (!Files.exists(path)) {
            return ResponseEntity.notFound().build();
        }

        // Determinar o tipo de mídia
        String contentType = Files.probeContentType(path); // Detecta MIME (ex: image/jpeg)

        // Carregar como recurso
        UrlResource resource = new UrlResource(path.toUri());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                .body(resource);
    }
}
