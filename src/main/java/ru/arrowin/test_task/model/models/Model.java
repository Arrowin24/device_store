package ru.arrowin.test_task.model.models;

import lombok.*;

import javax.persistence.*;

@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Model {
    @Id private String serialNum;
    private String modelName;
    private String color;
    private double price;
    private double sizeHeight;
    private double sizeLength;
    private double sizeWidth;
    private boolean isAvailable;


    public String toText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Модель: ").append(modelName).append(" ");
        stringBuilder.append("Цена: ").append(price).append(" ");
        stringBuilder.append("Цвет: ").append(color).append(" ");
        stringBuilder.append("Размеры (д/ш/в): ").append(sizeLength).append("/").append(sizeWidth).append("/")
                     .append(sizeHeight).append(" ");
        if (isAvailable) {
            stringBuilder.append("Товар в наличи").append(" ");
        } else {
            stringBuilder.append("Товара нет в наличии").append(" ");
        }
        return stringBuilder.toString();
    }

}
