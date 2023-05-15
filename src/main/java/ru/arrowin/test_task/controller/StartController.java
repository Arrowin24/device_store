package ru.arrowin.test_task.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.arrowin.test_task.service.impl.StartDataService;

import java.util.List;

@Tag(
        name = "Пополнение базы данных",
        description = "Пополнение базы данных перед первым использованием. Проверка всего списка техники"
)
@RestController
@RequestMapping("/start")
public class StartController {

    private final StartDataService startDataService;

    public StartController(StartDataService startDataService) {
        this.startDataService = startDataService;
    }

    @Operation(
            summary = "Загрузка стартового реестра техники",
            description = "Помещает в базу данных по 3 вида техники с 2 моделями на каждый вид"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Стартовый набор техники успешно добавился в пустую базу данных"
                    ), @ApiResponse(
                    responseCode = "500",
                    description =
                            "Возможно в базе данных уже находился какой то набор техники. Поэтому произошла " +
                                    "ошибка. Если хотите заново загрузить стартовый реестр, то удалите с базы данных "
                                    + "предыдущие данные."
            )
            }
    )
    @PutMapping
    public ResponseEntity<Void> addStartData() {
        startDataService.uploadStartData();
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Список техники",
            description = "Получения списка техники в базе данных"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Представлен набор всей техники в реестре"
                    ), @ApiResponse(
                    responseCode = "500",
                    description =
                            "Возможно в базе данных уже находился какой то набор техники. Поэтому произошла " +
                                    "ошибка. Если хотите заново загрузить стартовый реестр, то удалите с базы данных "
                                    + "предыдущие данные."
            )
            }
    )
    @GetMapping(
            path = "/getAll",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<String>> getAllData() {
        List<String> devices = startDataService.getAllData();
        return ResponseEntity.ok(devices);
    }
}
