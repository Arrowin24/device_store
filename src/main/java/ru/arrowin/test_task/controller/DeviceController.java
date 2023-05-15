package ru.arrowin.test_task.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.TVModel;
import ru.arrowin.test_task.service.DeviceService;

import java.util.List;

@Tag(
        name = "Работа со всеми видами техники.",
        description = "По выделенным атрибутам необходимо реализовать поиск по наименованию, " + "вне " +
                "зависимости от регистра, а также реализовать фильтрацию по виду техники, цвету, цене (от/до)."
)
@RequestMapping("/api/device/")
@RestController
public class DeviceController {

    private final DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping(
            path = "/findByAllParams",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getDevicesByAllParams(
            @RequestParam(
                    name = "Название линейки",
                    required = false
            ) String deviceName,
            @RequestParam(
                    name = "Страна",
                    required = false
            ) String country,
            @RequestParam(required = false) String manufacturer,
            @RequestParam(
                    required = false,
                    defaultValue = "false"
            ) boolean isOnlineOrderAvailable,
            @RequestParam(
                    required = false,
                    defaultValue = "false"
            ) boolean isInstallmentAvailable,
            @RequestParam(required = false) String serialNum,
            @RequestParam(required = false) String modelName,
            @RequestParam(required = false) String color,
            @RequestParam(
                    required = false,
                    defaultValue = "1000000"
            ) double maxPrice,
            @RequestParam(
                    required = false,
                    defaultValue = "0"
            ) double minPrice,
            @RequestParam(
                    required = false,
                    defaultValue = "false"
            ) boolean isAvailable)

    {
        List<Model> models = deviceService.getDeviceByAllParams(deviceName, country, manufacturer,
                                                                isOnlineOrderAvailable, isInstallmentAvailable,
                                                                serialNum, modelName, color, maxPrice, minPrice,
                                                                isAvailable);
        List<String> modelsToText = deviceService.convertModelsToText(models);
        return ResponseEntity.ok(modelsToText);
    }

}
