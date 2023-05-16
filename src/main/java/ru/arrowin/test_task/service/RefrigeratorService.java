package ru.arrowin.test_task.service;

import ru.arrowin.test_task.model.devices.Refrigerator;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.RefrigeratorModel;

import java.util.List;

public interface RefrigeratorService {
    List<RefrigeratorModel> getRefrigeratorsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, int doorsNum, String compressorType);

    List<Model> getRefrigeratorsBy(String modelName, String color, Double minPrice, Double maxPrice);

    Refrigerator createRefrigerator(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable);

    RefrigeratorModel createRefrigeratorModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, int doorsNum,
            String compressorType);
}
