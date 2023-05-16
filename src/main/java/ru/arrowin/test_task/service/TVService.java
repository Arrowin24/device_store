package ru.arrowin.test_task.service;



import ru.arrowin.test_task.model.devices.TV;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.TVModel;

import java.util.List;

public interface TVService {


    List<TVModel> getTVsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String technology, String category);

    List<Model> getTVsBy(String modelName, String color, Double minPrice, Double maxPrice);

    TV createTV(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable);

    TVModel createTVModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, String technology,
            String category);
}
