package ru.arrowin.test_task.service.repository.device;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.devices.SmartPhone;

import javax.persistence.criteria.Expression;

@Repository
public interface SmartPhoneRepository extends JpaRepository<SmartPhone, Integer>, JpaSpecificationExecutor<SmartPhone> {

    default Specification<SmartPhone> hasDeviceName(String deviceName) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(deviceName));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("deviceName"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhone> hasCountry(String country) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(country));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("country"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhone> hasManufacturer(String manufacturer) {
        return (root, query, criteriaBuilder) -> {
            Expression<String> lowerCaseNameParam = criteriaBuilder.lower(criteriaBuilder.literal(manufacturer));
            Expression<String> lowerCaseNameField = criteriaBuilder.lower(root.get("manufacturer"));
            return criteriaBuilder.equal(lowerCaseNameField, lowerCaseNameParam);
        };
    }

    default Specification<SmartPhone> combinedSpecification(String deviceName, String country, String manufacturer) {
        Specification<SmartPhone> result = Specification.where(null);
        return result.and(hasDeviceName(deviceName)).and(hasCountry(country)).and(hasManufacturer(manufacturer));
    }
}
