package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.RecipeCustomer;

@Repository
public interface RecipeCustomerRepository extends CrudRepository<RecipeCustomer, Integer> {
	List<RecipeCustomer> findByCustomer(Customer customer);
}
