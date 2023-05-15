package ru.arrowin.test_task.service;

import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.service.impl.SortType;

import java.util.List;

public interface DeviceService {
    List<Model> getDeviceByAllParams(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable);

    List<Model> getDeviceBy(
            ModelType modelType, String modelName, String color, Double minPrice, Double maxPrice);

    List<String> convertModelsToText(List<Model> models);

    List<Model> sortBy(List<Model> models, SortType sortType);
}
