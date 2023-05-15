package ru.arrowin.test_task.service.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.arrowin.test_task.model.models.Model;

import javax.persistence.criteria.Expression;


public interface ModelSpecification<T extends Model> extends JpaSpecificationExecutor<T> {

    default Specification<T> hasDeviceName(String deviceName, String DEVICE_TYPE) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(DEVICE_TYPE.toLowerCase()).get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<T> hasCountry(String country, String DEVICE_TYPE) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(DEVICE_TYPE.toLowerCase()).get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<T> hasManufacturer(String manufacturer, String DEVICE_TYPE) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(DEVICE_TYPE.toLowerCase()).get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<T> hasOnlineOrder(boolean isOnlineOrderAvailable, String DEVICE_TYPE) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(DEVICE_TYPE.toLowerCase()).get("isOnlineOrderAvailable"), isOnlineOrderAvailable);
    }

    default Specification<T> hasInstallment(boolean isInstallmentAvailable, String DEVICE_TYPE) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(DEVICE_TYPE.toLowerCase()).get("isInstallmentAvailable"), isInstallmentAvailable);
    }

    default Specification<T> hasSerialNum(String serialNum) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(serialNum));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("serialNum"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<T> hasModelName(String modelName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(modelName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("modelName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<T> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(color));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("color"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<T> hasPriceLessThan(double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), maxPrice);
    }

    default Specification<T> hasPriceMoreThan(double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), minPrice);
    }

    default Specification<T> hasAvailable(boolean isAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
    }

    default Specification<T> combinedModelSpecification(
            String DEVICE_TYPE, String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable)
    {
        Specification<T> result = Specification.where(null);

        if (deviceName != null) {
            result = result.and(hasDeviceName(deviceName, DEVICE_TYPE));
        }
        if (country != null) {
            result = result.and(hasCountry(country, DEVICE_TYPE));
        }
        if (manufacturer != null) {
            result = result.and(hasManufacturer(manufacturer, DEVICE_TYPE));
        }
        if (isOnlineOrderAvailable) {
            result = result.and(hasOnlineOrder(true, DEVICE_TYPE)); //подумай про это
        }
        if (isInstallmentAvailable) {
            result = result.and(hasInstallment(true, DEVICE_TYPE));
        }
        if (serialNum != null) {
            result = result.and(hasSerialNum(serialNum));
        }
        if (modelName != null) {
            result = result.and(hasModelName(modelName));
        }
        if (color != null) {
            result = result.and(hasColor(color));
        }
        result = result.and(hasPriceLessThan(maxPrice));
        result = result.and(hasPriceMoreThan(minPrice));
        if (isAvailable) {
            result = result.and(hasAvailable(true)); //подумай про это
        }
        return result;
    }

}
