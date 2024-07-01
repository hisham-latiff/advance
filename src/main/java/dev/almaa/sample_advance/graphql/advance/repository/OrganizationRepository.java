package dev.almaa.sample_advance.graphql.advance.repository;

import dev.almaa.sample_advance.graphql.advance.model.Organization;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface OrganizationRepository extends CrudRepository<Organization, Integer>, JpaSpecificationExecutor<Organization> {
}
