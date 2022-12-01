package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CategoryRepository;
import aaa.tavern.dto.CategoryDto;
import aaa.tavern.entity.Category;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	
	/**
	 * Loads all categories
	 * 
	 * @return List<CategoryDto>
	 */
	public List<CategoryDto> loadAllCategory() {
		Iterable<Category> iterableCategory = categoryRepository.findAll();
		
		if(!iterableCategory.iterator().hasNext()) {
			throw new EntityNotFoundException();
		}

		List<CategoryDto> listCategoryDto = new ArrayList<CategoryDto>();
		for (Category category : iterableCategory) {
			CategoryDto categoryDto = new CategoryDto(category);
			listCategoryDto.add(categoryDto);
		}
		return listCategoryDto;
	}
}
