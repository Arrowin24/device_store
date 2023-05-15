package ru.arrowin.test_task.model.devices;

import lombok.*;
import ru.arrowin.test_task.model.models.Model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.List;
import java.util.stream.Collectors;


@MappedSuperclass
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public abstract class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String deviceName;
    private String country;
    private String manufacturer;
    private boolean isOnlineOrderAvailable;
    private boolean isInstallmentAvailable;

    public <T extends Model> List<T> getAvailableModels(List<T> models) {
        return models.stream().filter(Model::isAvailable).collect(Collectors.toList());
    }

    public String toTextFromDevice() {
        return "Device{ name='" + deviceName + '\'' + ", country='" + country + '\'' + ", manufacturer" + "='" + manufacturer + '\'' + ", isOnlineOrderAvailable=" + isOnlineOrderAvailable + ", " + "isInstallmentAvailable=" + isInstallmentAvailable;
    }
}
