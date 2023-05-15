package ru.arrowin.test_task.service.repository;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.TVModel;

import javax.persistence.criteria.Expression;

@Repository
public interface TVRepository
        extends JpaRepository<TVModel, String>, JpaSpecificationExecutor<TVModel>, ModelSpecification<TVModel>
{
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
        result.and(combinedModelSpecification(ModelType.TV.getModelTypeName(), deviceName, country, manufacturer,
                                              isOnlineOrderAvailable, isInstallmentAvailable, serialNum, modelName,
                                              color, maxPrice, minPrice, isAvailable));
        if (technology != null) {
            result = result.and(hasTechnology(technology));
        }
        if (category != null) {
            result = result.and(hasCategory(category));
        }
        return result;
    }
}
