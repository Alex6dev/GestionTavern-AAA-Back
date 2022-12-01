package aaa.tavern.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import aaa.tavern.dao.ManagerRepository;
import aaa.tavern.dto.CategoryDto;
import aaa.tavern.dto.CustomerDto;
import aaa.tavern.dto.IngredientDto;
import aaa.tavern.dto.InventoryManagerIngredientDto;
import aaa.tavern.dto.LoadManagerDto;
import aaa.tavern.dto.ManagerDto;
import aaa.tavern.dto.PlaceDto;
import aaa.tavern.dto.RecipeCustomerDto;
import aaa.tavern.dto.RecipeDto;
import aaa.tavern.dto.SubCategoryDto;
import aaa.tavern.dto.TableRestDto;
import aaa.tavern.entity.Manager;
import aaa.tavern.utils.ServiceUtil;

@Service
public class LoadManagerService {

    @Autowired
    private ManagerRepository managerRepository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ManagerCustomerService managerCustomerService;

    @Autowired
    private PlaceService placeService;

    @Autowired
    private RecipeCustomerService recipeCustomerService;

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    private TableRestService tableRestService;

    /**
     * Load Manager's game selected by its ID
     * 
     * @param managerId
     * @return LoadManagerDto
     */
    public LoadManagerDto loadManager(int managerId) throws Exception {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Manager manager = ServiceUtil.getEntity(managerRepository, managerId);
        if (currentPrincipalName.equals(manager.getPlayer().getEmail())) {

            ManagerDto managerDto = managerService.loadManagerDto(manager);

            List<InventoryManagerIngredientDto> listInventoryManagerIngredientDto = managerService
                    .loadInventoryIngredientsByManager(managerId);
            List<RecipeCustomerDto> listRecipeCustomer = new ArrayList<RecipeCustomerDto>();
            List<CategoryDto> listCategoryDto = categoryService.loadAllCategory();
            List<SubCategoryDto> listSubCategoryDto = subCategoryService.loadAllSubCategory();
            List<IngredientDto> listIngredientDto = ingredientService.loadIngredientsByManagerLevel(manager.getLevel());
            List<CustomerDto> listCustomerDto = managerCustomerService.loadCustomerByManager(managerId);
            for (CustomerDto customerDto : listCustomerDto) {

                listRecipeCustomer.addAll(recipeCustomerService.loadRecipeByCustomer(customerDto.getId()));
            }
            List<PlaceDto> listPlaceDto = placeService.loadPlaceByManagerId(managerId);
            List<TableRestDto> listTableRestDto = new ArrayList<TableRestDto>();
            for (PlaceDto placeDto : listPlaceDto) {
                listTableRestDto = tableRestService.loadTableRestByPlace(placeDto.getId());
            }
            List<RecipeDto> listRecipeDto = recipeService.loadRecipeByLessOrEqualLevel(manager.getLevel());

            LoadManagerDto loadManagerDto = new LoadManagerDto(managerDto, listCategoryDto, listCustomerDto,
                    listIngredientDto, listInventoryManagerIngredientDto, listPlaceDto, listRecipeDto,
                    listRecipeCustomer,
                    listSubCategoryDto,
                    listTableRestDto);
            return loadManagerDto;
        } else {
            throw new Exception("Le manager ne correspond pas a votre compte");
        }
    }
}
