package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.received.ShopIngredientDto;
import aaa.tavern.dto.received.ShopIngredientQuantityDto;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;
import aaa.tavern.exception.ForbiddenException;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class ShopServiceTest {

	@MockBean
	ManagerRepository managerRepository;

	@MockBean
	IngredientRepository ingredientRepository;

	@Autowired
	ShopService shopService;

	////// FONCTION D'ACHAT ////////

	@Test
	public void givenIngredientToBuy_WhenAllCondition_ThenSuccess() throws Exception {

		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setId(4);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);
		ingredient4.setBuyingPrice(40);
		ingredient4.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init player
		Player player = new Player();
		player.setIdPlayer(1);

		// init manager
		Manager manager = new Manager();
		manager.setChest(40);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);
		manager.setPlayer(player);
		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient4);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		shopService.prepareIngredientAndBuy(shopIngredientDto);

		assertEquals(manager.getIngredientQuantity().get(ingredient4), 1);
	}

	// Ingredient de trop haut niveau
	@Test
	public void givenIngredientToBuy_WhenTooHightLevel_ThenForbiddenException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setId(4);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);
		ingredient4.setBuyingPrice(40);
		ingredient4.setLevel(2);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient4);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(ForbiddenException.class, () -> shopService.prepareIngredientAndBuy(shopIngredientDto));
	}

	// Le Manager n'a pas assez d'argent
	@Test
	public void givenIngredientToBuy_WhenChestEmpty_ThenForbiddenException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setId(4);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);
		ingredient4.setBuyingPrice(40);
		ingredient4.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init manager
		Manager manager = new Manager();
		manager.setChest(39);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient4);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(ForbiddenException.class, () -> shopService.prepareIngredientAndBuy(shopIngredientDto));
	}

	// Le Manager selectioné n'existe pas
	@Test
	public void givenIngredientToBuy_WhenManagerEmpty_ThenEntityNotFoundException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setId(4);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);
		ingredient4.setBuyingPrice(40);
		ingredient4.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient4);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(EntityNotFoundException.class, () -> shopService.prepareIngredientAndBuy(shopIngredientDto));
	}

	// L'ingrédient selectioné n'existe pas
	@Test
	public void givenIngredientToBuy_WhenIngredientEmpty_ThenEntityNotFoundException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);
		Ingredient ingredient4 = new Ingredient();
		ingredient4.setId(4);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);
		ingredient4.setBuyingPrice(40);
		ingredient4.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient4);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(EntityNotFoundException.class, () -> shopService.prepareIngredientAndBuy(shopIngredientDto));
	}

	/////// FONCTION DE VENTE ///////

	@Test
	public void givenIngredientToSell_WhenAllCondition_ThenSuccess()
			throws Exception {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init player
		Player player = new Player();
		player.setIdPlayer(1);

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);
		manager.setPlayer(player);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient1);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		shopService.prepareIngredientAndSell(shopIngredientDto);

		assertEquals(manager.getIngredientQuantity().get(ingredient1), 2);
	}

	// Ingredient de trop haut niveau
	@Test
	public void givenIngredientToSell_WhenTooHightLevel_ThenForbiddenException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(2);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient1);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(ForbiddenException.class, () -> shopService.prepareIngredientAndSell(shopIngredientDto));
	}

	// L'ingrédient selectioné n'existe pas
	@Test
	public void givenIngredientToSell_WhenIngredientEmpty_ThenEntityNotFoundException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient1);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(EntityNotFoundException.class, () -> shopService.prepareIngredientAndSell(shopIngredientDto));
	}

	// Le Manager selectioné n'existe pas
	@Test
	public void givenIngredientToSell_WhenManagerEmpty_ThenEntityNotFoundException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);

		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();
		ingredientQuantity.put(ingredient1, 3);

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient1);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(EntityNotFoundException.class, () -> shopService.prepareIngredientAndSell(shopIngredientDto));
	}

	// Le Manager ne possède pas l'Ingredient
	@Test
	public void givenIngredientToSell_WhenManagerNotGetIngredient_ThenForbiddenException()
			throws EntityNotFoundException, ForbiddenException {
		ShopIngredientQuantityDto shopIngredientQuantity = new ShopIngredientQuantityDto(1, 1);
		ShopIngredientQuantityDto[] tabShopIngredientQuantity = new ShopIngredientQuantityDto[1];
		tabShopIngredientQuantity[0] = shopIngredientQuantity;

		ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1, tabShopIngredientQuantity);
		Ingredient ingredient1 = new Ingredient();
		ingredient1.setId(1);

		ingredient1.setBuyingPrice(10);
		ingredient1.setLevel(1);

		Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient, Integer>();

		// init manager
		Manager manager = new Manager();
		manager.setChest(200);
		manager.setLevel(1);
		manager.setIngredientQuantity(ingredientQuantity);

		// Mokito
		Optional<Manager> optManager = Optional.of(manager);
		Optional<Ingredient> optIngredient = Optional.of(ingredient1);
		Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
		Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

		assertThrows(ForbiddenException.class, () -> shopService.prepareIngredientAndSell(shopIngredientDto));
	}

	// Le Manager retire le dernier type d'un ingrédient de son inventaire
	// @Test
	// public void
	// givenIngredientToSell_WhenManagerGetOneIngredient_ThenInventoryEmpty()
	// throws EntityNotFoundException, ForbiddenException {
	// ShopIngredientQuantityDto shopIngredientQuantity = new
	// ShopIngredientQuantityDto(1, 1);
	// ShopIngredientQuantityDto[] tabShopIngredientQuantity = new
	// ShopIngredientQuantityDto[1];
	// tabShopIngredientQuantity[0] = shopIngredientQuantity;

	// ShopIngredientDto shopIngredientDto = new ShopIngredientDto(1,
	// tabShopIngredientQuantity);
	// Ingredient ingredient1 = new Ingredient();
	// ingredient1.setId(1);

	// ingredient1.setBuyingPrice(10);
	// ingredient1.setLevel(1);

	// Map<Ingredient, Integer> ingredientQuantity = new HashMap<Ingredient,
	// Integer>();
	// ingredientQuantity.put(ingredient1, 1);

	// // init player
	// Player player = new Player();
	// player.setIdPlayer(1);

	// // init manager
	// Manager manager = new Manager();
	// manager.setChest(200);
	// manager.setLevel(1);
	// manager.setIngredientQuantity(ingredientQuantity);
	// manager.setPlayer(player);

	// // Mokito
	// Optional<Manager> optManager = Optional.of(manager);
	// Optional<Ingredient> optIngredient = Optional.of(ingredient1);
	// Mockito.when(managerRepository.findById(1)).thenReturn(optManager);
	// Mockito.when(ingredientRepository.findById(1)).thenReturn(optIngredient);

	// shopService.prepareIngredientAndSell(shopIngredientDto);

	// assertEquals(ingredientQuantity.size(), 0);
	// }

	// /////// FONCTION POUR LISTER LES INGREDIENTS ///////

	// @Test
	// public void
	// givenOrderListIngredients_WhenThreeIngredients_ThenLinkedByLevel()
	// throws EntityNotFoundException, ForbiddenException {
	// SubCategory subCategory = new SubCategory();
	// subCategory.setIdSubCategory(1);

	// Ingredient ingredient1 = new Ingredient(1, "name1", 1, 10, subCategory);
	// Ingredient ingredient2 = new Ingredient(2, "name2", 1, 20, subCategory);
	// Ingredient ingredient3 = new Ingredient(3, "name3", 1, 30, subCategory);

	// Manager manager = new Manager();
	// manager.setLevel(1);
	// manager.setIdManager(1);

	// // Mokito
	// List<Ingredient> listIngredients = new ArrayList<Ingredient>();
	// listIngredients.add(ingredient1);
	// listIngredients.add(ingredient2);
	// listIngredients.add(ingredient3);
	// Optional<Manager> optManager = Optional.of(manager);
	// Mockito.when(ingredientRepository.findByLevelLessThanEqual(1)).thenReturn(listIngredients);
	// Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

	// List<IngredientDto> listIngredientsDto = shopService.getAllIngredients(1);

	// assertEquals(listIngredientsDto.size(), 3);
	// }
}
