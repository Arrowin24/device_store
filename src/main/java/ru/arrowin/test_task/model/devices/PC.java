package ru.arrowin.test_task.model.devices;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.arrowin.test_task.model.models.PCModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "pc")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PC extends Device{
    @OneToMany(mappedBy = "pc", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<PCModel> models;
}
