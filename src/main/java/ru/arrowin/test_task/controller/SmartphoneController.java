package ru.arrowin.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.test_task.model.devices.SmartPhone;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.SmartPhoneModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.SmartphoneService;
import ru.arrowin.test_task.service.impl.SortType;

import java.util.List;

@Tag(
        name = "Операции c телефонами",
        description = "Поиск и добавление новой линейки и моделей телефонов."
)
@RequestMapping("/api/device/smartphone")
@RestController
public class SmartphoneController {

    private final SmartphoneService smartphoneService;

    public SmartphoneController(SmartphoneService smartphoneService) {
        this.smartphoneService = smartphoneService;
    }

    @Operation(
            summary = "Поиск телефонов по всем доступным параметрам",
            description = "Получения списка телефонов с учетом всех парамеров"
    )
    @GetMapping(
            path = "/getByAttributes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getVacuums(
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
                    name = "ОЗУ",
                    required = false
            ) int memory,
            @RequestParam(
                    name = "Количество камер",
                    required = false
            ) int cameraNums,
            @RequestParam(
                    name = "Вид сортировки",
                    defaultValue = "BY_NAME_INCREASING"
            ) SortType sortType)
    {
        List<SmartPhoneModel> filteredModels = smartphoneService.getSmartphonesByParam(deviceName, country,
                                                                                       manufacturer,
                                                                                       isOnlineOrderAvailable,
                                                                                       isInstallmentAvailable,
                                                                                       serialNum, modelName, color,
                                                                                       maxPrice, minPrice, isAvailable,
                                                                                       memory, cameraNums);
        filteredModels = DeviceService.sortBy(filteredModels, sortType);
        List<String> answer = DeviceService.convertModelsToText(filteredModels);
        return ResponseEntity.ok(answer);
    }

    @Operation(
            summary = "Создание новой линейки телефонов",
            description = "Создает и добавляет в базу данных новую линейку телефонов. Есть обязательные поля!"
    )
    @PostMapping("/newSmartphoneDevice")
    public ResponseEntity<SmartPhone> createTV(
            @RequestParam(
                    name = "Название линейки"
            ) String deviceName,
            @RequestParam(
                    name = "Страна"
            ) String country,
            @RequestParam(
                    name = "Изготовитель"
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
            ) boolean isInstallmentAvailable)
    {
        SmartPhone smartPhone = smartphoneService.createSmartphone(deviceName, country, manufacturer,
                                                                   isOnlineOrderAvailable, isInstallmentAvailable);
        return ResponseEntity.ok(smartPhone);
    }

    @Operation(
            summary = "Создание новой модели в линейку телефонов",
            description = "Создает и добавляет в базу данных новую модель телефонов. Есть обязательные поля! " +
                    "Убедитесь, что вы добавляете модель в линейку телефонов, которая уже существует."
    )
    @PostMapping("/newSmartPhoneModel")
    public ResponseEntity<SmartPhoneModel> createTVModel(
            @RequestParam(
                    name = "Название линейки"
            ) String deviceName,
            @RequestParam(
                    name = "Страна"
            ) String country,
            @RequestParam(
                    name = "Изготовитель"
            ) String manufacturer,
            @RequestParam(
                    name = "Серийный номер"
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
                    name = "Стоимость",
                    required = false,
                    defaultValue = "1000000"
            ) double price,
            @RequestParam(
                    name = "Высота, см",
                    required = false,
                    defaultValue = "0"
            ) double sizeH,
            @RequestParam(
                    name = "Длина, см",
                    required = false,
                    defaultValue = "0"
            ) double sizeL,
            @RequestParam(
                    name = "Ширина, см",
                    required = false,
                    defaultValue = "0"
            ) double sizeW,
            @RequestParam(
                    name = "Товар в наличии",
                    required = false,
                    defaultValue = "false"
            ) boolean isAvailable,
            @RequestParam(
                    name = "ОЗУ",
                    required = false,
                    defaultValue = "0"
            ) int memory,
            @RequestParam(
                    name = "Количество камер",
                    required = false,
                    defaultValue = "0"
            ) int cameraNums)
    {
        SmartPhoneModel model = smartphoneService.createSmartPhoneModel(deviceName, country, manufacturer, serialNum,
                                                                        modelName, color, price, sizeH, sizeL, sizeW,
                                                                        isAvailable, memory, cameraNums);
        return ResponseEntity.ok(model);
    }

    @Operation(
            summary = "Поиск техники по основным параметрам (Имя модели, цвет, пределы стоимости.)",
            description =
                    "Получения списка техники с учетом всех фильтров и сортировок. Необходимый вид сортировки " +
                            "можно задать с помощью поля \"Вид сортировки\""
    )
    @GetMapping(
            path = "/findByParams",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getBy(
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
        List<Model> models = smartphoneService.getSmartphonesBy(modelName, color, minPrice, maxPrice);
        models = DeviceService.sortBy(models, sortType);
        List<String> modelsToText = DeviceService.convertModelsToText(models);
        return ResponseEntity.ok(modelsToText);
    }
}
