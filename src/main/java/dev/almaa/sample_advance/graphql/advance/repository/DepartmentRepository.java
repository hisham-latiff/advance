package dev.almaa.sample_advance.graphql.advance.repository;

import dev.almaa.sample_advance.graphql.advance.model.Department;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department, Integer>, JpaSpecificationExecutor<Department> {
}
