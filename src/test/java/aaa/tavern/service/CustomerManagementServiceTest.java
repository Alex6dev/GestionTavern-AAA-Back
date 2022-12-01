package aaa.tavern.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.RecipeRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Place;
import aaa.tavern.entity.Player;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.entity.SubCategory;
import aaa.tavern.entity.TableRest;
import aaa.tavern.exception.ForbiddenException;

//whenassertThrow 
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
@WithMockUser(username = "userName", authorities = "USER")
public class CustomerManagementServiceTest {
    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private TableRestRepository tableRestRepository;

    @MockBean
    private CustomerManagementService customerManagementService;

    @MockBean
    private RecipeRepository recipeRepository;

    @MockBean
    private ManagerRepository managerRepository;

    @MockBean
    private Customer customerMock;

    @Test
    public void modifedTableRestWithAssignNewTable() throws ForbiddenException, Exception {
        Customer customer = new Customer();
        Optional<Customer> optCustomer = Optional.of(customer);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);

        Place place = new Place();
        place.setPlaceId(1);
        TableRest tableRest = new TableRest();
        tableRest.setNumberPlace(4);
        tableRest.setPlace(place);
        Optional<TableRest> optTableRest = Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        Player player = new Player();
        player.setEmail("userName");
        Manager manager = new Manager();
        manager.setPlayer(player);
        Optional<Manager> optManager = Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        Recipe recipe = new Recipe();
        Mockito.when(customerManagementService.getRecipe(manager)).thenReturn(recipe);

        customerManagementService.assignNewTable(1, 1, 1);

        verify(tableRestRepository)
                .save(argThat(tableRest2 -> tableRest2.getNumberPlace() == 4));
    }

    @Test
    public void modifedCustomerTableIdWithAssignNewTable() throws ForbiddenException, Exception {
        Player player = new Player();
        player.setEmail("userName");
        Manager manager = new Manager();
        manager.setPlayer(player);
        Optional<Manager> optManager = Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        Customer customer = new Customer();
        Optional<Customer> optCustomer = Optional.of(customer);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);

        Place place = new Place();
        place.setPlaceId(1);
        TableRest tableRest = new TableRest();
        tableRest.setNumberPlace(5);
        tableRest.setIdTable(1);
        tableRest.setPlace(place);
        Optional<TableRest> optTableRest = Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        Recipe recipe = new Recipe();
        Mockito.when(customerManagementService.getRecipe(manager)).thenReturn(recipe);

        customerManagementService.assignNewTable(1, 1, 1);

        Mockito.verify(customerRepository)
                .save(ArgumentMatchers.argThat(customer2 -> customer2.getTableRest().getIdTable() == 1));
    }

    @Test
    public void returnEntityNotFoundExceptionInCustomerWithAssignNewTable() {
        Player player = new Player();
        player.setEmail("userName");
        Manager manager = new Manager();
        manager.setPlayer(player);
        Optional<Manager> optManager = Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        Optional<Customer> optCustomer = Optional.empty();
        Mockito.when(customerRepository.findById(1)).thenReturn(optCustomer);

        TableRest tableRest = new TableRest();
        tableRest.setNumberPlace(5);
        Optional<TableRest> optTableRest = Optional.of(tableRest);
        Mockito.when(tableRestRepository.findById(1)).thenReturn(optTableRest);

        assertThrows(EntityNotFoundException.class, () -> customerManagementService.assignNewTable(1, 1, 1));
    }

    @Test
    public void verifyReturnNewRecipeRandom() {

        Manager manager = new Manager();
        manager.setIdManager(1);
        manager.setLevel(1);
        Optional<Manager> optManager = Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        SubCategory subCategory = new SubCategory();
        subCategory.setIdSubCategory(1);

        List<Recipe> listTest = new ArrayList<Recipe>();
        ArrayList<RecipeIngredient> tabIngredientsForRecipe = new ArrayList<RecipeIngredient>();

        Recipe recipe1 = new Recipe("recipe1", Integer.valueOf(1), Integer.valueOf(1), 1L, Long.valueOf(1l),
                new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe1.setId(1);
        listTest.add(recipe1);

        Recipe recipe2 = new Recipe("recipe2", Integer.valueOf(1), Integer.valueOf(1), 1L, Long.valueOf(1l),
                new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe2.setId(2);
        recipe2.setName("recipe2");
        listTest.add(recipe2);

        Recipe recipe3 = new Recipe("recipe3", Integer.valueOf(1), Integer.valueOf(1), 1l, Long.valueOf(1l),
                new Date(1l), Integer.valueOf(1), subCategory, tabIngredientsForRecipe);
        recipe3.setId(3);
        recipe3.setName("recipe3");
        listTest.add(recipe3);

        Mockito.when(recipeRepository.findByLevelLessThanEqual(1)).thenReturn(listTest);
        Recipe recipe = customerManagementService.getRecipe(manager);
        assertTrue(listTest.contains(recipe));
    }

    @Test
    public void givenCustomerServed_WhenCustomerEat() {
        Manager manager = new Manager();
        manager.setIdManager(1);
        manager.setLevel(1);
        Optional<Manager> optManager = Optional.of(manager);
        Mockito.when(managerRepository.findById(1)).thenReturn(optManager);

        TableRest tableRest = new TableRest();
        tableRest.setIdTable(1);
        Optional<Customer> optCutomer = Optional.of(customerMock);
        Mockito.when(customerRepository.findById(1)).thenReturn(optCutomer);
        Mockito.when(customerMock.getTableRest()).thenReturn(tableRest);
        customerManagementService.customerServed(1);

        Mockito.verify(customerMock).setConsommationStart(Mockito.any());

        Mockito.verify(customerRepository).save(customerMock);
    }

}
