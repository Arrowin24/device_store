package ru.arrowin.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.arrowin.test_task.model.devices.Vacuum;
import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.VacuumModel;
import ru.arrowin.test_task.service.DeviceService;
import ru.arrowin.test_task.service.VacuumService;
import ru.arrowin.test_task.service.impl.SortType;

import java.util.List;

@Tag(
        name = "Операции c пылесосами",
        description = "Поиск и добавление новой линейки и моделей пылесосов."
)
@RequestMapping("/api/device/vacuum")
@RestController
public class VacuumController {

    private final VacuumService vacuumService;

    public VacuumController(VacuumService vacuumService) {
        this.vacuumService = vacuumService;
    }

    @Operation(
            summary = "Поиск пылесосов по всем доступным параметрам",
            description = "Получения списка пылесосов с учетом всех фильтров"
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
                    name = "Объем пылесборника",
                    required = false
            ) double containerVolume,
            @RequestParam(
                    name = "Количество режимов",
                    required = false
            ) int modesNum,
            @RequestParam(
                    name = "Вид сортировки",
                    defaultValue = "BY_NAME_INCREASING"
            ) SortType sortType)
    {
        List<VacuumModel> filteredModels = vacuumService.getVacuumsByParam(deviceName, country, manufacturer,
                                                                           isOnlineOrderAvailable,
                                                                           isInstallmentAvailable, serialNum, modelName,
                                                                           color, maxPrice, minPrice, isAvailable,
                                                                           containerVolume, modesNum);
        filteredModels = DeviceService.sortBy(filteredModels, sortType);
        List<String> answer = DeviceService.convertModelsToText(filteredModels);
        return ResponseEntity.ok(answer);
    }

    @Operation(
            summary = "Создание новой линейки пылесосов",
            description = "Создает и добавляет в базу данных новую линейку пылесосов. Есть обязательные поля!"
    )
    @PostMapping("/newVacuumDevice")
    public ResponseEntity<Vacuum> createTV(
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
        Vacuum vacuum = vacuumService.createVacuum(deviceName, country, manufacturer, isOnlineOrderAvailable,
                                                   isInstallmentAvailable);
        return ResponseEntity.ok(vacuum);
    }

    @Operation(
            summary = "Создание новой модели в линейку пылесосов",
            description = "Создает и добавляет в базу данных новую модель пылесосов. Есть обязательные поля! " +
                    "Убедитесь, что вы добавляете модель в линейку пылесосов, которая уже существует."
    )
    @PostMapping("/newVacuumModel")
    public ResponseEntity<VacuumModel> createTVModel(
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
                    name = "Объем пылесборника",
                    required = false,
                    defaultValue = "0"
            ) double containerVolume,
            @RequestParam(
                    name = "Количество режимов",
                    required = false,
                    defaultValue = "0"
            ) int modesNum)
    {
        VacuumModel model = vacuumService.createVacuumModel(deviceName, country, manufacturer, serialNum, modelName,
                                                            color, price, sizeH, sizeL, sizeW, isAvailable,
                                                            containerVolume, modesNum);
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
        List<Model> models = vacuumService.getVacuumsBy(modelName, color, minPrice, maxPrice);
        models = DeviceService.sortBy(models, sortType);
        List<String> modelsToText = DeviceService.convertModelsToText(models);
        return ResponseEntity.ok(modelsToText);
    }
}
