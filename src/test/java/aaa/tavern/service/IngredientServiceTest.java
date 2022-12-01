package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dto.IngredientDto;
import aaa.tavern.entity.Category;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.SubCategory;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class IngredientServiceTest {

	@Autowired
	private IngredientService ingredientService;

	@MockBean
	private IngredientRepository ingredientRepository;

	@Test
	public void givenListIngredients_WhenfindByLevelLessThanEqual_ThenReturnListIngredientDto() {
		Category category = new Category();
		category.setIdCategory(1);
		SubCategory subCategory = new SubCategory();
		subCategory.setIdSubCategory(1);
		subCategory.setCategory(category);
		List<Ingredient> listIngredients = new ArrayList<Ingredient>();
		
		// Création de 6 ingrédients avec un level jusqu'au niveau 6
		for (int i = 0; i < 6; i++) {
			Ingredient ingredient = new Ingredient(i+1, null, i+1, 100, subCategory);
			listIngredients.add(ingredient);
		}
		
		Mockito.when(ingredientRepository.findByLevelLessThanEqual(6)).thenReturn(listIngredients);
		
		List<IngredientDto> listIngredientsDto = new ArrayList<IngredientDto>()	;	
		for (Ingredient ingredient : listIngredients) {
			IngredientDto ingredientDto = new IngredientDto(ingredient);
			listIngredientsDto.add(ingredientDto);
		}
		
		List<IngredientDto> listIngredientsDto2 = ingredientService.loadIngredientsByManagerLevel(6);
		
		assertEquals(listIngredientsDto, listIngredientsDto2);
	}
}
