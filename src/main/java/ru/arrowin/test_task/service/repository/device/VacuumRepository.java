package ru.arrowin.test_task.service.repository.device;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.arrowin.test_task.model.devices.Vacuum;

@Repository
public interface VacuumRepository extends JpaRepository<Vacuum, Integer> {
}
