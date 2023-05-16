package ru.arrowin.test_task.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.arrowin.test_task.model.devices.PC;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pc_model")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PCModel extends Model{
    private String category;
    private String processorType;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private PC pc;

    @Override
    public String toText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Тип товара: PC").append("; ");
        stringBuilder.append(super.toText());
        stringBuilder.append("Категория: ").append(category).append("; ");
        stringBuilder.append("Тип процессора: ").append(processorType).append("; ");
        stringBuilder.append(pc.toText());
        return stringBuilder.toString();
    }
}
