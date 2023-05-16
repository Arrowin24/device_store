package ru.arrowin.test_task.model.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelType {
    TV("tv"),
    VACUUM("vacuum"),
    REFRIGERATOR("refrigerator"),
    SMARTPHONE("smartphone"),
    PC ("pc"),
    ALL("all");
    private final String modelTypeName;
}
