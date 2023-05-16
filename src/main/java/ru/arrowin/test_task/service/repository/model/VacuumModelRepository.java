package ru.arrowin.test_task.service.repository.model;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.TVModel;
import ru.arrowin.test_task.model.models.VacuumModel;

import javax.persistence.criteria.Expression;


@Repository
public interface VacuumModelRepository
        extends JpaRepository<VacuumModel, String>, JpaSpecificationExecutor<VacuumModel>
{
    default Specification<VacuumModel> hasDeviceName(String deviceName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.VACUUM.getModelTypeName().toLowerCase()).get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<VacuumModel> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.VACUUM.getModelTypeName().toLowerCase()).get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<VacuumModel> hasOnlineOrder(boolean isOnlineOrderAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.VACUUM.getModelTypeName().toLowerCase()).get("isOnlineOrderAvailable"),
                isOnlineOrderAvailable);
    }

    default Specification<VacuumModel> hasInstallment(boolean isInstallmentAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(
                root.get(ModelType.VACUUM.getModelTypeName().toLowerCase()).get("isInstallmentAvailable"),
                isInstallmentAvailable);
    }

    default Specification<VacuumModel> hasSerialNum(String serialNum) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(serialNum));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("serialNum"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<VacuumModel> hasModelName(String modelName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(modelName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("modelName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<VacuumModel> hasColor(String color) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(color));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("color"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<VacuumModel> hasPriceLessThan(double maxPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), maxPrice);
    }

    default Specification<VacuumModel> hasPriceMoreThan(double minPrice) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), minPrice);
    }

    default Specification<VacuumModel> hasAvailable(boolean isAvailable) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isAvailable"), isAvailable);
    }

    default Specification<VacuumModel> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(
                    root.get(ModelType.VACUUM.getModelTypeName().toLowerCase()).get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<VacuumModel> hasContainerVolume(double containerVolume) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("containerVolume"), containerVolume);
    }

    default Specification<VacuumModel> hasModesNum(int modesNum) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("modesNum"), modesNum);
    }


    default Specification<VacuumModel> combinedSpecification(
            String deviceName, String country, String manufacturer, boolean isOnlineOrderAvailable,
            boolean isInstallmentAvailable, String serialNum, String modelName, String color, double maxPrice,
            double minPrice, boolean isAvailable, double containerVolume, int modesNum)
    {
        Specification<VacuumModel> result = Specification.where(null);
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
        if (containerVolume > 0) {
            result = result.and(hasContainerVolume(containerVolume));
        }
        if (modesNum > 0) {
            result = result.and(hasModesNum(modesNum));
        }
        return result;
    }

    default Specification<VacuumModel> combinedModelSpecification(
            String modelName, String color, Double minPrice, Double maxPrice)
    {
        Specification<VacuumModel> result = Specification.where(null);
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
