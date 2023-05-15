package ru.arrowin.test_task.service.repository.model;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.RefrigeratorModel;
import ru.arrowin.test_task.model.models.SmartPhoneModel;


import javax.persistence.criteria.Expression;

@Repository
public interface RefrigeratorModelRepository
        extends JpaRepository<RefrigeratorModel, String>, JpaSpecificationExecutor<RefrigeratorModel>
{
    default Specification<RefrigeratorModel> hasDeviceName(String deviceName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.REFRIGERATOR.getModelTypeName().toLowerCase()).get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<RefrigeratorModel> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.REFRIGERATOR.getModelTypeName().toLowerCase()).get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<RefrigeratorModel> hasOnlineOrder(boolean isOnlineOrderAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.REFRIGERATOR.getModelTypeName().toLowerCase()).get("isOnlineOrderAvailable"),
                isOnlineOrderAvailable);
    }

    default Specification<RefrigeratorModel> hasInstallment(boolean isInstallmentAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.REFRIGERATOR.getModelTypeName().toLowerCase()).get("isInstallmentAvailable"),
                isInstallmentAvailable);
    }

    default Specification<RefrigeratorModel> hasSerialNum(String serialNum) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(serialNum));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("serialNum"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<RefrigeratorModel> hasModelName(String modelName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(modelName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("modelName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<RefrigeratorModel> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(color));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("color"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<RefrigeratorModel> hasPriceLessThan(double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), maxPrice);
    }

    default Specification<RefrigeratorModel> hasPriceMoreThan(double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), minPrice);
    }

    default Specification<RefrigeratorModel> hasAvailable(boolean isAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
    }

    default Specification<RefrigeratorModel> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.REFRIGERATOR.getModelTypeName().toLowerCase()).get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<RefrigeratorModel> hasDoorsNum(int doorsNum) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("doorsNum"), doorsNum);
    }

    default Specification<RefrigeratorModel> hasCompressorType(String compressorType) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(compressorType));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("compressorType"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }


    default Specification<RefrigeratorModel> combinedSpecification(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, int doorsNum, String compressorType)
    {
        Specification<RefrigeratorModel> result = Specification.where(null);
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
        if (doorsNum > 0) {
            result = result.and(hasDoorsNum(doorsNum));
        }
        if (compressorType != null) {
            result = result.and(hasCompressorType(compressorType));
        }
        return result;
    }

    default Specification<RefrigeratorModel> combinedModelSpecification(
            String modelName, String color, Double minPrice, Double maxPrice)
    {
        Specification<RefrigeratorModel> result = Specification.where(null);
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
