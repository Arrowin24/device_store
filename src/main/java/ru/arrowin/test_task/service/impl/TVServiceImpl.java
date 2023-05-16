package ru.arrowin.test_task.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.arrowin.test_task.exception.DBContainsModelException;
import ru.arrowin.test_task.exception.DeviceNotFoundException;
import ru.arrowin.test_task.model.devices.TV;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.TVModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.TVService;
import ru.arrowin.test_task.service.repository.device.TVRepository;
import ru.arrowin.test_task.service.repository.model.TVModelRepository;

import java.util.List;

@Service
public class TVServiceImpl implements TVService {

    private final TVModelRepository tvModelRepository;

    private final DeviceService deviceService;
    private final TVRepository tVRepository;

    public TVServiceImpl(TVModelRepository tvModelRepository, DeviceService deviceService, TVRepository tVRepository) {
        this.tvModelRepository = tvModelRepository;
        this.deviceService = deviceService;
        this.tVRepository = tVRepository;
    }


    @Override
    public List<TVModel> getTVsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String technology, String category)
    {
        Specification<TVModel> spec = tvModelRepository.combinedSpecification(deviceName, country, manufacturer,
                                                                              isOnlineOrderAvailable,
                                                                              isInstallmentAvailable, serialNum,
                                                                              modelName, color, maxPrice, minPrice,
                                                                              isAvailable, technology, category);

        return tvModelRepository.findAll(spec);
    }

    @Override
    public List<Model> getTVsBy(String modelName, String color, Double minPrice, Double maxPrice) {
        return deviceService.getDeviceBy(ModelType.TV, modelName, color, minPrice, maxPrice);
    }

    @Override
    public TV createTV(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable)
    {
        TV tv = new TV();
        tv.setDeviceName(deviceName);
        tv.setCountry(country);
        tv.setManufacturer(manufacturer);
        tv.setOnlineOrderAvailable(isOnlineOrderAvailable);
        tv.setInstallmentAvailable(isInstallmentAvailable);
        tVRepository.save(tv);
        return tv;
    }

    @Override
    public TVModel createTVModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, String technology,
            String category)
    {
        if (tvModelRepository.findById(serialNum).isPresent()) {
            throw new DBContainsModelException();
        }
        Specification<TV> tvSpecification = tVRepository.combinedSpecification(deviceName, country, manufacturer);
        TV tv = tVRepository.findAll(tvSpecification).stream().findAny().orElseThrow(DeviceNotFoundException::new);
        TVModel tvModel = new TVModel();
        tvModel.setSerialNum(serialNum);
        tvModel.setModelName(modelName);
        tvModel.setColor(color);
        tvModel.setPrice(Price);
        tvModel.setSizeHeight(sizeH);
        tvModel.setSizeLength(sizeL);
        tvModel.setSizeWidth(sizeW);
        tvModel.setAvailable(isAvailable);
        tvModel.setCategory(category);
        tvModel.setTechnology(technology);
        tvModel.setTv(tv);

        tvModelRepository.save(tvModel);
        return tvModel;
    }


}
