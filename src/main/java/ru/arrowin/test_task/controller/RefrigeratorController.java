package ru.arrowin.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.test_task.model.devices.Refrigerator;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.RefrigeratorModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.RefrigeratorService;
import ru.arrowin.test_task.service.impl.SortType;

import java.util.List;

@Tag(
        name = "Операции c холодильниками",
        description = "Поиск и добавление новой линейки и моделей холодильников."
)
@RequestMapping("/api/device/refrigerator")
@RestController
public class RefrigeratorController {

    private final RefrigeratorService refrigeratorService;

    public RefrigeratorController(RefrigeratorService refrigeratorService) {
        this.refrigeratorService = refrigeratorService;
    }

    @Operation(
            summary = "Поиск холодильников по всем доступным параметрам",
            description = "Получения списка холодильников с учетом всех фильтров"
    )
    @GetMapping(
            path = "/getByAttributes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getRefrigerators(
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
                    name = "Количество дверей",
                    required = false
            ) int doorsNum,
            @RequestParam(
                    name = "Тип компрессора",
                    required = false
            ) String compressorType,
            @RequestParam(
                    name = "Вид сортировки",
                    defaultValue = "BY_NAME_INCREASING"
            ) SortType sortType)
    {
        List<RefrigeratorModel> filteredModels = refrigeratorService.getRefrigeratorsByParam(deviceName, country,
                                                                                             manufacturer,
                                                                                             isOnlineOrderAvailable,
                                                                                             isInstallmentAvailable,
                                                                                             serialNum, modelName,
                                                                                             color, maxPrice, minPrice,
                                                                                             isAvailable, doorsNum,
                                                                                             compressorType);
        filteredModels = DeviceService.sortBy(filteredModels, sortType);
        List<String> answer = DeviceService.convertModelsToText(filteredModels);
        return ResponseEntity.ok(answer);
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
        List<Model> models = refrigeratorService.getRefrigeratorsBy(modelName, color, minPrice, maxPrice);
        models = DeviceService.sortBy(models, sortType);
        List<String> modelsToText = DeviceService.convertModelsToText(models);
        return ResponseEntity.ok(modelsToText);
    }

    @Operation(
            summary = "Создание новой линейки холодильников",
            description = "Создает и добавляет в базу данных новую линейку холодильников. Есть обязательные поля!"
    )
    @PostMapping("/newRefrigeratorDevice")
    public ResponseEntity<Refrigerator> createTV(
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
        Refrigerator refrigerator = refrigeratorService.createRefrigerator(deviceName, country, manufacturer,
                                                                           isOnlineOrderAvailable,
                                                                           isInstallmentAvailable);
        return ResponseEntity.ok(refrigerator);
    }

    @Operation(
            summary = "Создание новой модели в линейку холодильников",
            description = "Создает и добавляет в базу данных новую модель холодильников. Есть обязательные поля! " +
                    "Убедитесь, что вы добавляете модель в линейку холодильников, которая уже существует."
    )
    @PostMapping("/newRefrigeratorModel")
    public ResponseEntity<RefrigeratorModel> createTVModel(
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
                    name = "Количество дверей",
                    required = false,
                    defaultValue = "0"
            ) int doorsNum,
            @RequestParam(
                    name = "Тип компрессора",
                    required = false
            ) String compressorType)
    {
        RefrigeratorModel model = refrigeratorService.createRefrigeratorModel(deviceName, country, manufacturer,
                                                                              serialNum, modelName, color, price, sizeH,
                                                                              sizeL, sizeW, isAvailable, doorsNum,
                                                                              compressorType);
        return ResponseEntity.ok(model);
    }
}
