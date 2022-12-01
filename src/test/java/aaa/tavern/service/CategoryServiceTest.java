package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.CategoryRepository;
import aaa.tavern.dto.CategoryDto;
import aaa.tavern.entity.Category;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class CategoryServiceTest {

	@MockBean
	private CategoryRepository categoryRepository;

	@Autowired
	private CategoryService categoryService;

	@Test
	public void givenCategories_whenFindAllCategories_thenReturnListCategoriesDto() {
		List<Category> listCategory = new ArrayList<Category>();
		for (int i = 0; i < 5; i++) {
			Category category = new Category();
			category.setIdCategory(i+1);
			category.setName("Category n° " + (i+1));
			listCategory.add(category);
		}
		
		Iterable<Category> iterableCategories = new Iterable<Category>() {			
			@Override
			public Iterator<Category> iterator() {
				
				return listCategory.iterator();
			}
		};
		
		Mockito.when(categoryRepository.findAll()).thenReturn(iterableCategories);
		
		List<CategoryDto> listCategoryDto = new ArrayList<CategoryDto>();
		for (Category category : iterableCategories) {
			CategoryDto categoryDto = new CategoryDto(category);
			listCategoryDto.add(categoryDto);
		}
		
		List<CategoryDto> listCategoryDto2 = categoryService.loadAllCategory();
		
		assertEquals(listCategoryDto, listCategoryDto2);
	}
}
