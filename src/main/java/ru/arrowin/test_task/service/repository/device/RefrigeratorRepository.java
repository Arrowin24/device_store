package ru.arrowin.test_task.service.repository.device;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.devices.Refrigerator;

import javax.persistence.criteria.Expression;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Integer>, JpaSpecificationExecutor<Refrigerator> {
    default Specification<Refrigerator> hasDeviceName(String deviceName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<Refrigerator> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<Refrigerator> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<Refrigerator> combinedSpecification(String deviceName, String country, String manufacturer) {
        Specification<Refrigerator> result = Specification.where(null);
        return result.and(hasDeviceName(deviceName)).and(hasCountry(country)).and(hasManufacturer(manufacturer));
    }
}
