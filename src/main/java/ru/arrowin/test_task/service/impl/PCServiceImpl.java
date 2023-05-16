package ru.arrowin.test_task.service.impl;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.arrowin.test_task.exception.DBContainsModelException;
import ru.arrowin.test_task.exception.DeviceNotFoundException;
import ru.arrowin.test_task.model.devices.PC;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.PCModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.PCService;
import ru.arrowin.test_task.service.repository.device.PCRepository;
import ru.arrowin.test_task.service.repository.model.PCModelRepository;

import java.util.List;

@Service
public class PCServiceImpl implements PCService {

    private final PCModelRepository pcModelRepository;

    private final DeviceService deviceService;
    private final PCRepository pcRepository;

    public PCServiceImpl(PCModelRepository pcModelRepository, DeviceService deviceService, PCRepository pcRepository) {
        this.pcModelRepository = pcModelRepository;
        this.deviceService = deviceService;
        this.pcRepository = pcRepository;
    }

    @Override
    public List<PCModel> getPCsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String processorType, String category)
    {
        Specification<PCModel> spec = pcModelRepository.combinedSpecification(deviceName, country, manufacturer,
                                                                              isOnlineOrderAvailable,
                                                                              isInstallmentAvailable, serialNum,
                                                                              modelName, color, maxPrice, minPrice,
                                                                              isAvailable, processorType, category);

        return pcModelRepository.findAll(spec);
    }

    @Override
    public List<Model> getPCsBy(String modelName, String color, Double minPrice, Double maxPrice) {
        return deviceService.getDeviceBy(ModelType.PC, modelName, color, minPrice, maxPrice);
    }

    @Override
    public PC createPC(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable)
    {
        PC pc = new PC();
        pc.setDeviceName(deviceName);
        pc.setCountry(country);
        pc.setManufacturer(manufacturer);
        pc.setOnlineOrderAvailable(isOnlineOrderAvailable);
        pc.setInstallmentAvailable(isInstallmentAvailable);
        pcRepository.save(pc);
        return pc;
    }

    @Override
    public PCModel createPCModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, String processorType,
            String category)
    {
        if (!pcModelRepository.findById(serialNum).isPresent()) {
            throw new DBContainsModelException();
        }
        Specification<PC> tvSpecification = pcRepository.combinedSpecification(deviceName, country, manufacturer);
        PC pc = pcRepository.findAll(tvSpecification).stream().findAny().orElseThrow(DeviceNotFoundException::new);
        PCModel pcModel = new PCModel();
        pcModel.setSerialNum(serialNum);
        pcModel.setModelName(modelName);
        pcModel.setColor(color);
        pcModel.setPrice(Price);
        pcModel.setSizeHeight(sizeH);
        pcModel.setSizeLength(sizeL);
        pcModel.setSizeWidth(sizeW);
        pcModel.setAvailable(isAvailable);
        pcModel.setCategory(category);
        pcModel.setProcessorType(processorType);
        pcModel.setPc(pc);

        pcModelRepository.save(pcModel);
        return pcModel;
    }


}
