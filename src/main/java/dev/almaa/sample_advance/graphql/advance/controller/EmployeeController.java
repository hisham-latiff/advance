package dev.almaa.sample_advance.graphql.advance.controller;

import dev.almaa.sample_advance.graphql.advance.filter.EmployeeFilter;
import dev.almaa.sample_advance.graphql.advance.filter.FilterField;
import dev.almaa.sample_advance.graphql.advance.model.Department;
import dev.almaa.sample_advance.graphql.advance.model.Employee;
import dev.almaa.sample_advance.graphql.advance.model.EmployeeInput;
import dev.almaa.sample_advance.graphql.advance.model.Organization;
import dev.almaa.sample_advance.graphql.advance.repository.DepartmentRepository;
import dev.almaa.sample_advance.graphql.advance.repository.EmployeeRepository;
import dev.almaa.sample_advance.graphql.advance.repository.OrganizationRepository;
import org.springframework.boot.autoconfigure.rsocket.RSocketProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class EmployeeController {

    DepartmentRepository departmentRepository;
    EmployeeRepository employeeRepository;
    OrganizationRepository organizationRepository;

    EmployeeController(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository, OrganizationRepository organizationRepository) {
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
        this.organizationRepository = organizationRepository;
    }

    @QueryMapping
    public Iterable<Employee> employees() {
        return employeeRepository.findAll();
    }

    @QueryMapping
    public Employee employee(@Argument Integer id) {
        return employeeRepository.findById(id).orElseThrow();
    }

    @MutationMapping
    public Employee newEmployee(@Argument EmployeeInput employee){
        Department department = departmentRepository.findById(employee.getDepartmentId()).get();
        Organization organization = organizationRepository.findById(employee.getOrganizationId()).get();
        return employeeRepository.save(new Employee(null, employee.getFirstName(), employee.getLastName(), employee.getPosition(), employee.getAge(), employee.getSalary(),department,organization));
    }

    @QueryMapping
    public Iterable<Employee> employeesWithFilter(@Argument EmployeeFilter filter) {
        Specification<Employee> spec = null;
        if(filter.getSalary() != null)
            spec = bySalary(filter.getSalary());
        if(filter.getAge() !=null)
            spec = (spec == null ? byAge(filter.getAge()): spec.and(byAge(filter.getAge())));
        if(filter.getPosition() != null)
            spec = (spec == null ? byPosition(filter.getPosition()): spec.and(byPosition(filter.getPosition())));

        if(spec != null)
            return employeeRepository.findAll(spec);
        else
            return employeeRepository.findAll();
    }

    private Specification<Employee> bySalary (FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder,root.get("salary"), null);
    }

    private Specification<Employee> byAge(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("age"), null);

    }

    private Specification<Employee> byPosition(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, null, root.get("position"));
    }

}
