package aaa.tavern.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import aaa.tavern.entity.Ingredient;
import aaa.tavern.entity.Recipe;
import aaa.tavern.entity.RecipeIngredient;

public class RecipeDto {
	private Integer id;
	private String name;
	private Integer sellingPrice;
	private Integer level;
	private Long consommationTime;
	private Long preparationTime;
	private Date peremptionDate;
	private Integer expGiven;
	private Integer idSubCategory;
	private List<InventoryManagerIngredientDto> tabIngredientsForRecipe = new ArrayList<InventoryManagerIngredientDto>();

	protected RecipeDto(){
		
	}
	public RecipeDto(Recipe recipe) {
		this.id = recipe.getId();
		this.name = recipe.getName();
		this.sellingPrice = recipe.getSellingPrice();
		this.level = recipe.getLevel();
		this.consommationTime = recipe.getConsommationTime();
		this.preparationTime = recipe.getPreparationTime();
		this.peremptionDate = recipe.getPeremptionDate();
		this.expGiven = recipe.getExpGiven();
		this.idSubCategory = recipe.getSubCategory().getIdSubCategory();
		for (RecipeIngredient recipeIngredient : recipe.getTabIngredientsForRecipe()) {
			Ingredient ingredient = recipeIngredient.getIngredient();
			Integer quantity = recipeIngredient.getQuantity();
			this.tabIngredientsForRecipe.add(new InventoryManagerIngredientDto(ingredient, quantity));
		}
	}

	/**
	 * Deux RecipeDto sont les mêmes si ils ont le même identifiant.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;

		if (o == null || getClass() != o.getClass())
			return false;

		RecipeDto that = (RecipeDto) o;
		return Objects.equals(id, that.id);
	}

	/**
	 * L'identifiant définit le hash.
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	// #region
	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getSellingPrice() {
		return sellingPrice;
	}

	public Integer getLevel() {
		return level;
	}

	public Long getConsommationTime() {
		return consommationTime;
	}

	public Long getPreparationTime() {
		return preparationTime;
	}

	public Date getPeremptionDate() {
		return peremptionDate;
	}

	public Integer getExpGiven() {
		return expGiven;
	}

	public Integer getIdSubCategory() {
		return idSubCategory;
	}
	public List<InventoryManagerIngredientDto> getTabIngredientsForRecipe() {
		return tabIngredientsForRecipe;
	}


	// #endregion

}
