package ru.arrowin.test_task.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.arrowin.test_task.model.models.TVModel;
import ru.arrowin.test_task.service.TVService;

import java.util.List;


@Tag(
        name = "Операции c телевизором",
        description = "получение всех теликов."
)
@RequestMapping("/api/device/tv")
@RestController
public class TVController {

    private final TVService tvService;

    public TVController(TVService tvService) {
        this.tvService = tvService;
    }

    /*@GetMapping
    public ResponseEntity<String> getAllTV() {
        String ans = tvService.getAllModels().stream().map(TVModel::toTextFromModel).collect(Collectors.toList())
                              .toString();
        return ResponseEntity.ok(ans);
    }

    @GetMapping("/getTVs")
    public ResponseEntity<List<TV>> getAllTVModel() {
        List<TV> tvModels = tvService.getAllTV();
        System.out.println(tvModels);
        return ResponseEntity.ok(tvModels);
    }*/

    @GetMapping(
            path = "/tvs",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TVModel>> getTvs(
            @RequestParam(required = false) String deviceName,
            @RequestParam(required = false) String country,
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
            ) boolean isAvailable,
            @RequestParam(required = false) String technology,
            @RequestParam(required = false) String category)
    {
        List<TVModel> filteredTvModels = tvService.getTVsByParam(deviceName, country, manufacturer,
                                                                 isOnlineOrderAvailable, isInstallmentAvailable,
                                                                 serialNum, modelName, color, maxPrice, minPrice,
                                                                 isAvailable, technology, category);
        return ResponseEntity.ok(filteredTvModels);
    }

}
