package ru.arrowin.test_task.model.devices;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.arrowin.test_task.model.models.VacuumModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "vacuum")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Vacuum extends Device {
    @OneToMany(mappedBy = "vacuum", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<VacuumModel> models;
}
