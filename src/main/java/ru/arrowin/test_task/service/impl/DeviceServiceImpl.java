package ru.arrowin.test_task.service.impl;

import org.springframework.stereotype.Service;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.repository.model.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    private final PCModelRepository pcModelRepository;
    private final RefrigeratorModelRepository refrigeratorModelRepository;
    private final SmartPhoneModelRepository smartPhoneModelRepository;
    private final TVModelRepository tvModelRepository;
    private final VacuumModelRepository vacuumModelRepository;


    public DeviceServiceImpl(
            PCModelRepository pcModelRepository, RefrigeratorModelRepository refrigeratorModelRepository,
            SmartPhoneModelRepository smartPhoneModelRepository, TVModelRepository tvModelRepository,
            VacuumModelRepository vacuumModelRepository)
    {
        this.pcModelRepository = pcModelRepository;
        this.refrigeratorModelRepository = refrigeratorModelRepository;
        this.smartPhoneModelRepository = smartPhoneModelRepository;
        this.tvModelRepository = tvModelRepository;
        this.vacuumModelRepository = vacuumModelRepository;

    }

    @Override
    public List<Model> getDeviceByAllParams(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable)
    {
        List<Model> models = new ArrayList<>();
        models.addAll(pcModelRepository.findAll(
                pcModelRepository.combinedSpecification(deviceName, country, manufacturer, isOnlineOrderAvailable,
                                                        isInstallmentAvailable, serialNum, modelName, color, maxPrice,
                                                        minPrice, isAvailable, null, null)));
        models.addAll(refrigeratorModelRepository.findAll(
                refrigeratorModelRepository.combinedSpecification(deviceName, country, manufacturer,
                                                                  isOnlineOrderAvailable, isInstallmentAvailable,
                                                                  serialNum, modelName, color, maxPrice, minPrice,
                                                                  isAvailable, 0, null)));
        models.addAll(smartPhoneModelRepository.findAll(
                smartPhoneModelRepository.combinedSpecification(deviceName, country, manufacturer,
                                                                isOnlineOrderAvailable, isInstallmentAvailable,
                                                                serialNum, modelName, color, maxPrice, minPrice,
                                                                isAvailable, 0, 0)));
        models.addAll(tvModelRepository.findAll(
                tvModelRepository.combinedSpecification(deviceName, country, manufacturer, isOnlineOrderAvailable,
                                                        isInstallmentAvailable, serialNum, modelName, color, maxPrice,
                                                        minPrice, isAvailable, null, null)));
        models.addAll(vacuumModelRepository.findAll(
                vacuumModelRepository.combinedSpecification(deviceName, country, manufacturer, isOnlineOrderAvailable,
                                                            isInstallmentAvailable, serialNum, modelName, color,
                                                            maxPrice, minPrice, isAvailable, 0, 0)));

        return models;
    }


    @Override
    public List<Model> getDeviceBy(
            ModelType modelType, String modelName, String color, Double minPrice, Double maxPrice)
    {
        List<Model> models = new ArrayList<>();
        switch (modelType) {
            case PC:
                models.addAll(pcModelRepository.findAll(
                        pcModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
                break;
            case TV:
                models.addAll(tvModelRepository.findAll(
                        tvModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
                break;
            case VACUUM:
                models.addAll(vacuumModelRepository.findAll(
                        vacuumModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
                break;
            case SMARTPHONE:
                models.addAll(smartPhoneModelRepository.findAll(
                        smartPhoneModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
                break;
            case REFRIGERATOR:
                models.addAll(refrigeratorModelRepository.findAll(
                        refrigeratorModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
                break;
            default:
                models.addAll(getAllDeviceBy(modelName, color, minPrice, maxPrice));
        }
        return models;
    }

    private List<Model> getAllDeviceBy(String modelName, String color, Double minPrice, Double maxPrice) {
        List<Model> models = new ArrayList<>();
        models.addAll(refrigeratorModelRepository.findAll(
                refrigeratorModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
        models.addAll(smartPhoneModelRepository.findAll(
                smartPhoneModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
        models.addAll(vacuumModelRepository.findAll(
                vacuumModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
        models.addAll(tvModelRepository.findAll(
                tvModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
        models.addAll(pcModelRepository.findAll(
                pcModelRepository.combinedModelSpecification(modelName, color, minPrice, maxPrice)));
        return models;
    }

    @Override
    public List<String> convertModelsToText(List<Model> models) {
        return models.stream().map(Model::toText).collect(Collectors.toList());
    }

    @Override
    public List<Model> sortBy(List<Model> models, SortType sortType) {

        switch (sortType) {
            case BY_NAME_INCREASING:
                return models.stream().sorted(Comparator.comparing(m -> m.getModelName().toLowerCase()))
                             .collect(Collectors.toList());
            case BY_NAME_DECREASING:
                return models.stream().sorted(Comparator.comparing(m -> m.getModelName().toLowerCase(),
                                                                   Comparator.reverseOrder()))
                             .collect(Collectors.toList());
            case BY_PRICE_INCREASING:
                return models.stream().sorted(Comparator.comparing(Model::getPrice)).collect(Collectors.toList());
            case BY_PRICE_DECREASING:
                return models.stream().sorted(Comparator.comparing(Model::getPrice, Comparator.reverseOrder()))
                             .collect(Collectors.toList());
            default:
                return models;
        }
    }

}
