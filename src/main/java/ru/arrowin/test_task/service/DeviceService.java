package ru.arrowin.test_task.service;

import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.service.impl.SortType;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public interface DeviceService {
    List<Model> getDeviceByAllParams(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable);

    List<Model> getDeviceBy(
            ModelType modelType, String modelName, String color, Double minPrice, Double maxPrice);

    static <T extends Model> List<String> convertModelsToText(List<T> models) {
        return models.stream().map(Model::toText).collect(Collectors.toList());
    }


    static <T extends Model> List<T> sortBy(List<T> models, SortType sortType) {
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
