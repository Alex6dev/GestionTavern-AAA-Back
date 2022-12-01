package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class InventoryIngredientRepositoryTest {

	@Autowired
	InventoryIngredientRepository inventoryIngredientRepository;

	@Autowired
	ManagerRepository managerRepository;

	@Test
	@Sql("givenIngredientAndManager_findByManager_thenReturnInventoryIngredient.sql")
	public void givenIngredientAndManager_findByManager_thenReturnInventoryIngredient() {
		Manager manager = new Manager();
		Optional<Manager> managerOpt = managerRepository.findById(1);
		manager = managerOpt.get();
		List<InventoryIngredient> inventoryIngredients = inventoryIngredientRepository.findByManagerAndQuantityGreaterThan(manager, 0);;

		assertEquals(inventoryIngredients.size(), 3);
	}

	@Test
	@Sql("givenIngredientAndManager_findByManager_thenReturnAnEmptyListInventoryIngredient.sql")
	public void givenIngredientAndManager_findByManager_thenReturnAnEmptyListInventoryIngredient() {
		Manager manager = new Manager();
		Optional<Manager> managerOpt = managerRepository.findById(3);
		manager = managerOpt.get();
		List<InventoryIngredient> inventoryIngredients = inventoryIngredientRepository.findByManagerAndQuantityGreaterThan(manager, 0);

		assertTrue(inventoryIngredients.isEmpty());
	}
}
