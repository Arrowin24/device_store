package ru.arrowin.test_task.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.arrowin.test_task.exception.DBContainsModelException;
import ru.arrowin.test_task.exception.DeviceNotFoundException;
import ru.arrowin.test_task.model.devices.Vacuum;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.VacuumModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.VacuumService;
import ru.arrowin.test_task.service.repository.device.VacuumRepository;
import ru.arrowin.test_task.service.repository.model.VacuumModelRepository;

import java.util.List;

@Service
public class VacuumServiceImpl implements VacuumService {
    private final VacuumModelRepository vacuumModelRepository;

    private final DeviceService deviceService;
    private final VacuumRepository vacuumRepository;


    public VacuumServiceImpl(
            VacuumModelRepository vacuumModelRepository, DeviceService deviceService, VacuumRepository vacuumRepository)
    {
        this.vacuumModelRepository = vacuumModelRepository;
        this.deviceService = deviceService;
        this.vacuumRepository = vacuumRepository;
    }


    @Override
    public List<VacuumModel> getVacuumsByParam(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, double containerVolume, int modesNum)
    {
        Specification<VacuumModel> spec = vacuumModelRepository.combinedSpecification(deviceName, country, manufacturer,
                                                                                      isOnlineOrderAvailable,
                                                                                      isInstallmentAvailable, serialNum,
                                                                                      modelName, color, maxPrice,
                                                                                      minPrice, isAvailable,
                                                                                      containerVolume, modesNum);

        return vacuumModelRepository.findAll(spec);
    }

    @Override
    public List<Model> getVacuumsBy(String modelName, String color, Double minPrice, Double maxPrice) {
        return deviceService.getDeviceBy(ModelType.VACUUM, modelName, color, minPrice, maxPrice);
    }

    @Override
    public Vacuum createVacuum(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable)
    {
        Vacuum vacuum = new Vacuum();
        vacuum.setDeviceName(deviceName);
        vacuum.setCountry(country);
        vacuum.setManufacturer(manufacturer);
        vacuum.setOnlineOrderAvailable(isOnlineOrderAvailable);
        vacuum.setInstallmentAvailable(isInstallmentAvailable);
        vacuumRepository.save(vacuum);
        return vacuum;
    }

    @Override
    public VacuumModel createVacuumModel(
            String deviceName, String country, String manufacturer, String serialNum, String modelName, String color,
            double Price, double sizeH, double sizeL, double sizeW, boolean isAvailable, double containerVolume,
            int modesNum)
    {
        if (vacuumModelRepository.findById(serialNum).isPresent()) {
            throw new DBContainsModelException();
        }
        Specification<Vacuum> vacuumSpecification = vacuumRepository.combinedSpecification(deviceName, country,
                                                                                           manufacturer);
        Vacuum vacuum = vacuumRepository.findAll(vacuumSpecification).stream().findAny()
                                        .orElseThrow(DeviceNotFoundException::new);

        VacuumModel vacuumModel = new VacuumModel();
        vacuumModel.setSerialNum(serialNum);
        vacuumModel.setModelName(modelName);
        vacuumModel.setColor(color);
        vacuumModel.setPrice(Price);
        vacuumModel.setSizeHeight(sizeH);
        vacuumModel.setSizeLength(sizeL);
        vacuumModel.setSizeWidth(sizeW);
        vacuumModel.setAvailable(isAvailable);
        vacuumModel.setContainerVolume(containerVolume);
        vacuumModel.setModesNum(modesNum);
        vacuumModel.setVacuum(vacuum);

        vacuumModelRepository.save(vacuumModel);
        return vacuumModel;
    }

}
