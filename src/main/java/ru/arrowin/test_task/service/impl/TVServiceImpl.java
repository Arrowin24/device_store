package ru.arrowin.test_task.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.arrowin.test_task.model.models.TVModel;
import ru.arrowin.test_task.service.TVService;
import ru.arrowin.test_task.service.repository.TVRepository;

import java.util.List;

@Service
public class TVServiceImpl implements TVService {

    private final TVRepository tvRepository;

    public TVServiceImpl(TVRepository tvRepository) {
        this.tvRepository = tvRepository;
    }


    @Override
    public List<TVModel> getTVsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String technology, String category)
    {
        Specification<TVModel> spec = tvRepository.combinedSpecification(deviceName, country, manufacturer,
                                                                         isOnlineOrderAvailable, isInstallmentAvailable,
                                                                         serialNum, modelName, color, maxPrice,
                                                                         minPrice, isAvailable, technology, category);

        return tvRepository.findAll(spec);
    }

/*
    public List<TVModel> getModelsByParam(
            String name, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable)
    {
        List<TV> tvs = getTVsByParam(name, country, manufacturer, isOnlineOrderAvailable, isInstallmentAvailable);
        return tvs.stream().flatMap(tv -> tv.getModels().stream()).collect(Collectors.toList());
    }
*/

}
