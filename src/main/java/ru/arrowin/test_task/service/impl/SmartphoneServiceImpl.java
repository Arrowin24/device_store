package ru.arrowin.test_task.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.arrowin.test_task.exception.DBContainsModelException;
import ru.arrowin.test_task.exception.DeviceNotFoundException;
import ru.arrowin.test_task.model.devices.SmartPhone;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.SmartPhoneModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.SmartphoneService;
import ru.arrowin.test_task.service.repository.device.SmartPhoneRepository;
import ru.arrowin.test_task.service.repository.model.SmartPhoneModelRepository;

import java.util.List;

@Service
public class SmartphoneServiceImpl implements SmartphoneService {
    private final SmartPhoneModelRepository smartPhoneModelRepository;
    private final DeviceService deviceService;
    private final SmartPhoneRepository smartPhoneRepository;

    public SmartphoneServiceImpl(
            SmartPhoneModelRepository smartPhoneModelRepository, DeviceService deviceService,
            SmartPhoneRepository smartPhoneRepository)
    {
        this.smartPhoneModelRepository = smartPhoneModelRepository;
        this.deviceService = deviceService;
        this.smartPhoneRepository = smartPhoneRepository;
    }

    @Override
    public List<SmartPhoneModel> getSmartphonesByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, int memory, int cameraNums)
    {
        Specification<SmartPhoneModel> spec = smartPhoneModelRepository.combinedSpecification(deviceName, country,
                                                                                              manufacturer,
                                                                                              isOnlineOrderAvailable,
                                                                                              isInstallmentAvailable,
                                                                                              serialNum, modelName,
                                                                                              color, maxPrice, minPrice,
                                                                                              isAvailable, memory,
                                                                                              cameraNums);

        return smartPhoneModelRepository.findAll(spec);
    }

    @Override
    public List<Model> getSmartphonesBy(String modelName, String color, Double minPrice, Double maxPrice) {
        return deviceService.getDeviceBy(ModelType.SMARTPHONE, modelName, color, minPrice, maxPrice);
    }

    @Override
    public SmartPhone createSmartphone(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable)
    {
        SmartPhone smartPhone = new SmartPhone();
        smartPhone.setDeviceName(deviceName);
        smartPhone.setCountry(country);
        smartPhone.setManufacturer(manufacturer);
        smartPhone.setOnlineOrderAvailable(isOnlineOrderAvailable);
        smartPhone.setInstallmentAvailable(isInstallmentAvailable);
        smartPhoneRepository.save(smartPhone);
        return smartPhone;
    }

    @Override
    public SmartPhoneModel createSmartPhoneModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, int memory, int cameraNums)
    {
        if (smartPhoneModelRepository.findById(serialNum).isPresent()) {
            throw new DBContainsModelException();
        }

        Specification<SmartPhone> smartPhoneSpecification = smartPhoneRepository.combinedSpecification(deviceName,
                                                                                                       country,
                                                                                                       manufacturer);
        SmartPhone smartPhone = smartPhoneRepository.findAll(smartPhoneSpecification).stream().findAny()
                                                    .orElseThrow(DeviceNotFoundException::new);
        SmartPhoneModel model = new SmartPhoneModel();
        model.setSerialNum(serialNum);
        model.setModelName(modelName);
        model.setColor(color);
        model.setPrice(Price);
        model.setSizeHeight(sizeH);
        model.setSizeLength(sizeL);
        model.setSizeWidth(sizeW);
        model.setAvailable(isAvailable);
        model.setMemory(memory);
        model.setCameraNums(cameraNums);
        model.setSmartphone(smartPhone);

        smartPhoneModelRepository.save(model);
        return model;
    }

}
