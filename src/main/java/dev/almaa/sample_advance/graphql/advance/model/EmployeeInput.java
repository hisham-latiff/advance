package dev.almaa.sample_advance.graphql.advance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Employee Input Class that replicate the Employee model for input representation for graphql mutation mapping
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInput {
    private String firstName;
    private String lastName;
    private String position;
    private int salary;
    private int age;
    private Integer departmentId;
    private Integer organizationId;
}
