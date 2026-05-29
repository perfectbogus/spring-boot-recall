package dev.perfectbogus.productservice.repository;

import dev.perfectbogus.productservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    boolean existsEmployeeByName(String name);
}
