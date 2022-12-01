package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.InventoryIngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dto.CreateManagerDTO;
import aaa.tavern.dto.InventoryManagerIngredientDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;
import aaa.tavern.entity.SubCategory;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ManagerServiceTest {

	@MockBean
	private PlayerRepository playerRepository;

	@MockBean
	private ManagerRepository managerRepository;

	@MockBean
	private InventoryIngredientRepository inventoryIngredientRepository;

	@Autowired
	private ManagerService managerService;

	@Test
	public void whenCreateManager_thenSaveOnDatabase() {
		Manager manager = new Manager();
		manager.setName("Test");

		Player player = Mockito.mock(Player.class);

		Mockito.when(playerRepository.findById(0)).thenReturn(Optional.of(player));
		manager.setPlayer(player);
		CreateManagerDTO managerDto = new CreateManagerDTO(manager);
		managerService.createManager(managerDto);

		Mockito.verify(managerRepository).save(manager);

	}

	@Test
	public void deleteManager() throws Exception {
		Manager manager = Mockito.mock(Manager.class);
		Mockito.when(managerRepository.findById(0)).thenReturn(Optional.of(manager));
		managerService.deleteManager(0);

		Mockito.verify(managerRepository).delete(manager);
	}

	@Test
	public void listExistingManagerDto() throws Exception {
		Player player = Mockito.mock(Player.class);
		player.setEmail("test@test.com");
		Mockito.when(playerRepository.findByEmail(player.getEmail())).thenReturn(Optional.of(player));
		Manager manager1 = new Manager("Test1", 10, 20, 20, 20, player);
		Manager manager2 = new Manager("Test2", 10, 20, 20, 20, player);
		Manager manager3 = new Manager("Test3", 10, 20, 20, 20, player);
		List<Manager> managers = new ArrayList<Manager>();
		managers.add(manager1);
		managers.add(manager2);
		managers.add(manager3);
		Mockito.when(managerRepository.findByPlayer(player)).thenReturn(managers);

		List<ManagerDto> listManagerDtos = new ArrayList<ManagerDto>();
		ManagerDto managerDto1 = new ManagerDto(manager1);
		ManagerDto managerDto2 = new ManagerDto(manager2);
		ManagerDto managerDto3 = new ManagerDto(manager3);
		listManagerDtos.add(managerDto1);
		listManagerDtos.add(managerDto2);
		listManagerDtos.add(managerDto3);

		List<ManagerDto> listManagerDtos2 = managerService.listExistingManagerDto(player.getEmail());

		assertEquals(listManagerDtos, listManagerDtos2);
	}

	@Test
	public void givenInventoryIngredient_whenFindByManager_thenReturnListInventoryManagerIngredientDto()
			throws Exception {
		Manager manager = new Manager();
		SubCategory subCategory = new SubCategory();
		subCategory.setIdSubCategory(1);
		Mockito.when(managerRepository.findById(0)).thenReturn(Optional.of(manager));

		List<InventoryIngredient> listInventoryIngredient = new ArrayList<InventoryIngredient>();

		for (int i = 0; i < 5; i++) {
			Ingredient ingredient = new Ingredient(i + 1, "ingrédientTest " + (i + 1), i + 1, 20, subCategory);

			InventoryIngredient inventoryIngredient = new InventoryIngredient(manager, ingredient, 3);
			listInventoryIngredient.add(inventoryIngredient);
		}

		Mockito.when(inventoryIngredientRepository.findByManagerAndQuantityGreaterThan(manager, 0))
				.thenReturn(listInventoryIngredient);

		List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto = new ArrayList<InventoryManagerIngredientDto>();

		for (InventoryIngredient inventoryIngredient : listInventoryIngredient) {
			InventoryManagerIngredientDto inventoryManagerIngredientDto = new InventoryManagerIngredientDto(
					inventoryIngredient.getIngredient(), inventoryIngredient.getQuantity());
			listInventoryManagerIngredientDto.add(inventoryManagerIngredientDto);
		}

		List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto2 = managerService
				.loadInventoryIngredientsByManager(0);

		assertEquals(listInventoryManagerIngredientDto, listInventoryManagerIngredientDto2);
	}

}
