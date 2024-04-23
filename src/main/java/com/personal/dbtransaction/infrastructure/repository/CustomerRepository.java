package com.personal.dbtransaction.infrastructure.repository;

import com.personal.dbtransaction.domain.model.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
