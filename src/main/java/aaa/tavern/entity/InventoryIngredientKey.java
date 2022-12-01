package aaa.tavern.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Embeddable
public class InventoryIngredientKey implements Serializable{

	private static final long serialVersionUID = -910108551941824015L;

	@Column(name = "manager_id")
    private Integer managerId;

    @Column(name = "ingredient_id")
    private Integer ingredientId;

    public InventoryIngredientKey() {
    }

    public InventoryIngredientKey(Integer managerId, Integer ingredientId){
        this.managerId = managerId ;
        this.ingredientId = ingredientId ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        InventoryIngredientKey that = (InventoryIngredientKey) o;
        return managerId == that.managerId && ingredientId == that.ingredientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerId, ingredientId);
    }
    
    //#region get/set
    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    //#endregion

}
