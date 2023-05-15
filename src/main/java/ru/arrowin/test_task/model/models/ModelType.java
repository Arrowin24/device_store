package ru.arrowin.test_task.model.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ModelType {
    TV("TV"),
    VACUUM("Vacuum"),
    REFRIGERATOR("Refrigerator"),
    SMARTPHONE("SmartPhone"),
    PC ("PC");
    private final String modelTypeName;
}
