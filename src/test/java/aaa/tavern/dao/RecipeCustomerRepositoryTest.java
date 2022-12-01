package aaa.tavern.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import aaa.tavern.entity.Customer;
import aaa.tavern.entity.RecipeCustomer;

@DataJpaTest
@TestPropertySource(locations = "classpath:test.properties")
public class RecipeCustomerRepositoryTest {

    @Autowired
    RecipeCustomerRepository recipeCustomerRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Sql("given5RecipeCustomerAnd2Customer_whenFindByCustomer_ThenReturnListOf3RecipeCustomer.sql")
    public void given5RecipeCustomer_whenFindByCustomerWith3Recipe_ThenReturnListOf3RecipeCustomer() {
        Customer customer = customerRepository.findById(1).get();
        List<RecipeCustomer> listRecipeCustomers = recipeCustomerRepository.findByCustomer(customer);
        assertEquals(listRecipeCustomers.size(), 3);
    }

    @Test
    @Sql("given5RecipeCustomerAnd2Customer_whenFindByCustomerWithoutRecipe_ThenReturnAEmptyListRecipeCustomer.sql")
    public void given5RecipeCustomerAnd2Customer_whenFindByCustomerWithoutRecipe_ThenReturnAEmptyListRecipeCustomer() {
        Customer customer = customerRepository.findById(2).get();
        List<RecipeCustomer> listRecipeCustomers = recipeCustomerRepository.findByCustomer(customer);
        assertTrue(listRecipeCustomers.isEmpty());
    }
}
