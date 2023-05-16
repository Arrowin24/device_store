package ru.arrowin.test_task.service;

import ru.arrowin.test_task.model.devices.PC;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.PCModel;

import java.util.List;

public interface PCService {
    List<PCModel> getPCsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String processorType, String category);

    List<Model> getPCsBy(String modelName, String color, Double minPrice, Double maxPrice);

    PC createPC(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable);

    PCModel createPCModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, String processorType,
            String category);
}
