package aaa.tavern.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.entity.RecipeIngredientKey;


@Repository
public interface RecipeIngredientRepository extends CrudRepository<RecipeIngredient, RecipeIngredientKey> {
    List<RecipeIngredient> findByRecipe(Recipe Recipe); 
    
}
