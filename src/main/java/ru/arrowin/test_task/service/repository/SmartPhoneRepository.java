package ru.arrowin.test_task.service.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.SmartPhoneModel;

public interface SmartPhoneRepository
        extends JpaRepository<SmartPhoneModel, String>, JpaSpecificationExecutor<SmartPhoneModel>,
        ModelSpecification<SmartPhoneModel>
{
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
        result.and(
                combinedModelSpecification(ModelType.SMARTPHONE.getModelTypeName(), deviceName, country, manufacturer,
                                           isOnlineOrderAvailable, isInstallmentAvailable, serialNum, modelName, color,
                                           maxPrice, minPrice, isAvailable));
        if (memory != 0) {
            result = result.and(hasMemory(memory));
        }
        if (cameraNums != 0) {
            result = result.and(hasCameraNums(cameraNums));
        }
        return result;
    }
}
