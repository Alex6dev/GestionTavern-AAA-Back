package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.CustomerRepository;
import aaa.tavern.dao.IngredientRepository;
import aaa.tavern.dao.InventoryIngredientRepository;
import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dao.PlaceRepository;
import aaa.tavern.dao.PlayerRepository;
import aaa.tavern.dao.TableRestRepository;
import aaa.tavern.dto.CreateManagerDTO;
import aaa.tavern.dto.InventoryManagerIngredientDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.entity.Customer;
import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.InventoryIngredient;
import aaa.tavern.entity.Manager;
import aaa.tavern.entity.Place;
import aaa.tavern.entity.Player;
import aaa.tavern.entity.TableRest;
import aaa.tavern.exception.ForbiddenException;
import aaa.tavern.utils.ServiceUtil;

@Service
public class ManagerService {

    @Autowired
    private InventoryIngredientRepository inventoryIngredientRepository;

    @Autowired
    private PlaceRepository placeRepository;

    @Autowired
    private TableRestRepository tableRestRepository;

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    public Manager createManager(CreateManagerDTO createManagerDto) {
        Player player = playerRepository.findByEmail(createManagerDto.getEmail()).get();
        Manager manager = new Manager(createManagerDto.getName(), 0, 100, 1, 0, player);
        Place place = new Place("Restaurant", 1, 1, manager);
        managerRepository.save(manager);
        placeRepository.save(place);
        TableRest table1 = new TableRest(4, 0f, 0f, 0f, place);
        TableRest table2 = new TableRest(4, 0f, 0f, 0f, place);
        tableRestRepository.save(table1);
        tableRestRepository.save(table2);
        return manager;
    }

    public void deleteManager(int idManager) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {

            managerRepository.delete(manager);
        } else {
            throw new Exception("L'email ne correspond pas a celui de votre compte");
        }

    }

    /**
     * Convert a Manager class instance into a ManagerDto class instance
     * 
     * @param manager
     * @return ManagerDto
     */
    public ManagerDto loadManagerDto(Manager manager) {
        return new ManagerDto(manager);
    }

    /**
     * Lists player's managers in the database and returns them in List of
     * managerDto
     * 
     * @param player
     * @return List<ManagerDto>
     * @throws Exception
     */
    public List<ManagerDto> listExistingManagerDto(String email) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Player player = playerRepository.findByEmail(email).get();
        if (currentPrincipalName.equals(player.getEmail())) {
            List<Manager> managers = managerRepository.findByPlayer(player);
            if (managers.isEmpty()) {
                throw new EntityNotFoundException();
            }
            List<ManagerDto> managersDto = new ArrayList<ManagerDto>();
            for (Manager manager : managers) {
                ManagerDto managerDto = loadManagerDto(manager);
                managersDto.add(managerDto);
            }

            return managersDto;
        } else {
            throw new Exception("L'email ne correspond pas a celui de votre compte");
        }
    }

    @Transactional(rollbackOn = { EntityNotFoundException.class, ForbiddenException.class })
    public ManagerDto selectManager(int idManager) throws Exception {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {
            ManagerDto managerDto = new ManagerDto(manager);
            return managerDto;

        } else {
            throw new Exception("Le manager ne correspond pas a votre compte");
        }
    }

    public void giveExperienceManagerWithRecipe(int idManager, int idIngredient) throws Exception {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {
            Ingredient ingredient = ServiceUtil.getEntity(ingredientRepository, idIngredient);
            if (ingredient == null || manager == null)
                throw new EntityNotFoundException();
        } else {
            throw new Exception("Le manager ne correspond pas a votre compte");
        }

    }

    public void giveExperienceManagerWithCustomer(int idManager, int idCustomer) throws Exception {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {

            Customer customer = ServiceUtil.getEntity(customerRepository, idCustomer);

            if (customer == null || manager == null)
                throw new EntityNotFoundException();
        } else {
            throw new Exception("Le manager ne correspond pas a votre compte");
        }

    }

    /**
     * Loads manager's Inventory
     * 
     * @param idManager
     * @return List<InventoryManagerIngredientDto>
     * @throws Exception
     */
    public List<InventoryManagerIngredientDto> loadInventoryIngredientsByManager(int idManager) throws Exception {
        Manager manager = ServiceUtil.getEntity(managerRepository, idManager);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {

            List<InventoryIngredient> listInventoryIngredients = inventoryIngredientRepository
                    .findByManagerAndQuantityGreaterThan(manager, 0);

            List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto = new ArrayList<InventoryManagerIngredientDto>();

            for (InventoryIngredient inventoryIngredient : listInventoryIngredients) {
                Ingredient ingredient = inventoryIngredient.getIngredient();
                InventoryManagerIngredientDto inventoryManagerIngredientDto = new InventoryManagerIngredientDto(
                        ingredient,
                        inventoryIngredient.getQuantity());
                listInventoryManagerIngredientDto.add(inventoryManagerIngredientDto);
            }

            return listInventoryManagerIngredientDto;
        } else {
            throw new Exception("Le manager ne correspond pas a votre compte");
        }
    }
}