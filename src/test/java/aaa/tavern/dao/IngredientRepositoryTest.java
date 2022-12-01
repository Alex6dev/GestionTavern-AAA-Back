package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Ingredient;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class IngredientRepositoryTest {

    @Autowired
    IngredientRepository ingredientRepository;

    @Test
    @Sql("giventIngredients_findByLessThanEqualsLevel_ThenReturnIngredients.sql")
    public void giventIngredients_findByLessThanEqualsLevel_ThenReturnIngredients() {
        List<Ingredient> ingredients = ingredientRepository.findByLevelLessThanEqual(4);

        assertEquals(ingredients.size(), 4);
    }
}
