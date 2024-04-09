package com.personal.dbtransaction.repository;

import com.personal.dbtransaction.model.CustomerEntity;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
