package aaa.tavern.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Embeddable
public class RecipeCustomerKey implements Serializable{
	
	private static final long serialVersionUID = 5520985500021746614L;

	@Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "recipe_id")
    private Integer recipeId;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

            RecipeCustomerKey that = (RecipeCustomerKey) o;
        return customerId == that.customerId && recipeId == that.recipeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId, recipeId);
    }

    //#region
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Integer recipeId) {
        this.recipeId = recipeId;
    }
    //#endregion
}
