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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String deviceName;
    private String country;
    private String manufacturer;
    private boolean isOnlineOrderAvailable;
    private boolean isInstallmentAvailable;

    public <T extends Model> List<T> getAvailableModels(List<T> models) {
        return models.stream().filter(Model::isAvailable).collect(Collectors.toList());
    }

    public String toText() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Наименование  линейки техники: ").append(deviceName).append("; ");
        stringBuilder.append("Страна производства: ").append(country).append("; ");
        stringBuilder.append("Фирма производитель: ").append(manufacturer).append("; ");
        if (isOnlineOrderAvailable) {
            stringBuilder.append("Доступен для онлайн заказа").append("; ");
        } else {
            stringBuilder.append("Не доступен для онлайн заказа").append("; ");
        }
        if (isInstallmentAvailable) {
            stringBuilder.append("Доступен для рассрочки").append("; ");
        } else {
            stringBuilder.append("Не доступен для рассрочки").append("; ");
        }
        return stringBuilder.toString();
    }
}
