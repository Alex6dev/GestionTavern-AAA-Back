package aaa.tavern.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name="inventory_ingredient")
public class InventoryIngredient implements Serializable{
    

	private static final long serialVersionUID = 4058658748506881792L;

	@EmbeddedId
    private InventoryIngredientKey id = new InventoryIngredientKey() ;
    
    @ManyToOne
    @MapsId("managerId")
    @JoinColumn(name = "manager_id", insertable = false, updatable = false)
    private Manager manager;
    
    @ManyToOne
    @MapsId("ingredientId")
    @JoinColumn(name = "ingredient_id", insertable = false, updatable = false)
    private Ingredient ingredient;
    
    
    private Integer quantity ;
    
    
    public InventoryIngredient() { }
    
    public InventoryIngredient(Manager manager, Ingredient ingredient,Integer quantity){
        this.manager= manager;
        this.ingredient=ingredient;
        this.quantity=quantity;
    }
    /**
	 * Deux InventoryIngredient sont les mêmes si ils ont le même identifiant.
	 */
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        InventoryIngredient that = (InventoryIngredient) o;
        return Objects.equals(id, that.id);
    }

	/**
	 * L'identifiant définit le hash.
	 */
    @Override
    public int hashCode() {
        return id.hashCode();
    }
    
    //#region get/set
    public InventoryIngredientKey getId() {
        return id;
    }


    public void setId(InventoryIngredientKey id) {
        this.id = id;
    }

    public Manager getManager() {
        return manager;
    }


    public void setManager(Manager manager) {
        this.manager = manager;
    }


    public Ingredient getIngredient() {
        return ingredient;
    }


    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }


    public Integer getQuantity() {
        return quantity;
    }


    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    //#endregion  
}
