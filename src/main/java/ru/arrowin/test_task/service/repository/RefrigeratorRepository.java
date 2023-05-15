package ru.arrowin.test_task.service.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.RefrigeratorModel;


import javax.persistence.criteria.Expression;

public interface RefrigeratorRepository
        extends JpaRepository<RefrigeratorModel, String>, JpaSpecificationExecutor<RefrigeratorModel>,
        ModelSpecification<RefrigeratorModel>
{
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
        result.and(
                combinedModelSpecification(ModelType.REFRIGERATOR.getModelTypeName(), deviceName, country, manufacturer,
                                           isOnlineOrderAvailable, isInstallmentAvailable, serialNum, modelName, color,
                                           maxPrice, minPrice, isAvailable));
        if (doorsNum > 0) {
            result = result.and(hasDoorsNum(doorsNum));
        }
        if (compressorType != null) {
            result = result.and(hasCompressorType(compressorType));
        }
        return result;
    }
}
