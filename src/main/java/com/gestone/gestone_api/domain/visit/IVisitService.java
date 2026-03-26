package com.gestone.gestone_api.domain.visit;

import java.util.List;
import java.util.UUID;

public interface IVisitService {
    Visit save(Visit visit);
    Visit findById(UUID id);
    List<Visit> findAll(UUID marbleshopId);
    Visit complete(UUID visitId);
    void delete(UUID visitId);
}
