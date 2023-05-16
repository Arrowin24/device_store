package ru.arrowin.test_task.service.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {
    BY_NAME_INCREASING, BY_NAME_DECREASING, BY_PRICE_INCREASING, BY_PRICE_DECREASING
}
