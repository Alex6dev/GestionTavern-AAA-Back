package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.InventoryIngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.IngredientDto;
import aaa.tavern.dto.InventoryManagerIngredientDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.received.ShopIngredientDto;
import aaa.tavern.dto.received.ShopIngredientQuantityDto;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ShopService {
    @Autowired
    ManagerRepository managerRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    InventoryIngredientRepository inventoryIngredientRepository;

    /**
     * returns the list ingredients of game according to the Manager level
     * /!\ Use the same method of ManagerService instead /!\
     * 
     * @param idManager
     * @return List of Ingredients according to level
     * @throws Exception
     */
    // Liste l'inventaire en fonction du niveau du manager
    public List<IngredientDto> getAllIngredients(int idManager) throws Exception {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {
            List<Ingredient> listEntityIngredients = ingredientRepository.findByLevelLessThanEqual(manager.getLevel());
            List<IngredientDto> listIngredientsDto = new ArrayList<IngredientDto>();

            for (Ingredient ingredientElement : listEntityIngredients) {
                listIngredientsDto.add(new IngredientDto(ingredientElement));
            }

            return listIngredientsDto;
        } else {
            throw new Exception("Ce manager ne correspond pas a votre compte");
        }
    }

    /**
     * Loads manager and ingredient to purchase and add to inventory
     * 
     * @param idManager
     * @param idIngredient
     * @return ManagerDto with manager update
     * @throws Exception
     */
    // charge le manager et l'ingredient pour acheter et ajouter ce dernier dans
    // l'inventaire
    public List<InventoryManagerIngredientDto> prepareIngredientAndBuy(ShopIngredientDto shopIngredientDto)
            throws Exception {
        Manager manager = ServiceUtil.getEntity(managerRepository, shopIngredientDto.getIdManager());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {
            ShopIngredientQuantityDto[] shopIngredientQuantity = shopIngredientDto.getShopIngredientQuantity();

            for (int i = 0; i < shopIngredientDto.getShopIngredientQuantity().length; i++) {
                Ingredient ingredient = ServiceUtil.getEntity(ingredientRepository,
                        shopIngredientQuantity[i].getIdIngredient());
                if (ingredient.getLevel() > manager.getLevel())
                    throw new ForbiddenException();

                if (!Buy(ingredient, manager, shopIngredientQuantity[i].getQuantity()))
                    throw new ForbiddenException();

                Add(ingredient, manager, shopIngredientQuantity[i].getQuantity());
            }
            managerRepository.save(manager);
            ManagerDto managerDto = new ManagerDto(manager);
            return managerDto.getIngredientQuantity();
        } else {
            throw new Exception("Ce manager ne correspond pas a votre compte");
        }
    }

    /**
     * Load the manager and the ingredient sell and remove the latter from the
     * inventory
     * 
     * @param idManager
     * @param idIngredient
     * @return ManagerDto with manager update
     * @throws Exception
     */
    // Charge le manager et l'ingredient vendre et retirer ce dernier de
    // l'inventaire
    public List<InventoryManagerIngredientDto> prepareIngredientAndSell(ShopIngredientDto shopIngredientDto)
            throws Exception {
        Manager manager = ServiceUtil.getEntity(managerRepository, shopIngredientDto.getIdManager());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {

            ShopIngredientQuantityDto[] shopIngredientQuantity = shopIngredientDto.getShopIngredientQuantity();

            for (int i = 0; i < shopIngredientDto.getShopIngredientQuantity().length; i++) {
                Ingredient ingredient = ServiceUtil.getEntity(ingredientRepository,
                        shopIngredientQuantity[i].getIdIngredient());

                if (ingredient.getLevel() > manager.getLevel())
                    throw new ForbiddenException();

                Sell(ingredient, manager, shopIngredientQuantity[i].getQuantity());

                if (!Remove(ingredient, manager, shopIngredientQuantity[i].getQuantity()))
                    throw new ForbiddenException();
            }

            managerRepository.save(manager);
            ManagerDto managerDto = new ManagerDto(manager);
            return managerDto.getIngredientQuantity();
        } else {
            throw new Exception("Ce manager ne correspond pas a votre compte");
        }
    }

    /**
     * Adds the ingredient to the manager’s inventory
     * 
     * @param ingredient
     * @param manager
     * @return void
     */
    // Ajoute l'ingredient dans l'inventaire du manager
    private void Add(Ingredient ingredient, Manager manager, int quantity) {
        Map<Ingredient, Integer> inventory = manager.getIngredientQuantity();
        Integer quantityInventory = inventory.get(ingredient);
        InventoryIngredient inventoryIngredient;

        if (quantityInventory == null) {
            quantityInventory = quantity;
            inventoryIngredient = new InventoryIngredient(manager, ingredient, quantityInventory);
            inventoryIngredientRepository.save(inventoryIngredient);
            manager.getInventoryIngredient().add(inventoryIngredient);
            inventory.put(ingredient, quantityInventory);
        } else {
            quantityInventory = quantityInventory + quantity;
            inventory.put(ingredient, quantityInventory);
        }
        manager.setIngredientQuantity(inventory);
    }

    /**
     * Deletes the ingredient in the manager’s inventory
     * 
     * @param ingredient
     * @param manager
     * @return returns false if the manager does not have the ingredient in its
     *         inventory
     */
    // Supprime l'ingredient dans l'inventaire du manager
    private boolean Remove(Ingredient ingredient, Manager manager, int quantity) {
        Map<Ingredient, Integer> inventory = manager.getIngredientQuantity();

        Integer quantityInventory = inventory.get(ingredient);

        if (quantityInventory != null && quantityInventory >= quantity) {
            /*
             * if(quantityInventory == quantity)
             * 
             * for (InventoryIngredient element : manager.getInventoryIngredient()) {
             * if(element.getManager() == manager && element.getIngredient().getId() ==
             * ingredient.getId()){
             * inventory.put(ingredient, quantityInventory)
             * //inventoryIngredientRepository.delete(element);
             * element.setQuantity(0);
             * break ;
             * }
             * }
             */
            quantityInventory = quantityInventory - quantity;
            inventory.put(ingredient, quantityInventory);

            manager.setIngredientQuantity(inventory);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Withdraws the money from the Manager
     * 
     * @param ingredient
     * @param manager
     * @return returns false if the manager does not have enough money to buy the
     *         ingredient
     */
    // Retire l'argent au Manager
    private boolean Buy(Ingredient ingredient, Manager manager, int quantity) {
        if (manager.getChest() >= ingredient.getBuyingPrice() * quantity) {
            manager.setChest(manager.getChest() - ingredient.getBuyingPrice() * quantity);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Adds money to the manager rounded to the top integer
     * 
     * @param ingredient
     * @param manager
     * @return void
     */
    // Ajoute l'argent au manager arrondi à l'entier supérieur
    private void Sell(Ingredient ingredient, Manager manager, int quantity) {
        manager.setChest(manager.getChest() + ((int) Math.ceil(ingredient.getBuyingPrice() / 2)) * quantity);
    }
}
