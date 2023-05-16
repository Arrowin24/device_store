package ru.arrowin.test_task.service;

import ru.arrowin.test_task.model.devices.SmartPhone;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.SmartPhoneModel;

import java.util.List;

public interface SmartphoneService {
    List<SmartPhoneModel> getSmartphonesByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, int memory, int cameraNums);

    List<Model> getSmartphonesBy(String modelName, String color, Double minPrice, Double maxPrice);

    SmartPhone createSmartphone(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable);

    SmartPhoneModel createSmartPhoneModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, int memory, int cameraNums);
}
