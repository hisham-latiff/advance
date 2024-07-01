package dev.almaa.sample_advance.graphql.advance.repository;

import dev.almaa.sample_advance.graphql.advance.model.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> , JpaSpecificationExecutor<Employee> {
}
