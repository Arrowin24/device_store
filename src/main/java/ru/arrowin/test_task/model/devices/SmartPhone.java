package ru.arrowin.test_task.model.devices;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.arrowin.test_task.model.models.SmartPhoneModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "smartphone")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SmartPhone extends Device{
    @OneToMany(mappedBy = "smartPhone", cascade = CascadeType.ALL)
    private List<SmartPhoneModel> models;
}

