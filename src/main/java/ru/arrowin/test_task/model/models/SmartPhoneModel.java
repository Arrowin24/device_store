package ru.arrowin.test_task.model.models;

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
    private SmartPhone smartPhone;
}
