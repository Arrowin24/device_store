package ru.arrowin.test_task.service;



import ru.arrowin.test_task.model.models.TVModel;

import java.util.List;

public interface TVService {


    List<TVModel> getTVsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String technology, String category);
}
