package aaa.tavern.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.RecipeCustomerRepository;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class RecipeCustomerServiceTest {

    @Autowired
    RecipeCustomerService recipeCustomerService;

    @MockBean
    RecipeCustomerRepository recipeCustomerRepository;

    @MockBean
    CustomerRepository customerRepository;

    // @Test
    // public void
    // givenCustomerAnd5Recipe_findRecipeByCustomer_thenReturnListRecipeDto() {
    // Customer customer = new Customer();
    // Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
    // SubCategory subCategory = new SubCategory();
    // subCategory.setIdSubCategory(1);
    // ArrayList<RecipeIngredient> tabIngredientsForRecipe = new
    // ArrayList<RecipeIngredient>();

    // List<RecipeCustomer> listRecipeCustomer = new ArrayList<RecipeCustomer>();

    // for (int i = 0; i < 5; i++) {
    // Recipe recipe = new Recipe("test", 1, 1, 1l, 1l, new Date(1l), 10,
    // subCategory, tabIngredientsForRecipe);
    // RecipeCustomer recipeCustomer = new RecipeCustomer(recipe, customer,null);
    // listRecipeCustomer.add(recipeCustomer);
    // }

    // Mockito.when(recipeCustomerRepository.findByCustomer(customer)).thenReturn(listRecipeCustomer);

    // List<RecipeDto> listRecipesDto = new ArrayList<RecipeDto>();
    // for (RecipeCustomer recipeCustomer : listRecipeCustomer) {
    // RecipeDto recipeDto = new RecipeDto(recipeCustomer.getRecipe());
    // listRecipesDto.add(recipeDto);
    // }

    // List<RecipeDto> listRecipesDto2 =
    // recipeCustomerService.loadRecipeByCustomer(1);

    // assertEquals(listRecipesDto, listRecipesDto2);
    // }
}
