package ru.arrowin.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.test_task.model.devices.TV;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.TVModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.TVService;
import ru.arrowin.test_task.service.impl.SortType;

import java.util.List;

@Tag(
        name = "Операции c телевизорами",
        description = "Поиск и добавление новой линейки и моделей телевизоров."
)
@RequestMapping("/api/device/tv")
@RestController
public class TVController {

    private final TVService tvService;

    public TVController(TVService tvService) {
        this.tvService = tvService;
    }

    @Operation(
            summary = "Поиск телевизоров по всем доступным параметрам",
            description = "Получения списка телевизоров с учетом всех фильтров"
    )
    @GetMapping(
            path = "/getByAttributes",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getTvs(
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
                    name = "Технология",
                    required = false
            ) String technology,
            @RequestParam(
                    name = "Категория",
                    required = false
            ) String category,
            @RequestParam(
                    name = "Вид сортировки",
                    defaultValue = "BY_NAME_INCREASING"
            ) SortType sortType)
    {
        List<TVModel> filteredTvModels = tvService.getTVsByParam(deviceName, country, manufacturer,
                                                                 isOnlineOrderAvailable, isInstallmentAvailable,
                                                                 serialNum, modelName, color, maxPrice, minPrice,
                                                                 isAvailable, technology, category);
        filteredTvModels = DeviceService.sortBy(filteredTvModels, sortType);
        List<String> answer = DeviceService.convertModelsToText(filteredTvModels);
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
        List<Model> models = tvService.getTVsBy(modelName, color, minPrice, maxPrice);
        models = DeviceService.sortBy(models, sortType);
        List<String> modelsToText = DeviceService.convertModelsToText(models);
        return ResponseEntity.ok(modelsToText);
    }

    @Operation(
            summary = "Создание новой линейки телевизоров",
            description = "Создает и добавляет в базу данных новую линейку телевизоров. Есть обязательные поля!"
    )
    @PostMapping("/newTVDevice")
    public ResponseEntity<TV> createTV(
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
        TV tv = tvService.createTV(deviceName, country, manufacturer, isOnlineOrderAvailable, isInstallmentAvailable);
        return ResponseEntity.ok(tv);
    }

    @Operation(
            summary = "Создание новой модели в линейку телевизоров",
            description = "Создает и добавляет в базу данных новую модель телевизоров. Есть обязательные поля! " +
                    "Убедитесь, что вы добавляете модель в линейку телевизоров, которая уже существует."
    )
    @PostMapping("/newTVModel")
    public ResponseEntity<TVModel> createTVModel(
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
                    name = "Технология",
                    required = false
            ) String technology,
            @RequestParam(
                    name = "Категория",
                    required = false
            ) String category)
    {
        TVModel tvModel = tvService.createTVModel(deviceName, country, manufacturer, serialNum, modelName, color, price,
                                                  sizeH, sizeL, sizeW, isAvailable, technology, category);
        return ResponseEntity.ok(tvModel);
    }
}
