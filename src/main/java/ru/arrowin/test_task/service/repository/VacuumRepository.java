package ru.arrowin.test_task.service.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.arrowin.test_task.model.models.ModelType;
import ru.arrowin.test_task.model.models.VacuumModel;



public interface VacuumRepository extends JpaRepository<VacuumModel, String>, JpaSpecificationExecutor<VacuumModel>,
        ModelSpecification<VacuumModel>
{
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
        result.and(combinedModelSpecification(ModelType.VACUUM.getModelTypeName(), deviceName, country, manufacturer,
                                              isOnlineOrderAvailable, isInstallmentAvailable, serialNum, modelName,
                                              color, maxPrice, minPrice, isAvailable));
        if (containerVolume > 0) {
            result = result.and(hasContainerVolume(containerVolume));
        }
        if (modesNum > 0) {
            result = result.and(hasModesNum(modesNum));
        }
        return result;
    }
}
