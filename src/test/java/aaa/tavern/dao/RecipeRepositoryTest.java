package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Recipe;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class RecipeRepositoryTest {
    @Autowired
    private RecipeRepository recipeRepository;

    @Test
    @Sql("givenRecipeLevel2_WhenFindWithLevelRecipe_ThenReturn2Recipe.sql")
    public void givenRecipeLevel2_WhenFindWithLevelRecipe_ThenReturn2Recipe() {
        List<Recipe> listRecipe2 = recipeRepository.findByLevelLessThanEqual(2);
        assertEquals(listRecipe2.size(), 3);

    }

    @Test
    @Sql("givenRecipeLevel2_WhenFindWithLevelRecipe_ThenNotReturnRecipe.sql")
    public void givenRecipeLevel2_WhenFindWithLevelRecipe_ThenNotReturnRecipe() {
        List<Recipe> listRecipe2 = recipeRepository.findByLevelLessThanEqual(2);
        assertEquals(listRecipe2.size(), 0);

    }

    @Test
    @Sql("givenRecipeLevel2AndById1_WhenFindWithLevelAndIdRecipe_ThenNotReturnRecipe.sql")
    public void givenRecipeLevel2AndById1_WhenFindWithLevelAndIdRecipe_ThenNotReturnRecipe() {
        Optional<Recipe> optRecipe2 = recipeRepository.findByIdAndLevelLessThanEqual(2, 1);
        assertFalse(optRecipe2.isPresent());

    }

    @Test
    @Sql("givenRecipeLevel2AndById1_WhenFindWithLevelAndIdRecipe_ThenReturnOneRecipe.sql")
    public void givenRecipeLevel2AndById1_WhenFindWithLevelAndIdRecipe_ThenReturnOneRecipe() {
        Optional<Recipe> optRecipe2 = recipeRepository.findByIdAndLevelLessThanEqual(2, 5);
        assertTrue(optRecipe2.isPresent());

    }
}
