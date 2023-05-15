package ru.arrowin.test_task.service.repository.model;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.PCModel;

import javax.persistence.criteria.Expression;
@Repository
public interface PCModelRepository
        extends JpaRepository<PCModel, String>, JpaSpecificationExecutor<PCModel>, ModelSpecification<PCModel>
{
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
        result.and(combinedModelSpecification(ModelType.PC.getModelTypeName(), deviceName, country, manufacturer,
                                              isOnlineOrderAvailable, isInstallmentAvailable, serialNum, modelName,
                                              color, maxPrice, minPrice, isAvailable));
        if (processorType != null) {
            result = result.and(hasProcessorType(processorType));
        }
        if (category != null) {
            result = result.and(hasCategory(category));
        }
        return result;
    }

}
