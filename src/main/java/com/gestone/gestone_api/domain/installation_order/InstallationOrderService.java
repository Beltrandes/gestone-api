package com.gestone.gestone_api.domain.installation_order;

import com.gestone.gestone_api.domain.order.MarbleshopOrder;
import com.gestone.gestone_api.domain.order.MarbleshopOrderService;
import com.gestone.gestone_api.domain.order.MarbleshopOrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class InstallationOrderService {

    @Autowired
    private InstallationOrderRepository installationOrderRepository;

    @Autowired
    private MarbleshopOrderService marbleshopOrderService;

    public InstallationOrder save(InstallationOrderRequestDTO requestDTO) {
        MarbleshopOrder order = marbleshopOrderService.findById(requestDTO.orderId());

        InstallationOrder installationOrder = new InstallationOrder();
        installationOrder.setOrder(order);
        installationOrder.setMarbleshop(order.getMarbleshop());
        installationOrder.setStatus(requestDTO.status() != null ? requestDTO.status() : InstallationOrderStatus.PENDING);
        installationOrder.setInstallers(requestDTO.installers());
        installationOrder.setAddress(requestDTO.address());
        installationOrder.setNotes(requestDTO.notes());
        installationOrder.setScheduledDate(requestDTO.scheduledDate());
        
        if (installationOrder.getStatus() == InstallationOrderStatus.IN_PROGRESS || installationOrder.getStatus() == InstallationOrderStatus.SCHEDULED) {
            marbleshopOrderService.updateStatus(order.getId(), MarbleshopOrderStatus.INSTALLING);
        }

        return installationOrderRepository.save(installationOrder);
    }
    
    public InstallationOrder updateStatus(UUID id, InstallationOrderStatus newStatus) {
        InstallationOrder installationOrder = findById(id);
        installationOrder.setStatus(newStatus);
        
        if (newStatus == InstallationOrderStatus.COMPLETED) {
            installationOrder.setCompletionDate(LocalDateTime.now());
            marbleshopOrderService.updateStatus(installationOrder.getOrder().getId(), MarbleshopOrderStatus.INSTALLED);
        } else if (newStatus == InstallationOrderStatus.IN_PROGRESS || newStatus == InstallationOrderStatus.SCHEDULED) {
            marbleshopOrderService.updateStatus(installationOrder.getOrder().getId(), MarbleshopOrderStatus.INSTALLING);
        }
        
        return installationOrderRepository.save(installationOrder);
    }

    public void uploadImage(UUID id, MultipartFile file) throws IOException {
        InstallationOrder installationOrder = findById(id);
        String uploadDir = "/uploads/installations/";
        String fileName = id + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir + fileName);
        Files.createDirectories(filePath.getParent());
        Files.write(filePath, file.getBytes());

        installationOrder.getImages().add(fileName);
        installationOrderRepository.save(installationOrder);
    }

    public InstallationOrder findById(UUID id) {
        return installationOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Installation Order not found with id: " + id));
    }

    public List<InstallationOrder> findAll(UUID marbleshopId) {
        return installationOrderRepository.findAllByMarbleshopId(marbleshopId);
    }
    
    public InstallationOrder findByOrderId(UUID orderId) {
        return installationOrderRepository.findByOrderId(orderId);
    }
}
