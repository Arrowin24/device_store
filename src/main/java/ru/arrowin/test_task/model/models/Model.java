package ru.arrowin.test_task.model.models;

import lombok.*;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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


    public String toTextFromModel() {
        return "Model{" + "serialNum='" + serialNum + '\'' + ", name='" + modelName + '\'' + ", color='" + color + '\'' + "," +
                " price=" + price + ", " + "sizeHeight=" + sizeHeight + ", sizeLength=" + sizeLength + ", sizeWidth=" + sizeWidth + ", " + '\'' + ", isAvailable=" + isAvailable;
    }

}
