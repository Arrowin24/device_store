package ru.arrowin.test_task.service;

import ru.arrowin.test_task.model.devices.Vacuum;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.VacuumModel;

import java.util.List;

public interface VacuumService {
    List<VacuumModel> getVacuumsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, double containerVolume, int modesNum);

    List<Model> getVacuumsBy(String modelName, String color, Double minPrice, Double maxPrice);

    Vacuum createVacuum(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable);

    VacuumModel createVacuumModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, double containerVolume,
            int modesNum);
}
