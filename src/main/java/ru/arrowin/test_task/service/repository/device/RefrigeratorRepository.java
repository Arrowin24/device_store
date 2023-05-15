package ru.arrowin.test_task.service.repository.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.devices.Refrigerator;

@Repository
public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Integer> {
}
