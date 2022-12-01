package aaa.tavern.dto;

import java.util.List;

public class LoadManagerDto {

    private ManagerDto manager;

    private List<CategoryDto> categories;

    private List<CustomerDto> customers;

    private List<IngredientDto> ingredients;

    private List<InventoryManagerIngredientDto> inventoryManagerIngredient;

    private List<PlaceDto> places;

    private List<RecipeDto> recipes;

    private List<RecipeCustomerDto> recipeCustomer;

    private List<SubCategoryDto> subCategories;

    private List<TableRestDto> tableRests;

    protected LoadManagerDto() {

    }

    public LoadManagerDto(
            ManagerDto manager,
            List<CategoryDto> categories,
            List<CustomerDto> customers,
            List<IngredientDto> ingredients,
            List<InventoryManagerIngredientDto> inventoryManagerIngredient,
            List<PlaceDto> places,
            List<RecipeDto> recipes,
            List<RecipeCustomerDto> recipeCustomer,
            List<SubCategoryDto> subCategories,
            List<TableRestDto> TableRests) {
        this.manager = manager;
        this.categories = categories;
        this.customers = customers;
        this.ingredients = ingredients;
        this.recipeCustomer = recipeCustomer;
        this.inventoryManagerIngredient = inventoryManagerIngredient;
        this.places = places;
        this.recipes = recipes;
        this.subCategories = subCategories;
        this.tableRests = TableRests;
    }

    // #region get
    public ManagerDto getManager() {
        return manager;
    }

    public List<CategoryDto> getCategories() {
        return categories;
    }

    public List<CustomerDto> getCustomers() {
        return customers;
    }

    public List<IngredientDto> getIngredients() {
        return ingredients;
    }

    public List<InventoryManagerIngredientDto> getInventoryManagerIngredient() {
        return inventoryManagerIngredient;
    }

    public List<PlaceDto> getPlaces() {
        return places;
    }

    public List<RecipeDto> getRecipes() {
        return recipes;
    }

    public List<SubCategoryDto> getSubCategories() {
        return subCategories;
    }

    public List<TableRestDto> getTableRests() {
        return tableRests;
    }

    public List<RecipeCustomerDto> getRecipeCustomer() {
        return recipeCustomer;
    }

    // #endregion

}
