package ru.arrowin.test_task.model.models;


import lombok.*;
import ru.arrowin.test_task.model.devices.Vacuum;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "vacuum_model")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VacuumModel extends Model {

    private double containerVolume;
    private int modesNum;
    @ManyToOne
    @JoinColumn(name = "id")
    private Vacuum vacuum;

    @Override
    public String toText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Тип товара: Холодильник").append(" ");
        stringBuilder.append(super.toText());
        stringBuilder.append("Объем пылесборника: ").append(containerVolume).append(" ");
        stringBuilder.append("Количество режимов: ").append(modesNum).append(" ");
        stringBuilder.append(vacuum.toText());
        return stringBuilder.toString();
    }
}
