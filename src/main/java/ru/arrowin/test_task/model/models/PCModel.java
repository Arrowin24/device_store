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
}
