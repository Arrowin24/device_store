package ru.arrowin.test_task.service.repository.model;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.PCModel;
import ru.arrowin.test_task.model.models.RefrigeratorModel;
import ru.arrowin.test_task.model.models.VacuumModel;

import javax.persistence.criteria.Expression;

@Repository
public interface PCModelRepository
        extends JpaRepository<PCModel, String>, JpaSpecificationExecutor<PCModel>, ModelSpecification<PCModel>
{
    default Specification<PCModel> hasDeviceName(String deviceName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.PC.getModelTypeName().toLowerCase()).get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<PCModel> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.PC.getModelTypeName().toLowerCase()).get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<PCModel> hasOnlineOrder(boolean isOnlineOrderAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.REFRIGERATOR.getModelTypeName().toLowerCase()).get("isOnlineOrderAvailable"),
                isOnlineOrderAvailable);
    }

    default Specification<PCModel> hasInstallment(boolean isInstallmentAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.PC.getModelTypeName().toLowerCase()).get("isInstallmentAvailable"),
                isInstallmentAvailable);
    }

    default Specification<PCModel> hasSerialNum(String serialNum) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(serialNum));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("serialNum"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<PCModel> hasModelName(String modelName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(modelName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("modelName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<PCModel> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(color));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("color"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<PCModel> hasPriceLessThan(double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), maxPrice);
    }

    default Specification<PCModel> hasPriceMoreThan(double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), minPrice);
    }

    default Specification<PCModel> hasAvailable(boolean isAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
    }

    default Specification<PCModel> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.PC.getModelTypeName().toLowerCase()).get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<PCModel> hasProcessorType(String processorType) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(processorType));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("processorType"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<PCModel> hasCategory(String category) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(category));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("category"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }


    default Specification<PCModel> combinedSpecification(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String processorType, String category)
    {
        Specification<PCModel> result = Specification.where(null);
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
        if (processorType != null) {
            result = result.and(hasProcessorType(processorType));
        }
        if (category != null) {
            result = result.and(hasCategory(category));
        }
        return result;
    }

    default Specification<PCModel> combinedModelSpecification(
            String modelName, String color, Double minPrice, Double maxPrice)
    {
        Specification<PCModel> result = Specification.where(null);
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
