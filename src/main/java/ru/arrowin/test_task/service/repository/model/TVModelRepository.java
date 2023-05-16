package ru.arrowin.test_task.service.repository.model;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ru.arrowin.test_task.model.models.Model;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.TVModel;

import javax.persistence.criteria.Expression;

@Repository
public interface TVModelRepository extends JpaRepository<TVModel, String>, JpaSpecificationExecutor<TVModel> {
    default Specification<TVModel> hasDeviceName(String deviceName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.TV.getModelTypeName().toLowerCase()).get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<TVModel> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.TV.getModelTypeName().toLowerCase()).get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<TVModel> hasOnlineOrder(boolean isOnlineOrderAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.TV.getModelTypeName().toLowerCase()).get("isOnlineOrderAvailable"),
                isOnlineOrderAvailable);
    }

    default Specification<TVModel> hasInstallment(boolean isInstallmentAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.TV.getModelTypeName().toLowerCase()).get("isInstallmentAvailable"),
                isInstallmentAvailable);
    }

    default Specification<TVModel> hasSerialNum(String serialNum) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(serialNum));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("serialNum"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<TVModel> hasModelName(String modelName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(modelName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("modelName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<TVModel> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(color));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("color"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<TVModel> hasPriceLessThan(double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), maxPrice);
    }

    default Specification<TVModel> hasPriceMoreThan(double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), minPrice);
    }

    default Specification<TVModel> hasAvailable(boolean isAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
    }

    default Specification<TVModel> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.TV.getModelTypeName().toLowerCase()).get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<TVModel> hasTechnology(String technology) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(technology));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("technology"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<TVModel> hasCategory(String category) {

        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(category));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("category"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }


    default Specification<TVModel> combinedSpecification(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, String technology, String category)
    {
        Specification<TVModel> result = Specification.where(null);
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
        if (technology != null) {
            result = result.and(hasTechnology(technology));
        }
        if (category != null) {
            result = result.and(hasCategory(category));
        }
        return result;
    }

    default Specification<TVModel> combinedModelSpecification(
            String modelName, String color, Double minPrice, Double maxPrice)
    {
        Specification<TVModel> result = Specification.where(null);
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
