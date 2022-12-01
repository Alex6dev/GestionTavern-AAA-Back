package aaa.tavern.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import aaa.tavern.entity.Recipe;


@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Integer> {
   List<Recipe> findByLevelLessThanEqual(Integer level); 
   Optional<Recipe> findByIdAndLevelLessThanEqual(Integer id ,Integer level); 
}
