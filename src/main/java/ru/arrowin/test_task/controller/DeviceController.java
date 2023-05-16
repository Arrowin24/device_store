package ru.arrowin.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.impl.SortType;

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

    @Operation(
            summary = "Поиск техники по всем доступным параметрам",
            description = "Получения списка техники с учетом всех фильтров"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Представлен набор отфильтрованной техники в реестре"
                    ), @ApiResponse(
                    responseCode = "500",
                    description = "Ошибка сервера"
            )
            }
    )
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
            @RequestParam(
                    name = "Изготовитель",
                    required = false
            ) String manufacturer,
            @RequestParam(
                    name = "Возможность заказа онлайн",
                    required = false,
                    defaultValue = "false"
            ) boolean isOnlineOrderAvailable,
            @RequestParam(
                    name = "Возможность оформления рассрочки",
                    required = false,
                    defaultValue = "false"
            ) boolean isInstallmentAvailable,
            @RequestParam(
                    name = "Серийный номер",
                    required = false
            ) String serialNum,
            @RequestParam(
                    name = "Название модели",
                    required = false
            ) String modelName,
            @RequestParam(
                    name = "Цвет",
                    required = false
            ) String color,
            @RequestParam(
                    name = "Максимальное значение стоимости",
                    required = false,
                    defaultValue = "1000000"
            ) double maxPrice,
            @RequestParam(
                    name = "Минимальное значение стоимости",
                    required = false,
                    defaultValue = "0"
            ) double minPrice,
            @RequestParam(
                    name = "Товар в наличии",
                    required = false,
                    defaultValue = "false"
            ) boolean isAvailable,
            @RequestParam(
                    name = "Вид сортировки",
                    required = false,
                    defaultValue = "BY_NAME_INCREASING"
            ) SortType sortType)
    {
        List<Model> models = deviceService.getDeviceByAllParams(deviceName, country, manufacturer,
                                                                isOnlineOrderAvailable, isInstallmentAvailable,
                                                                serialNum, modelName, color, maxPrice, minPrice,
                                                                isAvailable);
        models = DeviceService.sortBy(models, sortType);
        List<String> modelsToText = DeviceService.convertModelsToText(models);
        return ResponseEntity.ok(modelsToText);
    }

    @Operation(
            summary = "Поиск техники по основным параметрам (Тип техники, имя, цвет, пределы стоимости.)",
            description =
                    "Получения списка техники с учетом всех фильтров и сортировок. Необходимый вид сортировки " +
                            "можно задать с помощью поля \"Вид сортировки\""
    )
    @GetMapping(
            path = "/findByParams",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getDevicesBy(
            @RequestParam(
                    name = "Тип техники",
                    required = false,
                    defaultValue = "ALL"
            ) ModelType modelType,

            @RequestParam(
                    name = "Название модели",
                    required = false
            ) String modelName,
            @RequestParam(
                    name = "Цвет",
                    required = false
            ) String color,
            @RequestParam(
                    name = "Максимальное значение стоимости",
                    required = false,
                    defaultValue = "1000000"
            ) double maxPrice,
            @RequestParam(
                    name = "Минимальное значение стоимости",
                    required = false,
                    defaultValue = "0"
            ) double minPrice,
            @RequestParam(
                    name = "Вид сортировки",
                    required = false,
                    defaultValue = "BY_NAME_INCREASING"
            ) SortType sortType)
    {
        List<Model> models = deviceService.getDeviceBy(modelType, modelName, color, minPrice, maxPrice);
        models = DeviceService.sortBy(models, sortType);
        List<String> modelsToText = DeviceService.convertModelsToText(models);
        return ResponseEntity.ok(modelsToText);
    }

}
