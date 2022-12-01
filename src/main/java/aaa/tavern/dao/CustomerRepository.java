package aaa.tavern.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
    
    
}
