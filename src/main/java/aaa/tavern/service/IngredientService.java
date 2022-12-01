package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dto.IngredientDto;
import aaa.tavern.entity.Ingredient;

@Service
public class IngredientService {

	@Autowired
	private IngredientRepository ingredientRepository;

	/**
	 * Load Ingredients by manager's level
	 * 
	 * @param managerLevel
	 * @return List<IngredientDto>
	 */
	public List<IngredientDto> loadIngredientsByManagerLevel(int managerLevel) {
		List<Ingredient> listIngredients = ingredientRepository.findByLevelLessThanEqual(managerLevel);

		if (listIngredients.isEmpty()) {
			throw new EntityNotFoundException();
		}

		List<IngredientDto> listIngredientDto = new ArrayList<IngredientDto>();
		for (Ingredient ingredient : listIngredients) {
			IngredientDto ingredientDto = new IngredientDto(ingredient);
			listIngredientDto.add(ingredientDto);
		}

		return listIngredientDto;
	}
}
