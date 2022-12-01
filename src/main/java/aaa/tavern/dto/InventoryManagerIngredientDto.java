package aaa.tavern.dto;

import java.util.Objects;

import aaa.tavern.entity.Ingredient;

public class InventoryManagerIngredientDto {

	private int id;

	private String name;

	private int level;

	private int buyingPrice;

	private int idSubCategory;
	
	private int quantity;

	protected InventoryManagerIngredientDto() {

	}

	public InventoryManagerIngredientDto(Ingredient ingredient, int quantity) {
		this.id = ingredient.getId();
		this.name = ingredient.getName();
		this.level = ingredient.getLevel();
		this.buyingPrice = ingredient.getBuyingPrice();
		this.idSubCategory = ingredient.getSubCategory().getIdSubCategory();
		this.quantity = quantity;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InventoryManagerIngredientDto other = (InventoryManagerIngredientDto) obj;
		return id == other.id;
	}

	// #region Get
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public int getBuyingPrice() {
		return buyingPrice;
	}



	public void setLevel(int level) {
		this.level = level;
	}


	public int getIdSubCategory() {
		return idSubCategory;
	}

	public int getQuantity() {
		return quantity;
	}


	
	

	// #endregion

}
