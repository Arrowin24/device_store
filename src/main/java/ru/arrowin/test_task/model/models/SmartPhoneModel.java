package ru.arrowin.test_task.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.arrowin.test_task.model.devices.SmartPhone;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "smartphone_model")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SmartPhoneModel extends Model {
    private int memory;
    private int cameraNums;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private SmartPhone smartphone;

    @Override
    public String toText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Тип товара: Смартфон").append("; ");
        stringBuilder.append(super.toText());
        stringBuilder.append("Количество камер: ").append(cameraNums).append("; ");
        stringBuilder.append("ОЗУ: ").append(memory).append("; ");
        stringBuilder.append(smartphone.toText());
        return stringBuilder.toString();
    }
}
