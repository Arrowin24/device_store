package ru.arrowin.test_task.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.arrowin.test_task.model.devices.Refrigerator;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "refrigerator_model")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RefrigeratorModel extends Model {
    private int doorsNum;
    private String compressorType;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private Refrigerator refrigerator;

    @Override
    public String toText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Тип товара: Холодильник").append("; ");
        stringBuilder.append(super.toText());
        stringBuilder.append("Количество дверей: ").append(doorsNum).append("; ");
        stringBuilder.append("Тип компрессора: ").append(compressorType).append("; ");
        stringBuilder.append(refrigerator.toText());
        return stringBuilder.toString();
    }
}
