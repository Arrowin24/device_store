package ru.arrowin.test_task.service.repository.model;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.SmartPhoneModel;
import ru.arrowin.test_task.model.models.TVModel;
import ru.arrowin.test_task.model.models.VacuumModel;

import javax.persistence.criteria.Expression;

@Repository
public interface SmartPhoneModelRepository
        extends JpaRepository<SmartPhoneModel, String>, JpaSpecificationExecutor<SmartPhoneModel>,
        ModelSpecification<SmartPhoneModel>
{
    default Specification<SmartPhoneModel> hasDeviceName(String deviceName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.SMARTPHONE.getModelTypeName().toLowerCase()).get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhoneModel> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.SMARTPHONE.getModelTypeName().toLowerCase()).get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhoneModel> hasOnlineOrder(boolean isOnlineOrderAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.SMARTPHONE.getModelTypeName().toLowerCase()).get("isOnlineOrderAvailable"),
                isOnlineOrderAvailable);
    }

    default Specification<SmartPhoneModel> hasInstallment(boolean isInstallmentAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.SMARTPHONE.getModelTypeName().toLowerCase()).get("isInstallmentAvailable"),
                isInstallmentAvailable);
    }

    default Specification<SmartPhoneModel> hasSerialNum(String serialNum) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(serialNum));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("serialNum"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhoneModel> hasModelName(String modelName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(modelName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("modelName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhoneModel> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(color));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("color"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhoneModel> hasPriceLessThan(double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), maxPrice);
    }

    default Specification<SmartPhoneModel> hasPriceMoreThan(double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), minPrice);
    }

    default Specification<SmartPhoneModel> hasAvailable(boolean isAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
    }

    default Specification<SmartPhoneModel> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.SMARTPHONE.getModelTypeName().toLowerCase()).get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhoneModel> hasMemory(int memory) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("memory"), memory);
    }

    default Specification<SmartPhoneModel> hasCameraNums(int cameraNums) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("cameraNums"), cameraNums);
    }


    default Specification<SmartPhoneModel> combinedSpecification(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, int memory, int cameraNums)
    {
        Specification<SmartPhoneModel> result = Specification.where(null);
        if (deviceName != null) {
            result = result.and(hasDeviceName(deviceName));
        }
        if (country != null) {
            result = result.and(hasCountry(country));
        }
        if (manufacturer != null) {
            result = result.and(hasManufacturer(manufacturer));
        }
        if (isOnlineOrderAvailable) {
            result = result.and(hasOnlineOrder(true)); //подумай про это
        }
        if (isInstallmentAvailable) {
            result = result.and(hasInstallment(true));
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
        if (memory > 0) {
            result = result.and(hasMemory(memory));
        }
        if (cameraNums > 0) {
            result = result.and(hasCameraNums(cameraNums));
        }
        return result;
    }

    default Specification<SmartPhoneModel> combinedModelSpecification(
            String modelName, String color, Double minPrice, Double maxPrice)
    {
        Specification<SmartPhoneModel> result = Specification.where(null);
        if (modelName != null) {
            result = result.and(hasModelName(modelName));
        }
        if (color != null) {
            result = result.and(hasColor(color));
        }
        result = result.and(hasPriceLessThan(maxPrice));
        result = result.and(hasPriceMoreThan(minPrice));
        return result;
    }
}
