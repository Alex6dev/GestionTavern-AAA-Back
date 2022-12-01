package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.RecipeRepository;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.entity.Category;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Player;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.entity.SubCategory;
import aaa.tavern.entity.TableRest;
import aaa.tavern.exception.ForbiddenException;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@WithMockUser(username = "userName", authorities =  "USER" )
public class RecipeServiceTest {

    @Autowired
    private RecipeService recipeService;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private ManagerRepository managerRepository;

    @MockBean
    private CustomerRepository customerRepository;

    @Test
    public void requestRecipeSuccessful() throws EntityNotFoundException,ForbiddenException,Exception{
        Player player=new Player();
        player.setEmail("userName");
        Manager manager = new Manager();
        manager.setPlayer(player);
        manager.setLevel(1);


        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Category category= new Category(1, "category");
        SubCategory subCategory=new SubCategory(1, "subCategory", category);
        Recipe recipe= new Recipe("test", 1, 1, 1L, 1l, new Date(1l), 1, subCategory, new ArrayList<RecipeIngredient>());
        Ingredient ingredient1=new Ingredient(1, "test1", 1, 1, subCategory);
        Ingredient ingredient2=new Ingredient(2, "test2", 1, 1, subCategory);
        Ingredient ingredient3=new Ingredient(3, "test3", 1, 1, subCategory);
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findByIdAndLevelLessThanEqual(1,1)).thenReturn(optRecipe);
        
        //init manager
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 5);
        manager.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        TableRest tableRest=new TableRest();
        Customer customer= new Customer(1, 1F, 1F, 1F, 1F, 1F, 1F, new Time(1l), 1F, 1F, true, 1, tableRest, new HashSet<RecipeCustomer>(), new Timestamp(1L));
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);

        recipeService.prepareRecipe(1,1,1);
        
        assertEquals(manager.getIngredientQuantity().get(ingredient3), 1);
    }

    @Test
    public void requestRecipeRecipeNotFound(){
        //init recipe
        
        Optional<Recipe> optRecipe= Optional.empty();
        Mockito.when(recipeRepository.findByIdAndLevelLessThanEqual(1,1)).thenReturn(optRecipe);
        
        //init manager        
        Player player=new Player();
        player.setEmail("userName");
        Manager manager = new Manager();
        manager.setPlayer(player);
        manager.setLevel(1);
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 2);
        manager.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        Customer customer= new Customer(null, null, null, null, null, null, null, null, null, null, null);
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(ForbiddenException.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }

    @Test
    public void requestRecipeCustomerNotFound(){
        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Recipe recipe= new Recipe();
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1)).thenReturn(optRecipe);
        
        //init manager
        Player player=new Player();
        player.setEmail("userName");
        Manager manager = new Manager();
        manager.setPlayer(player);
        manager.setLevel(1);
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 2);
        manager.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        Optional<Customer> optCustomer= Optional.empty();        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(EntityNotFoundException.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }

    @Test
    public void requestRecipeManagerNotFound(){
        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Recipe recipe= new Recipe();
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1)).thenReturn(optRecipe);
        
        //init manager
        Optional<Manager> optManager= Optional.empty();
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

         //init customer
        Customer customer= new Customer(null, null, null, null, null, null, null, null, null, null, null);
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(Exception.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }

    @Test
    public void requestRecipeFailure(){
        //init recipe
        List<RecipeIngredient> tabIngredients= new ArrayList<RecipeIngredient>();
        Recipe recipe= new Recipe();
        Ingredient ingredient1=new Ingredient(1,"test1");
        Ingredient ingredient2=new Ingredient(2,"test2");
        Ingredient ingredient3=new Ingredient(3,"test3");
        RecipeIngredient recipeIngredient1= new RecipeIngredient(recipe,ingredient1,2);
        RecipeIngredient recipeIngredient2= new RecipeIngredient(recipe,ingredient2,3);
        RecipeIngredient recipeIngredient3= new RecipeIngredient(recipe,ingredient3,4);
        tabIngredients.add(recipeIngredient1);
        tabIngredients.add(recipeIngredient2);
        tabIngredients.add(recipeIngredient3);

        recipe.setTabIngredientsForRecipe(tabIngredients);
        Optional<Recipe> optRecipe= Optional.of(recipe);
        Mockito.when(recipeRepository.findById(1)).thenReturn(optRecipe);
        
        //init manager
        Player player=new Player();
        player.setEmail("userName");
        Manager manager = new Manager();
        manager.setPlayer(player);
        manager.setLevel(1);        
        Map<Ingredient,Integer> ingredientQuantity=new HashMap<Ingredient,Integer>();
        ingredientQuantity.put(ingredient1, 3);
        ingredientQuantity.put(ingredient2, 3);
        ingredientQuantity.put(ingredient3, 2);
        manager.setIngredientQuantity(ingredientQuantity);
        Optional<Manager> optManager= Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        //init customer
        Customer customer= new Customer(null, null, null, null, null, null, null, null, null, null, null);
        Optional<Customer> optCustomer= Optional.of(customer);        
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);
        
        assertThrows(ForbiddenException.class, ()->recipeService.prepareRecipe(1, 1, 1));
    }

    @Test
    public void givenListRecipe_whenFindByLevelManager_thenReturnListRecipeDto() {
		Manager manager = new Manager();
		manager.setLevel(5);

		SubCategory subCategory = new SubCategory();
		subCategory.setIdSubCategory(1);
		
		List<Recipe> listRecipe = new ArrayList<Recipe>();
		for (int i = 0; i < 5; i++) {
			Recipe recipe = new Recipe("test", 10, i+1, 1L, 1L, new Date(1l), 10, subCategory, new ArrayList<RecipeIngredient>());
			listRecipe.add(recipe);
		}
		
		Mockito.when(recipeRepository.findByLevelLessThanEqual(manager.getLevel())).thenReturn(listRecipe);
		
		List<RecipeDto> listRecipeDto = new ArrayList<RecipeDto>();
		for (Recipe recipe : listRecipe) {
			RecipeDto recipeDto = new RecipeDto(recipe);
			
			listRecipeDto.add(recipeDto);
		}
		
		List<RecipeDto> listRecipeDto2 = recipeService.loadRecipeByLessOrEqualLevel(manager.getLevel());
		
		assertEquals(listRecipeDto, listRecipeDto2);
	}
}
