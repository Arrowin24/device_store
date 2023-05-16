package ru.arrowin.test_task.model.devices;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.arrowin.test_task.model.models.TVModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


@Entity
@Table(name = "tv")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TV extends Device{
    @OneToMany(mappedBy = "tv", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<TVModel> models;
}

