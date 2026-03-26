package com.gestone.gestone_api.domain.visit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class VisitService implements IVisitService {

    @Autowired
    private VisitRepository visitRepository;

    @Override
    public Visit save(Visit visit) {
        return visitRepository.save(visit);
    }

    @Override
    public Visit findById(UUID id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Visit not found with id: " + id));
    }

    @Override
    public List<Visit> findAll(UUID marbleshopId) {
        return visitRepository.findAllByMarbleshopIdOrderByScheduledAtDesc(marbleshopId);
    }

    @Override
    public Visit complete(UUID visitId) {
        var visit = this.findById(visitId);
        visit.setCompleted(true);
        visit.setCompletedAt(LocalDateTime.now());
        return visitRepository.save(visit);
    }

    @Override
    public void delete(UUID visitId) {
        var visit = this.findById(visitId);
        visitRepository.delete(visit);
    }
}
