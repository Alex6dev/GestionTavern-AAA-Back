package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;

@Repository
public interface InventoryIngredientRepository extends CrudRepository<InventoryIngredient, Integer> {
    List<InventoryIngredient> findByManagerAndQuantityGreaterThan(Manager manager, int quantity);

}
