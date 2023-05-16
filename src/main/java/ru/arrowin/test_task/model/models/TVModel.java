package ru.arrowin.test_task.model.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import ru.arrowin.test_task.model.devices.TV;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tv_model")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TVModel extends Model {
    private String category;
    private String technology;
    @ManyToOne
    @JoinColumn(name = "id")
    @JsonBackReference
    private TV tv;


    @Override
    public String toText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Тип товара: Телевизор").append("; ");
        stringBuilder.append(super.toText());
        stringBuilder.append("Категория: ").append(category).append("; ");
        stringBuilder.append("Тип технологии: ").append(technology).append("; ");
        stringBuilder.append(tv.toText());
        return stringBuilder.toString();
    }
}
