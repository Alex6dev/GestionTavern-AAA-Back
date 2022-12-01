package aaa.tavern.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.RecipeCustomerRepository;
import aaa.tavern.dao.RecipeRepository;
import aaa.tavern.dto.RecipeCustomerInventoryIngredientDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeCustomer;
import aaa.tavern.entity.RecipeIngredient;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private RecipeCustomerRepository recipeCustomerRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     * High-level methods to create the recipe you want to launch
     * 
     * @param idManager  id of the manager who wants to make the recipe
     * @param idRecipe   id of the recipe we want to make
     * @param idCustomer id of the customer for whom we want to make the recipe
     * @throws Exception
     */
    @Transactional(rollbackOn = { EntityNotFoundException.class, ForbiddenException.class })
    public RecipeCustomerInventoryIngredientDto prepareRecipe(int idManager, int idRecipe, int idCustomer)
            throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Customer customer = ServiceUtil.getEntity(customerRepository, idCustomer);
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {

            Optional<Recipe> optRecipe = recipeRepository.findByIdAndLevelLessThanEqual(idRecipe, manager.getLevel());

            if (optRecipe.isEmpty())
                throw new ForbiddenException();

            Recipe recipe = optRecipe.get();

            return requestRecipe(manager, recipe, customer);
        } else {
            throw new Exception("Le manager ne correspond pas a votre compte");
        }
    }

    /**
     * We check if the manager has the resources to make the recipe
     * 
     * @param manager manager who wants to make the recipe
     * @param recipe  recipe we want to make
     * @return returns (Y/N) if we have the necessary resources
     */
    private boolean haveQuantityIngredientInInventaire(Manager manager, Recipe recipe) {
        for (RecipeIngredient recipeIngredient : recipe.getTabIngredientsForRecipe()) {
            Ingredient ingredient = recipeIngredient.getIngredient();
            Integer quantity = recipeIngredient.getQuantity();
            Map<Ingredient, Integer> inventaireManager = manager.getIngredientQuantity();
            if (!inventaireManager.containsKey(ingredient) || inventaireManager.get(ingredient) < quantity)
                return false;
        }
        return true;
    }

    /**
     * remove the resources from the inventory of the manager then create a
     * RecipeCustomer and finally save
     * 
     * @param manager  manager who wants to make the recipe
     * @param recipe   recipe we want to make
     * @param customer customer for whom we want to make the recipe
     * @throws ForbiddenException the manager does not have enough ingredients to
     *                            make the recipe
     */
    private RecipeCustomerInventoryIngredientDto requestRecipe(Manager manager, Recipe recipe, Customer customer)
            throws ForbiddenException {
        RecipeCustomer recipeCustomer = null ;

        if (haveQuantityIngredientInInventaire(manager, recipe)) {
            for (RecipeIngredient recipeIngredient : recipe.getTabIngredientsForRecipe()) {

                Ingredient ingredient = recipeIngredient.getIngredient();
                int quantity = recipeIngredient.getQuantity()-manager.getIngredientQuantity().get(ingredient) ;

                recipeIngredient.setQuantity(quantity);

                manager.getIngredientQuantity().put(ingredient,quantity);
            }
            //RecipeCustomer recipeCustomer = new RecipeCustomer(recipe, customer, null);
            //recipeCustomerRepository.save(recipeCustomer);
            List<RecipeCustomer> recipeCustomers = recipeCustomerRepository.findByCustomer(customer) ;
            for (RecipeCustomer recipeCustomerElement : recipeCustomers) {
                if(recipeCustomerElement.getRecipe() == recipe){
                    recipeCustomerElement.setRecipeStart(new Timestamp(System.currentTimeMillis()));
                    recipeCustomer = recipeCustomerElement ;
                    break ;
                }
            }
            recipeCustomerRepository.save(recipeCustomer);
            managerRepository.save(manager);

            /*
            for (InventoryIngredient inventoryIngredient : manager.getInventoryIngredient())
                inventoryIngredientRepository.save(inventoryIngredient);
            */

            return new RecipeCustomerInventoryIngredientDto(recipeCustomer, manager.getInventoryIngredient());

        } else {
            throw new ForbiddenException();
        }
    }

    public List<RecipeDto> loadRecipeByLessOrEqualLevel(int managerLevel) {
        List<Recipe> listRecipe = recipeRepository.findByLevelLessThanEqual(managerLevel);

        if (listRecipe.isEmpty()) {
            throw new EntityNotFoundException();
        }

        List<RecipeDto> listRecipeDto = new ArrayList<RecipeDto>();
        for (Recipe recipe : listRecipe) {
            RecipeDto recipeDto = new RecipeDto(recipe);
            listRecipeDto.add(recipeDto);
        }

        return listRecipeDto;
    }
}
