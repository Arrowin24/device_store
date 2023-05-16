package ru.arrowin.test_task.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.arrowin.test_task.exception.DBContainsModelException;
import ru.arrowin.test_task.exception.DeviceNotFoundException;
import ru.arrowin.test_task.model.devices.Refrigerator;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.RefrigeratorModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.RefrigeratorService;
import ru.arrowin.test_task.service.repository.device.RefrigeratorRepository;
import ru.arrowin.test_task.service.repository.model.RefrigeratorModelRepository;

import java.util.List;

@Service
public class RefrigeratorServiceImpl implements RefrigeratorService {
    private final RefrigeratorModelRepository refrigeratorModelRepository;

    private final DeviceService deviceService;
    private final RefrigeratorRepository refrigeratorRepository;

    public RefrigeratorServiceImpl(
            RefrigeratorModelRepository refrigeratorModelRepository, DeviceService deviceService,
            RefrigeratorRepository refrigeratorRepository)
    {
        this.refrigeratorModelRepository = refrigeratorModelRepository;
        this.deviceService = deviceService;
        this.refrigeratorRepository = refrigeratorRepository;
    }


    @Override
    public List<RefrigeratorModel> getRefrigeratorsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, int doorsNum, String compressorType)
    {
        Specification<RefrigeratorModel> spec = refrigeratorModelRepository.combinedSpecification(deviceName, country,
                                                                                                  manufacturer,
                                                                                                  isOnlineOrderAvailable,
                                                                                                  isInstallmentAvailable,
                                                                                                  serialNum, modelName,
                                                                                                  color, maxPrice,
                                                                                                  minPrice, isAvailable,
                                                                                                  doorsNum,
                                                                                                  compressorType);

        return refrigeratorModelRepository.findAll(spec);
    }

    @Override
    public List<Model> getRefrigeratorsBy(String modelName, String color, Double minPrice, Double maxPrice) {
        return deviceService.getDeviceBy(ModelType.REFRIGERATOR, modelName, color, minPrice, maxPrice);
    }

    @Override
    public Refrigerator createRefrigerator(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable)
    {
        Refrigerator refrigerator = new Refrigerator();
        refrigerator.setDeviceName(deviceName);
        refrigerator.setCountry(country);
        refrigerator.setManufacturer(manufacturer);
        refrigerator.setOnlineOrderAvailable(isOnlineOrderAvailable);
        refrigerator.setInstallmentAvailable(isInstallmentAvailable);
        refrigeratorRepository.save(refrigerator);
        return refrigerator;
    }

    @Override
    public RefrigeratorModel createRefrigeratorModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, int doorsNum,
            String compressorType)
    {
        if (!refrigeratorModelRepository.findById(serialNum).isPresent()) {
            throw new DBContainsModelException();
        }

        Specification<Refrigerator> vacuumSpecification = refrigeratorRepository.combinedSpecification(deviceName,
                                                                                                       country,
                                                                                                       manufacturer);
        Refrigerator refrigerator = refrigeratorRepository.findAll(vacuumSpecification).stream().findAny()
                                                          .orElseThrow(DeviceNotFoundException::new);
        RefrigeratorModel model = new RefrigeratorModel();
        model.setSerialNum(serialNum);
        model.setModelName(modelName);
        model.setColor(color);
        model.setPrice(Price);
        model.setSizeHeight(sizeH);
        model.setSizeLength(sizeL);
        model.setSizeWidth(sizeW);
        model.setAvailable(isAvailable);
        model.setDoorsNum(doorsNum);
        model.setCompressorType(compressorType);
        model.setRefrigerator(refrigerator);

        refrigeratorModelRepository.save(model);
        return model;
    }

}
