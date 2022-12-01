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

import aaa.tavern.dao.SubCategoryRepository;
import aaa.tavern.dto.SubCategoryDto;
import aaa.tavern.entity.Category;
import aaa.tavern.entity.SubCategory;

@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class SubCategoryServiceTest {

	@MockBean
	private SubCategoryRepository subCategoryRepository;

	@Autowired
	private SubCategoryService subCategoryService;

	@Test
	public void givenSubCategories_whenFindAllSubCategories_thenReturnListSubCategoriesDto() {
		
		Category category = new Category();
		category.setIdCategory(1);
		category.setName("Category");
		
		List<SubCategory> listSubCategories = new ArrayList<SubCategory>();
		for (int i = 0; i < 5; i++) {
			SubCategory subCategory = new SubCategory();
			subCategory.setCategory(category);
			subCategory.setIdSubCategory(i + 1);
			subCategory.setName("SubCategory n° " + (i + 1));
			listSubCategories.add(subCategory);
		}

		Iterable<SubCategory> iterableSubCategories = new Iterable<SubCategory>() {

			@Override
			public Iterator<SubCategory> iterator() {

				return listSubCategories.iterator();
			}
		};

		Mockito.when(subCategoryRepository.findAll()).thenReturn(iterableSubCategories);

		List<SubCategoryDto> listSubCategoryDto = new ArrayList<SubCategoryDto>();
		for (SubCategory subCategory : iterableSubCategories) {
			SubCategoryDto subCategoryDto = new SubCategoryDto(subCategory);
			listSubCategoryDto.add(subCategoryDto);
		}
		
		List<SubCategoryDto> listSubCategoryDto2 = subCategoryService.loadAllSubCategory();
		
		assertEquals(listSubCategoryDto, listSubCategoryDto2);
	}

}
